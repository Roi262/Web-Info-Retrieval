package webdata.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeaturesDict {
    private Map<Integer, FeaturesObject> dict;

    public FeaturesDict() {
        this.dict = new HashMap<>();
    }

    public void add(Integer reviewID, String productID, Integer score,
                       Integer helpNumerator, Integer helpDenominator, Integer reviewLen) {
        FeaturesObject featuresObject = new FeaturesObject(
                productID, score, helpNumerator, helpDenominator, reviewLen);
        this.dict.put(reviewID, featuresObject);
    }
}
