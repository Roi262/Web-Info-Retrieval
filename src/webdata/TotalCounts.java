package webdata;

import java.io.Serializable;

public class TotalCounts implements Serializable {
    private int totalNumOfReviews;
    private int totalNumOfTokens;

    public TotalCounts(){
        this.totalNumOfReviews = 0;
        this.totalNumOfTokens = 0;
    }

    public void setTotalNumOfReviews(int totalNumOfReviews) {
        this.totalNumOfReviews = totalNumOfReviews;
    }

    public void setTotalNumOfTokens(int totalNumOfTokens) {
        this.totalNumOfTokens = totalNumOfTokens;
    }

    public int getTotalNumOfReviews() {
        return totalNumOfReviews;
    }

    public int getTotalNumOfTokens() {
        return totalNumOfTokens;
    }
}
