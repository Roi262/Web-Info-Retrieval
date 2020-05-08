package webdata.Objects;

import java.util.*;


public class ProductDict {
    private Enumeration<Integer> reviewsIDs;

    //    reviews by productID
    private Map<String, TreeSet<Integer>> dict;

    public ProductDict() {
        this.dict = new HashMap<>();
    }

    public void add(String prodID, Integer reviewID) {
        TreeSet<Integer> reviews = dict.getOrDefault(prodID, new TreeSet<>());
        reviews.add(reviewID);
        dict.put(prodID, reviews);
    }

    public getCompressedDict()


    public Enumeration<Integer> getReviewsIDs(String prodID) {
        return Collections.enumeration(dict.get(prodID));
    }
}
