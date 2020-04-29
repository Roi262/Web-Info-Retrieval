package webdata.myObjects;

import java.util.ArrayList;

/**
 * Class to create the blocked FC
 */
public class BlockedFC {
    private ArrayList<String> sortedTerms;
    private ArrayList<FCObject> dictionary;
    private int k;
    private int currTermPtr;

    BlockedFC(ArrayList<String> sortedTerms, int k) {
        this.sortedTerms = sortedTerms;
        this.k = k;
        this.currTermPtr = 0;
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
        do{
            i++;
        }
        while(previousTerm.charAt(i) == term.charAt(i));
        return i;
    }

    private Integer updateAndRetrieveTermPtr(int ind){
        if (ind % this.k == 0){
            this.currTermPtr += this.k;
            return this.currTermPtr;
        }
        else{
            return null;
        }
    }

    private FCObject createObject(int ind, Boolean isKth) {
        FCObject obj = new FCObject();
        String previousTerm = this.sortedTerms.get(ind - 1);
        String term = this.sortedTerms.get(ind);
        int freq = getFreq();
        InvertedIndex = new InvertedIndex();
//        if the next term is a Kth term then length is null
        Integer length = ind + 1 % this.k == 0 ? null: term.length();
        Integer prefixSize = calcPrefix(previousTerm, term, isKth);
        Integer termPtr = updateAndRetrieveTermPtr(ind);
        updateConcatenatedString(term, length, prefixSize);
//        TODO add these values to FCObject by constructor or by setter methods
    }

    private void updateConcatenatedString(String term, Integer length, Integer prefixSize) {
//        TODO update concatenated string of dictionary with the term
        String concatenateTerm = term;
        if(prefixSize != null){
            String prefix = term.substring(0, prefixSize);
            assert prefix.length() != prefixSize: "Cropping string at wrong index.";
            concatenateTerm.replace(prefix, "");
        }
//        TODO concatenate to large string
        concatenate(concatenateTerm);
    }

    private void createDictionary() {
        String previousTerm = null;
        for (int i = 0; i < this.sortedTerms.size(); i++) {
            FCObject object;
            Boolean isKth = false;
            if (i % this.k == 0) {
                isKth = true;
            }
            object = createObject(i, isKth);
            this.dictionary.add(object);
        }
    }

    public ArrayList<FCObject> getDictionary() {
        this.createDictionary();
        return this.dictionary;
    }


}
