package webdata.Table;

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
        DeltaCompressor dComp = new DeltaCompressor(); //TODO maybe get compressor as an argument
        sortPostingList();
        updatePostingListToGaps();
        dComp.compressList(postingList);
        return dComp.getDeltaCompressedPL();
    }

    protected void sortPostingList() {
        Collections.sort(this.postingList);
    }

    protected void updatePostingListToGaps() {
        ArrayList<Integer> gaps = new ArrayList<>(this.postingList.size());
        gaps.set(0, this.postingList.get(0));
        for (int i = 1; i < this.postingList.size(); i++) {
            Integer diff = this.postingList.get(i) - this.postingList.get(i - 1);
            gaps.set(i, diff);
        }
        this.postingList = gaps;
    }

}
