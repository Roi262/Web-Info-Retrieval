package webdata;

import webdata.WriteToDisk.Table.FCRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import static webdata.Constants.BinarySearchConstants.*;
import static webdata.Constants.HyperParameters.k;

public class KinKDictReader {
    private ArrayList<FCRow> table;
    private String allTermString;

    public KinKDictReader(ArrayList<FCRow> table, String allTermString) {
        this.table = table;
        this.allTermString = allTermString;
    }

    /**
     * Return the number of reviews containing a given token (i.e., word)
     * Returns 0 if there are no reviews containing this token
     */
    public int getTokenFrequency(String token) {
        int rowInd = findRowIndex(token);
        FCRow row = table.get(rowInd);
        ArrayList<Integer> pList = DeltaCompressor.deltaDecode(row.getCompressedPostingList());
        assert pList.size() % 2 == 0;
//        TODO notice the above code is duplicated
        return pList.size()/2;
    }

    /**
     * Return the number of times that a given token (i.e., word) appears in
     * the reviews indexed
     * Returns 0 if there are no reviews containing this token
     */
    public int getTokenCollectionFrequency(String token) {
        int rowInd = findRowIndex(token);
        FCRow row = table.get(rowInd);
        return row.getFreq();
    }


    /**
     * The Posting List
     * Return a series of integers of the form id-1, freq-1, id-2, freq-2, ... such
     * that id-n is the n-th review containing the given token and freq-n is the
     * number of times that the token appears in review id-n
     * Note that the integers should be sorted by id
     * Returns an empty Enumeration if there are no reviews containing this token
     */
    public Enumeration<Integer> getReviewsWithToken(String token) {
        int rowInd = findRowIndex(token);
        FCRow row = table.get(rowInd);
        ArrayList<Integer> pList = DeltaCompressor.deltaDecode(row.getCompressedPostingList());
        assert pList.size() % 2 == 0;
        ArrayList<Integer> reviewIDs = resetValuesFromGaps(new ArrayList<>(pList.subList(0, pList.size()/2)));
        ArrayList<Integer> frequencies = new ArrayList<>(pList.subList(pList.size()/2, pList.size()));
        return Collections.enumeration(mergedPList(reviewIDs, frequencies));
    }

    private ArrayList<Integer> mergedPList(ArrayList<Integer> reviewIDs, ArrayList<Integer> frequencies){
        assert reviewIDs.size() == frequencies.size();
        ArrayList<Integer> mergedPList = new ArrayList<>();
        for (int i=0; i < reviewIDs.size(); i++){
            mergedPList.add(reviewIDs.get(i));
            mergedPList.add(frequencies.get(i));
        }
        return mergedPList;
    }

    private ArrayList<Integer> resetValuesFromGaps(ArrayList<Integer> pList){
        int prevValue = 0;
        for (int i=0; i < pList.size(); i++){
            int realValue = prevValue + pList.get(i);
            pList.set(i, realValue);
            prevValue = realValue;
        }
        return pList;
    }

    private int findRowIndex(String token) {
        int numOfTermPointers = table.size() / k;
        int currTermPointerIndex = numOfTermPointers / 2; // the i'th term pointer
        int currIndex = currTermPointerIndex * k; // the index the i'th term pointer points to in the long string
        FCRow kTermRow = table.get(currIndex);
        String currKTerm = allTermString.substring(currIndex, kTermRow.getLength());

        while (currIndex + k < allTermString.length()) {
            int offset = wordInBlock(currIndex, token, currKTerm);
            if (offset >= 0) {
                return currIndex + offset;
            }
            if (offset == SMALLER) {
                currTermPointerIndex /= 2;
            } else if (offset == LARGER) {
                currTermPointerIndex += currTermPointerIndex / 2;
            }
            currIndex = currTermPointerIndex * k;
        }
        throw new NoSuchElementException();
    }

    /**
     * @param termPtr
     * @param token
     * @return index of offset in block if word is in K block else smaller or larger (lexicographic) than the words in the block
     */
    private int wordInBlock(int termPtr, String token, String KTerm) {
        if (KTerm.equals(token)) {
            return 0;
        }
        if (token.compareTo(KTerm) < 0) {
            return SMALLER;
        }

        int strPtr = KTerm.length();
        for (int i = 1; i < k; i++) {
            FCRow row = table.get(termPtr + i);
            String prefix = KTerm.substring(0, row.getPrefixSize());
            int suffixLength = row.getLength() - row.getPrefixSize();
            String suffix = allTermString.substring(strPtr, suffixLength);
            if (token.equals(prefix + suffix)) {
                return i;
            }
            strPtr += suffixLength;
        }
        return LARGER;
    }
}
