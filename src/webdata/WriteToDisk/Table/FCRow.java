package webdata.WriteToDisk.Table;

import webdata.PostingList;
import static webdata.Constants.HyperParameters.k;


public class FCRow {
//    private InvertedIndex invertedIndex;
    private int freq;
    private String compressedPostingList;
    private Integer length;
    private Integer prefixSize;
    private Integer termPtr;
    private int currInd;
    private boolean isKth;
    private String previousTerm, term;
    private String croppedTerm;

    /***********GETTERS AND SETTERS**************/
    public String getCroppedTerm() {
        return croppedTerm;
    }


    public int getFreq() {
        return freq;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getPrefixSize() {
        return prefixSize;
    }

    public String getCompressedPostingList() {
        return compressedPostingList;
    }
    /*************************/


    /**
     * @param ind          index of this row object in table
     * @param isKth        True if ind%k==0 else False
     * @param term         complete string of current term
     * @param previousTerm complete string of previous term
     */
    public FCRow(int ind, boolean isKth, String term, PostingList postingList, String previousTerm, int termPtr) {
        this.currInd = ind;
        this.isKth = isKth;
        this.term = term;
        this.previousTerm = previousTerm;
        this.freq = postingList.getFreq();
        this.termPtr = termPtr;
        this.compressedPostingList = postingList.getCompressedPostingListWithFrequencies();
        create();
    }

    private void create() {
        updateLength();
        updatePrefix();
        this.cropTerm();
    }


    /**
     * if the next term is a Kth term then length is null
     */
    private void updateLength() {
        length = (currInd + 1) % k == 0 ? null : term.length();
    }

    private void cropTerm() {
        if (prefixSize != null) {
            String prefix = term.substring(0, prefixSize);
            assert prefix.length() == prefixSize : "Cropping string at wrong index.";
            croppedTerm = term.replace(prefix, "");
        } else {
            croppedTerm = term;
        }
    }

    /**
     * @return the matching prefix term has with previous term
     */
    private void updatePrefix() {
        if (isKth | previousTerm == null) {
            prefixSize = null;
            return;
        }
        int i = 0;
        while(i < previousTerm.length() && i < term.length() && (previousTerm.charAt(i) == term.charAt(i))) i++;
        prefixSize = i;
    }
}
