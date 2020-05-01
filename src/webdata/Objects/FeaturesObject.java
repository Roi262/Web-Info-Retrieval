package webdata.Objects;

public class FeaturesObject {
    private String productID;
    private Integer score;
    private Integer helpNumerator;
    private Integer helpDenominator;
    private Integer reviewLen;

    public FeaturesObject(String productID, Integer score, Integer helpNumerator,
                          Integer helpDenominator, Integer reviewLen) {
        this.productID = productID;
        this.score = score;
        this.helpNumerator = helpNumerator;
        this.helpDenominator = helpDenominator;
        this.reviewLen = reviewLen;
    }

    public String getProductID() {
        return productID;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getHelpNumerator() {
        return helpNumerator;
    }

    public Integer getHelpDenominator() {
        return helpDenominator;
    }

    public Integer getReviewLen() {
        return reviewLen;
    }
}
