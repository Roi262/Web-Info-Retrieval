package webdata;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to create the blocked FC
 */
public class FCTable {

    /**concatenated string objects***/
    private StringBuilder concatStrBuilder;
    private String concatStr;

    /*****/
    TreeMap<String, TermsObject> sortedTokensDict;
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
}
