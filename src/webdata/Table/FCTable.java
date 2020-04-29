package webdata.Table;

import java.util.ArrayList;

/**
 * Class to create the blocked FC
 */
public class FCTable {
    private ArrayList<String> sortedTerms;
    private ArrayList<FCRow> dictionary;
    private ConcatenatedString concatStr;
    private int k;
    private int currTermPtr;

    FCTable(ArrayList<String> sortedTerms, int k) {
        this.sortedTerms = sortedTerms;
        this.k = k;
        this.currTermPtr = 0;
        this.concatStr = new ConcatenatedString();
    }

    private void create() {
//        TODO dont forget to deal with endcases
        for (int i = 0; i < this.sortedTerms.size(); i++) {
            boolean isKth = i % this.k == 0;
            String previousTerm = this.sortedTerms.get(i - 1);
            String term = this.sortedTerms.get(i);
            FCRow row = new FCRow(i, isKth, term, previousTerm);
            row.create();
            String croppedTerm = row.getCroppedTerm();
            this.concatStr.update(croppedTerm);
            this.dictionary.add(row);
        }
    }

//  TODO should this be here?
    private Integer updateAndRetrieveTermPtr(int ind) {
        if (ind % this.k == 0) {
            this.currTermPtr += this.k;
            return this.currTermPtr;
        } else {
            return null;
        }
    }

}
