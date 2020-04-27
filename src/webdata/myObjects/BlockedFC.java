package webdata.myObjects;

import java.util.ArrayList;

/**
 * Class to create the blocked FC
 */
public class BlockedFC {
    private ArrayList<String> sortedTerms;
    private ArrayList<FCObject> dictionary;
    private int k;

    BlockedFC(ArrayList<String> sortedTerms, int k) {
        this.sortedTerms = sortedTerms;
        this.k = k;
    }


    private FCObject createObject(Boolean addTermPtr) {
        FCObject obj = new FCObject();
        String term = this.sortedTerms.get(i);
        int freq = getFreq();
        InvertedIndex = new InvertedIndex();
        int length = term.length();
        int prefixSize = getPrefix(previousTerm, term);
        int termPtr;
//        TODO add these values to FCObject by constructor or by setter methods
    }

    private void createDictionary() {
        String previousTerm = null;
        for (int i = 0; i < this.sortedTerms.size(); i++) {
            FCObject object;
            if (i % this.k == 0) {
                object = createObject(true);
            } else {
                object = createObject(false);
            }
            this.dictionary.add(object);
        }
    }

    public ArrayList<FCObject> getDictionary() {
        this.createDictionary();
        return this.dictionary;
    }


}
