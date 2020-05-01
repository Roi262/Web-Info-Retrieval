package webdata.Table;

import java.util.PrimitiveIterator;

public class FCRow {
    private int freq;
    private InvertedIndex invertedIndex;
    private Integer length;
    private Integer prefixSize;
    private Integer termPtr;
    private int ind;
    private boolean isKth;
    private String term;
    private String previousTerm;
    private String croppedTerm;


    /***********GETTERS AND SETTERS**************/
    public String getCroppedTerm() {
        return croppedTerm;
    }
    /*************************/


    /**
     * @param ind          index of this row object in table
     * @param isKth        True if ind%k==0 else False
     * @param term         complete string of current term
     * @param previousTerm complete string of previous term
     */
    public FCRow(int ind, boolean isKth, String term, String previousTerm) {
        this.ind = ind;
        this.isKth = isKth;
        this.term = term;
        this.previousTerm = previousTerm;
    }

    public void create() {
        int freq = getFreq();
        InvertedIndex = new InvertedIndex();
        updateLength();
        updatePrefix();
        Integer termPtr = updateAndRetrieveTermPtr(ind);
        this.cropTerm();
        addTermPtr();
    }

    private void updateLength() {
//        TODO fix, need k here
//        if the next term is a Kth term then length is null
        this.length = ind + 1 % this.k == 0 ? null : term.length();
    }


    private void cropTerm() {
        if (prefixSize != null) {
            String prefix = term.substring(0, prefixSize);
            assert prefix.length() == prefixSize : "Cropping string at wrong index.";
            this.croppedTerm = term.replace(prefix, "");
        } else {
            this.croppedTerm = term;
        }
    }

    /**
     * @return the matching prefix term has with previous term
     */
    private void updatePrefix() {
        if (isKth) {
            this.prefixSize = null;
        }
        int i = 0;
        do {
            i++;
        }
        while (previousTerm.charAt(i) == term.charAt(i));
        this.prefixSize = i;
    }
}
