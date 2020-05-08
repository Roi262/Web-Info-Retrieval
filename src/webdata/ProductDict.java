package webdata;

import java.util.*;


public class ProductDict {
    private Enumeration<Integer> reviewsIDs;

    //    reviews by productID
    private Map<String, TreeSet<Integer>> dict;
    private Map<String, String> compressedDict;

    public ProductDict() {
        this.dict = new HashMap<>();
        this.compressedDict = new HashMap<>();
    }

    public void add(String prodID, Integer reviewID) {
        TreeSet<Integer> reviews = dict.getOrDefault(prodID, new TreeSet<>());
        reviews.add(reviewID);
        dict.put(prodID, reviews);
    }

    public Map<String, TreeSet<Integer>> getDict() {
        return dict;
    }

    public void compressDict(){
        for (Map.Entry<String, TreeSet<Integer>> entry : dict.entrySet()) {
            TreeSet<Integer> reviews = dict.getOrDefault(prodID, new TreeSet<>());
            reviews.add(reviewID);
            dict.put(prodID, reviews);

        }



        }


    public Enumeration<Integer> getReviewsIDs(String prodID) {
        return Collections.enumeration(dict.get(prodID));
    }
}
