package webdata.Table;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to create the blocked FC
 */
public class FCTable {
    TreeMap<String, TermsObject> sortedTokensDict;
    private ArrayList<FCRow> dictionary;
    private ConcatenatedString concatStr;
    private int k;
    private int currTermPtr;

    public FCTable(Map<String, TermsObject> tokensDict, int k){
        this.k = k;
        this.currTermPtr = 0;
        this.concatStr = new ConcatenatedString();
        this.sortedTokensDict = new TreeMap<>(tokensDict); // treeMap is naturally sorted
    }

    public void create() {
//        TODO dont forget to deal with endcases
        int i = 0;
        String previousTerm = null, term;
        // TreeMap is naturally sorted
        for (Map.Entry<String, TermsObject> entry : sortedTokensDict.entrySet()){
            term = entry.getKey();
            boolean isKth = i % this.k == 0;
            FCRow row = new FCRow(i, isKth, k, term, entry.getValue(), previousTerm, currTermPtr);
            String croppedTerm = row.getCroppedTerm();
            this.concatStr.update(croppedTerm);
            this.dictionary.add(row);
            previousTerm = term;
            i++;
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
