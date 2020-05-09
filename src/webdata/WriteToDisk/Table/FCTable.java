package webdata.WriteToDisk.Table;

import webdata.TermsObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to create the blocked FC
 */
public class FCTable {

    /**
     * concatenated string objects
     ***/
    private StringBuilder concatStrBuilder;
    private String concatStr;

    /*****/
    TreeMap<String, TermsObject> sortedTokensDict;

    /*****THE TABLE*****/
    private ArrayList<FCRow> table;

    private int k;
    private int currTermPtr;

    public FCTable(Map<String, TermsObject> tokensDict, int k) {
        this.table = new ArrayList<>();
        this.k = k;
        this.currTermPtr = 0;
        this.concatStrBuilder = new StringBuilder();
        this.sortedTokensDict = new TreeMap<>(tokensDict); // treeMap is naturally sorted
    }

    public void create() {
        int i = 0;
        String previousTerm = null, term;
        // TreeMap is naturally sorted
        for (Map.Entry<String, TermsObject> entry : sortedTokensDict.entrySet()) {
            term = entry.getKey();
            boolean isKth = i % k == 0;
            FCRow row = new FCRow(i, isKth, k, term, entry.getValue(), previousTerm, currTermPtr);
            String croppedTerm = row.getCroppedTerm();
            this.concatStrBuilder.append(croppedTerm);
            table.add(row);
            previousTerm = term;
            currTermPtr += croppedTerm.length();
            i++;
        }
        concatStr = concatStrBuilder.toString();
    }

    /**
     * Return the number of reviews containing a given token (i.e., word)
     * Returns 0 if there are no reviews containing this token
     */
    public int getTokenFrequency(String token) {
        int rowInd = findRowIndex(token);
        FCRow row = table.get(rowInd);
        return row.getFreq();
    }

    private int findRowIndex(String token) {
        int numOfTermPointers = table.size() / k;
        int currIndex = numOfTermPointers / 2;
        String currTerm =
        assert currIndex % k == 0;
        while (true) {
            int offset = inBlock(currTermPtr, token);
            if (offset != -1) {
                return currIndex + offset;
            }
            if (token.compareTo())
        }


    }

    /**
     * @param termPtr
     * @param token
     * @return index in block if token is in the block, else -1
     */
    private int inBlock(int termPtr, String token) {

    }

    private String getTerm(int termPtr, int offset, String token) {
        FCRow kTermRow = table.get(termPtr);
        String KTerm = concatStr.substring(termPtr, kTermRow.getLength());

        FCRow row = table.get(termPtr + offset);
        int prefSize = row.getPrefixSize();
        String termSuffix = token.substring(prefSize);

        String term = concatStr.substring(termPtr, length);
    }

    private String[] getWordsInBlock(int termPtr, int offset, String token) {
        String[] wordsInBlock = new String[k];
        FCRow kTermRow = table.get(termPtr);
        String KTerm = concatStr.substring(termPtr, kTermRow.getLength());
        wordsInBlock[0] = KTerm;
        int strPtr = KTerm.length();
        for (int i = 1; i < k; i++) {
            FCRow row = table.get(termPtr + i);
            String prefix = KTerm.substring(0, row.getPrefixSize());
            int suffixLength = row.getLength() - row.getPrefixSize();
            String suffix = concatStr.substring(strPtr, suffixLength);
            wordsInBlock[i] = prefix + suffix;
            strPtr += suffixLength;


//            String termSuffix = token.substring(prefSize);


        }


    }


}
