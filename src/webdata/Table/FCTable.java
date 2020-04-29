package webdata.Table;

import java.util.ArrayList;

/**
 * Class to create the blocked FC
 */
public class FCTable {
    private ArrayList<String> sortedTerms;
    private ArrayList<FCRow> dictionary;
    private ConcatenatedStringOLD concatStr;
    private int k;
    private int currTermPtr;

    FCTable(ArrayList<String> sortedTerms, int k) {
        this.sortedTerms = sortedTerms;
        this.k = k;
        this.currTermPtr = 0;
    }

    private void create() {
        String previousTerm = null;
        for (int i = 0; i < this.sortedTerms.size(); i++) {
            FCRow row = createRow(i);
            this.dictionary.add(row);
        }
    }

    private FCRow createRow(int ind) {
        createValues();
        boolean isKth = ind % this.k == 0;
        String previousTerm = this.sortedTerms.get(ind - 1);
        String term = this.sortedTerms.get(ind);
        int freq = getFreq();
        InvertedIndex = new InvertedIndex();
        Integer length = ind + 1 % this.k == 0 ? null : term.length();

        FCRow obj = new FCRow();
//        if the next term is a Kth term then length is null
        Integer prefixSize = calcPrefix(previousTerm, term, isKth);
        Integer termPtr = updateAndRetrieveTermPtr(ind);
        String croppedTerm = cropTerm(term, length, prefixSize);
        this.concatStr.addTerm();
        addTermPtr();
    }


    private String cropTerm(String term, Integer length, Integer prefixSize) {
        if (prefixSize != null) {
            String prefix = term.substring(0, prefixSize);
            assert prefix.length() == prefixSize : "Cropping string at wrong index.";
            term = term.replace(prefix, "");
        }
        return term;
    }

    /**
     * @param previousTerm complete string of previous term
     * @param term         complete string of current term
     * @param isKth        True if ind%k==0 else False
     * @return the matching prefix term has with previous term
     */
    private Integer calcPrefix(String previousTerm, String term, Boolean isKth) {
        if (isKth) {
            return null;
        }
        int i = 0;
        do {
            i++;
        }
        while (previousTerm.charAt(i) == term.charAt(i));
        return i;
    }

    private Integer updateAndRetrieveTermPtr(int ind) {
        if (ind % this.k == 0) {
            this.currTermPtr += this.k;
            return this.currTermPtr;
        } else {
            return null;
        }
    }


    public ArrayList<FCRow> getDictionary() {
        this.create();
        return this.dictionary;
    }


}
