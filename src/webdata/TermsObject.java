package webdata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TermsObject {
    // total number of reviews containing the token at least once
    private Integer inKReviews;
    // total number of appearences in all reviews alltogether
    private Integer freq;
    private ArrayList<Integer> postingList;

    public TermsObject() {
        this.postingList = new ArrayList<>();
        this.inKReviews = 0;
        this.freq = 0;
    }

    public Integer getInKReviews() {
        return inKReviews;
    }

    public Integer getFreq() {
        return freq;
    }

    public ArrayList<Integer> getPostingList() {
        return postingList;
    }

    /**
     * Called once for every review where the token shows up
     */
    public void update(Integer newAppearences, Integer reviewID){
        freq += newAppearences;
        inKReviews++;
        postingList.add(reviewID);
    }

}
