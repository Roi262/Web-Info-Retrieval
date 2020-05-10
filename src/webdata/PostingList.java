package webdata;

import java.util.ArrayList;

/**
 * a series of integers of the form id-1, freq-1, id-2, freq-2, ... such
 * that id-n is the n-th review containing the given token and freq-n is the
 * number of times that the token appears in review id-n
 **/
public class PostingList {

    /** The list of only the review IDs*/
    private final ArrayList<Integer> postingList;

    /** The respective list of frequencies */
    private final ArrayList<Integer> frequencyList;


    public PostingList() {
        this.postingList = new ArrayList<>();
        this.frequencyList = new ArrayList<>();
    }

    public Integer getInKReviews() {
        return postingList.size();
    }

    /**
     * calculate frequency by counting all values of odd indeces in the posting list
     * @return
     */
    public int getFreq() {
        int freq = 0;
        for (int i = 0; i < frequencyList.size(); i++) {
            freq += frequencyList.get(i);
        }
        return freq;
    }

    /**
     * Called once for every review where the token shows up.
     * This list is updated in sorted order
     */
    public void update(Integer numOfAppearancesInReview, Integer reviewID) {
        postingList.add(reviewID);
        frequencyList.add(numOfAppearancesInReview);
    }

    public String getCompressedPostingListWithFrequencies(){
        updatePostingListToGaps();
        ArrayList<Integer> postingListWithFrequencies = postingList;
        postingListWithFrequencies.addAll(frequencyList);
        return DeltaCompressor.compressList(postingListWithFrequencies);
    }

    private void updatePostingListToGaps() {
        for(int i = postingList.size()-1; i > 0; i--){
            postingList.set(i, postingList.get(i) - postingList.get(i-1));
        }
    }

}
