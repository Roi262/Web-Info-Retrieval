package webdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FeaturesDict {
    private static final int PRODUCT_ID = 0;
    private static final int PRODUCT_ID = 0;
    private static final int PRODUCT_ID = 0;
    private static final int PRODUCT_ID = 0;
    private static final int PRODUCT_ID = 0;

    private ArrayList<String> dict;


    public FeaturesDict() {
        this.dict = new ArrayList<>();
    }

    /**
     * NOTE see that you elements by reviewID sequentaly, as we will get things by ID being the index of the list
     * @param productID
     * @param score
     * @param helpNumerator
     * @param helpDenominator
     * @param reviewLen
     */
    public void add(String productID, Integer score,
                    Integer helpNumerator, Integer helpDenominator, Integer reviewLen) {
        String values = productID + DeltaCompressor.compressList(
                new ArrayList<>(Arrays.asList(score, helpNumerator, helpDenominator, reviewLen)));
        dict.add(values);
    }

    public String getProductID(int reviewID){
        ArrayList<Integer> values = DeltaCompressor.decode(dict.get(reviewID));
        return values.get(PRODUCT_ID);
    }


}
