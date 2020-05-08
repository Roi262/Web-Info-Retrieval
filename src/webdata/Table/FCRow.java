package webdata.Table;

public class FCRow {
    private int freq;
    private InvertedIndex invertedIndex;
    private Integer length;
    private Integer prefixSize;
    private Integer termPtr;
    private int ind;
    private int k;
    private boolean isKth;
    private String previousTerm, term;
    private String croppedTerm;
    private String compressedString;

    /***********GETTERS AND SETTERS**************/
    public String getCroppedTerm() {
        return croppedTerm;
    }

    public String getCompressedString() {
        return compressedString;
    }
    /*************************/


    /**
     * @param ind          index of this row object in table
     * @param isKth        True if ind%k==0 else False
     * @param term         complete string of current term
     * @param previousTerm complete string of previous term
     */
    public FCRow(int ind, boolean isKth, int k, String term, TermsObject termsObject, String previousTerm, int termPtr) {
        this.ind = ind;
        this.isKth = isKth;
        this.term = term;
        this.k = k;
        this.previousTerm = previousTerm;
        this.freq = termsObject.getFreq();
        this.termPtr = termPtr;
        this.invertedIndex = new InvertedIndex(termsObject.getPostingList(), termsObject.getInKReviews());
        this.compressedString = invertedIndex.create();
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
        length = (ind + 1) % k == 0 ? null : term.length();
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
        if (isKth | previousTerm == null) {
            this.prefixSize = null;
            return;
        }
        int i = 0;
        do {
            i++;
        }
        while (previousTerm.charAt(i) == term.charAt(i));
        this.prefixSize = i;
    }
}
