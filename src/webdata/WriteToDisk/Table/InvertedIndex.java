package webdata.WriteToDisk.Table;

import webdata.DeltaCompressor;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class that compresses the posting list
 */
public class InvertedIndex {
    private String term;
    private ArrayList<Integer> postingList; // a list of document IDs

    public InvertedIndex(ArrayList<Integer> postingList, int totalCountOfTokenInAllReviews) {
        this.postingList = postingList;
//        NOTE that the first two elements of the posting list are its size and the total number
//        of appearances of the term
        this.postingList.add(0, postingList.size());
        this.postingList.add(1, totalCountOfTokenInAllReviews);
    }

    public String create() {
        sortPostingList();
        updatePostingListToGaps();
        return DeltaCompressor.compressList(postingList);
    }

    protected void sortPostingList() {
        Collections.sort(postingList);
    }

    protected void updatePostingListToGaps() {
        for(int i = postingList.size()-1; i > 0; i--){
            postingList.set(i, postingList.get(i) - postingList.get(i-1));
        }
    }

}
