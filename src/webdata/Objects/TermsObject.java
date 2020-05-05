package webdata.Objects;

import java.util.HashSet;
import java.util.Set;

public class TermsObject {
    // total number of reviews containing the token at least once
    private Integer inKReviews;
    // total number of appearences in all reviews alltogether
    private Integer numOfAppearences;
    private Set<Integer> postingList;

    public TermsObject() {
        this.postingList = new HashSet<>();
        this.inKReviews = 0;
        this.numOfAppearences = 0;
    }

    /**
     * Called once for every review where the token shows up
     */
    public void update(Integer newAppearences, Integer reviewID){
        numOfAppearences += newAppearences;
        inKReviews++;
        postingList.add(reviewID);
    }

}
