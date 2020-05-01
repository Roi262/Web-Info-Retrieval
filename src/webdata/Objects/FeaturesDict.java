package webdata.Objects;

import java.util.ArrayList;
import java.util.Map;

public class FeaturesDict {
    private Map<String, FeaturesObject> dict;

    public FeaturesDict() {
    }

    protected void add(String reviewID, String productID, Integer score,
                       Integer helpNumerator, Integer helpDenominator, Integer reviewLen) {
        FeaturesObject featuresObject = new FeaturesObject(
                productID, score, helpNumerator, helpDenominator, reviewLen);
        this.dict.put(reviewID, featuresObject);
    }
}