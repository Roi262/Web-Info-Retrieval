package webdata;

import webdata.WriteToDisk.FeaturesDict;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import static webdata.Constants.*;

public class IndexReader {

    /**
     * Returns the product identifier for the given review
     * Returns null if there is no review with the given identifier
     */
    public String getProductId(int reviewId) {
        FeaturesDict featuresDict = recoverDict(dict_file);
        return featuresDict.getProductID(reviewId);
    }

    /**
     * Returns the score for a given review
     * Returns -1 if there is no review with the given identifier
     */
    public int getReviewScore(int reviewId) {
        FeaturesDict featuresDict = recoverDict(dict_file);
        return featuresDict.getValue(reviewId, SCORE);
    }

    /**
     * Returns the numerator for the helpfulness of a given review * Returns -1 if there is no review with the given identifier
     */
    public int getReviewHelpfulnessNumerator(int reviewId) {
        FeaturesDict featuresDict = recoverDict(dict_file);
        return featuresDict.getValue(reviewId, HELP_N);
    }

    /**
     * Returns the denominator for the helpfulness of a given review * Returns -1 if there is no review with the given identifier
     */
    public int getReviewHelpfulnessDenominator(int reviewId) {
        FeaturesDict featuresDict = recoverDict(dict_file);
        return featuresDict.getValue(reviewId, HELP_D);
    }

    /**
     * Returns the number of tokens in a given review
     * Returns -1 if there is no review with the given identifier
     */
    public int getReviewLength(int reviewId) {
        FeaturesDict featuresDict = recoverDict(dict_file);
        return featuresDict.getValue(reviewId, REVIEW_LEN);
    }

    /**
     * Return the number of reviews containing a given token (i.e., word)
     * Returns 0 if there are no reviews containing this token
     */
    public int getTokenFrequency(String token) {
    }

    /**
     * Return the number of times that a given token (i.e., word) appears in
     * the reviews indexed
     * Returns 0 if there are no reviews containing this token
     */
    public int getTokenCollectionFrequency(String token) {
    }

    /**
     * The Posting List
     * Return a series of integers of the form id-1, freq-1, id-2, freq-2, ... such
     * that id-n is the n-th review containing the given token and freq-n is the
     * number of times that the token appears in review id-n
     * Note that the integers should be sorted by id
     * Returns an empty Enumeration if there are no reviews containing this token
     */
    public Enumeration<Integer> getReviewsWithToken(String token) {
    }

    /**
     * Return the number of product reviews available in the system
     */
    public int getNumberOfReviews() {
        TotalCounts tc = recover(totalCounts file);
        return tc.getTotalNumOfReviews();
    }

    /**
     * Return the number of tokens in the system
     * (Tokens should be counted as many times as they appear)
     */
    public int getTokenSizeOfReviews() {
        TotalCounts tc = recover(totalCounts file);
        return tc.getTotalNumOfTokens();
    }

    /**
     * Return the ids of the reviews for a given product identifier
     * Note that the integers returned should be sorted by id
     * Returns an empty Enumeration if there are no reviews for this product
     */
    public Enumeration<Integer> getProductReviews(String productId) {
        ProductDict pd = recover(product dict file);
        return pd.getReviewsIDs(productId);
    }


}
