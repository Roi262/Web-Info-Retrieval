package webdata.Objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TermsObject {
    private Integer numOfReviewsContainingToken;
    private Integer totalNumOfAppearencesInAllReviews;
    private Set<Integer> postingList;

    public TermsObject() {
        this.postingList = new HashSet<>();
    }

    public void update(int numOfNewAppearences, Integer reviewID){
        postingList.add(reviewID);
        totalNumOfAppearencesInAllReviews += numOfNewAppearences;
        numOfReviewsContainingToken += numOfNewAppearences;
    }

}
