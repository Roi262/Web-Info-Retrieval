package webdata.WriteToDisk.Table;


import webdata.PostingList;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static webdata.Constants.HyperParameters.k;

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
    TreeMap<String, PostingList> sortedTokensDict;

    /*****THE TABLE*****/
    private ArrayList<FCRow> table;

    private int currTermPtr;

    public FCTable(Map<String, PostingList> tokensDict) {
        this.table = new ArrayList<>();
        this.currTermPtr = 0;
        this.concatStrBuilder = new StringBuilder();
        this.sortedTokensDict = new TreeMap<>(tokensDict); // treeMap is naturally sorted
    }

    public void create() {
        int i = 0;
        String previousTerm = null, term;
        // TreeMap is naturally sorted
        for (Map.Entry<String, PostingList> entry : sortedTokensDict.entrySet()) {
            term = entry.getKey();
            boolean isKth = i % k == 0;
            FCRow row = new FCRow(i, isKth, term, entry.getValue(), previousTerm, currTermPtr);
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
