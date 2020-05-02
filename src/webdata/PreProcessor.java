package webdata;

import webdata.Objects.FeaturesDict;
import webdata.Objects.FeaturesObject;

import java.io.*;
import java.util.ArrayList;


public class PreProcessor {
    public static final String PROD_ID_PREFIX = "product/productId: ";
    public static final String REVIEW_ID_PREFIX = "review/userId: ";
    public static final String PROFILE_NAME_PREFIX = "review/profileName: ";
    public static final String HELP_PREFIX = "review/helpfulness: ";
    public static final String SCORE_PREFIX = "review/score: ";
    public static final String TIME_PREFIX = "review/time: ";
    public static final String SUMMARY_PREFIX = "review/summary: ";
    public static final String TEXT_PREFIX = "review/text: ";
    public static final int NUMERATOR = 0;
    public static final int DENOMINATOR = 1;

    private ArrayList<String> terms;
    private ArrayList<String> stringDocs;
    private BufferedReader reader;
    private File dataFile;
    private FeaturesDict featuresDict;
//    private currFeatureObj currFeatureObj;
    private int totalNumOfReviews;
    private int totalNumOfTokens;


    public PreProcessor(String inputFilePath) throws FileNotFoundException {
        this.dataFile = new File(inputFilePath);
        this.stringDocs = new ArrayList<>();
        this.reader = new BufferedReader(new FileReader(dataFile));
        this.featuresDict = new FeaturesDict();
    }

    public void preProcess() throws IOException {
        String line;
        String productID, reviewID;
        Integer score, helpNumerator, helpDenominator, reviewLen;
        while ((line = reader.readLine()) != null){
            if (unnecessaryValue(line)) {
                continue;
            }
            if (line.startsWith(PROD_ID_PREFIX){
                productID = handleProductID(line);
            }
            if (line.startsWith(REVIEW_ID_PREFIX)){
                reviewID = handleReviewID(line);
            }
            if (line.startsWith(HELP_PREFIX)){
                Integer[] help = handleHelpfulness(line);
                helpNumerator = help[NUMERATOR];
                helpDenominator = help[DENOMINATOR];
            }
//          make the score an integer string instead of float
            if (line.startsWith(SCORE_PREFIX)) {
                score = handleScore(line);
            }
            if (line.startsWith(TEXT_PREFIX)){
                handleText(line);
            }
            else{ // i.e., end of review
                featuresDict.add(reviewID, productID, score, helpNumerator, helpDenominator, reviewLen);
            }
        }
    }

    private void handleText(String line){
        line = line.replace(TEXT_PREFIX, "");



    }

    private Integer handleScore(String line) {
        line = line.replace(SCORE_PREFIX, "");
        line = line.replace(".0", "");
        return Integer.parseInt(line);
    }

    /**
     *
     * @param line
     * @return helpfullness values
     */
    private Integer[] handleHelpfulness(String line) {
        Integer[] help = new Integer[2];
        line = line.replace(HELP_PREFIX, "");
        line = line.replace("/", "");
        help[NUMERATOR] = Integer.parseInt(String.valueOf(line.charAt(NUMERATOR)));
        help[DENOMINATOR] = Integer.parseInt(String.valueOf(line.charAt(DENOMINATOR)));
        return help;
    }

    private String handleReviewID(String line) {
        String reviewID = line.replace(REVIEW_ID_PREFIX, "");

        return reviewID;
    }

    private String handleProductID(String line) {
        return line.replace(PROD_ID_PREFIX, "");
    }


    private Boolean unnecessaryValue(String line) {
        return line.startsWith(PROFILE_NAME_PREFIX) | line.startsWith(TIME_PREFIX) |
                line.startsWith(SUMMARY_PREFIX);
    }


    public static String cleanString(String str) {
        str = str.replaceAll("\\W", " ");
        str = str.toLowerCase();
        return str;
    }
}
