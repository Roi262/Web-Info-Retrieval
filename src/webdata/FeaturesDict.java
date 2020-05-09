package webdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static webdata.Constants.*;
//import static webdata.Constants.ReviewValues.*;

public class FeaturesDict {
//    private static final int PROD_ID = 0, SCORE = 1, HELP_N = 2, HELP_D = 3, REVIEW_LEN = 4;
//    private static final String SEPARATOR = "#";

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
        String values = productID + SEPARATOR + DeltaCompressor.compressList(
                new ArrayList<>(Arrays.asList(score, helpNumerator, helpDenominator, reviewLen)));
        dict.add(values);
    }

    public String getProductID(int reviewID){
        String value = dict.get(reviewID);
        return splitOnSeparator(value)[0];
    }

    public int getValue(int reviewID, int type){
        assert type == SCORE || type == HELP_D || type == HELP_N || type == REVIEW_LEN;
        String value = dict.get(reviewID);
        String binaryString = splitOnSeparator(value)[1];
        ArrayList<Integer> values = DeltaCompressor.deltaDecode(binaryString);
        return values.get(type);
    }


    private static String[] splitOnSeparator(String str){
        String[] list = str.split(SEPARATOR);
        assert list.length == 2;
        return list;
    }
}
