package webdata.Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;


//TODO THIS CLASS IS REDUNDANT
public class ProductDict {
    private int productID; //TODO maybe this is a string?
//    private Enumeration<Integer> reviewsIDs;
    private Map<Integer, ArrayList<Integer>> reviewsByProductIDDict;
    private ArrayList<Integer> reviewsArr;
    public ProductDict() {
        this.productID = productID;
    }

    public void add(Integer reviewID){
        reviewsArr.add(reviewID);
    }

    public Enumeration<Integer> getReviewsIDs() {
        return Collections.enumeration(reviewsArr);
    }
}
