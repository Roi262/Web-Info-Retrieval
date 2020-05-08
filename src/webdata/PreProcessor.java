package webdata;

import webdata.Objects.FeaturesDict;
import webdata.Table.TermsObject;

import java.io.*;
import java.util.*;


public class PreProcessor {

/***************Constants***************/

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

/******************************/

    private ArrayList<String> terms;
    private ArrayList<String> stringDocs;
    private BufferedReader reader;
    private File dataFile;
    private FeaturesDict featuresDict;
    private Map<String, TermsObject> tokensDict;

    //    private currFeatureObj currFeatureObj;
    private int totalNumOfReviews;
    private int totalNumOfTokens;

/***************GETTERS***************/
    public FeaturesDict getFeaturesDict() {
        return featuresDict;
    }

    public Map<String, TermsObject> getTokensDict() {
        return tokensDict;
    }
/*********************************/


    public PreProcessor(String inputFilePath) throws FileNotFoundException {
        this.dataFile = new File(inputFilePath);
        this.stringDocs = new ArrayList<>();
        this.reader = new BufferedReader(new FileReader(dataFile));
        this.featuresDict = new FeaturesDict();
        this.tokensDict = new HashMap<>();
    }

    public void preProcess() throws IOException {
        String line;
        String productID = null;
        Integer score = null;
        Integer helpNumerator = null;
        Integer helpDenominator = null;
        Integer reviewLen = null;
        Integer reviewID = 0;

        while ((line = reader.readLine()) != null) {
            if (unnecessaryValue(line)) {
                continue;
            }
            else if (line.startsWith(PROD_ID_PREFIX)) {
                productID = handleProductID(line);
            }
            else if (line.startsWith(HELP_PREFIX)) {
                Integer[] help = handleHelpfulness(line);
                helpNumerator = help[NUMERATOR];
                helpDenominator = help[DENOMINATOR];
            }
//          make the score an integer string instead of float
            else if (line.startsWith(SCORE_PREFIX)) {
                score = handleScore(line);
            }
            else if (line.startsWith(TEXT_PREFIX)) {
                reviewLen = handleText(line, reviewID);
            } else { // i.e., end of review TODO check in debug
                featuresDict.add(reviewID, productID, score, helpNumerator, helpDenominator, reviewLen);
                reviewID++;
            }
        }
    }

    /**
     * function to update Map<String, TermsObject> tokensDict
     * example of an element: <term: TermObject>
     * So for each token in tokens
     * First - create TermObject
     * Second - update tokensDict dictionary
     *
     * note that this function iterates over the text twice - bad effect on runtime!!
     * TODO if there is time try to minimize
     * @param line a line of the text of the review
     * @return
     */
    private Integer handleText(String line, Integer reviewID) {
        line = line.replace(TEXT_PREFIX, "");
        line = cleanString(line);

        ArrayList<String> tokens = new ArrayList<>(getText(line));
        Map<String, Integer> tokensCount = new HashMap<>();


        for (String token : tokens) {
            Integer count = tokensCount.getOrDefault(token, 0);
            tokensCount.put(token, count + 1);
        }

        for (Map.Entry<String, Integer> entry : tokensCount.entrySet()) {
            String token = entry.getKey();
            if(token.equals("")){
                continue;
            }
            TermsObject termsObject = tokensDict.getOrDefault(token, new TermsObject());
            termsObject.update(entry.getValue(), reviewID);
            tokensDict.put(token, termsObject);
        }

        return tokens.size();
    }

    /**
     * @param line   a cleaned text line
     * @return sorted list of all terms
     */
    private List<String> getText(String line) {
        List<String> tokens = Arrays.asList(line.split(" "));
        return tokens;
    }

    public static String cleanString(String str) {
        str = str.replaceAll("\\W", " ");
        str = str.toLowerCase();
        return str;
    }


    private Integer handleScore(String line) {
        line = line.replace(SCORE_PREFIX, "");
        line = line.replace(".0", "");
        return Integer.parseInt(line);
    }

    /**
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

        return line.replace(REVIEW_ID_PREFIX, "");
    }

    private String handleProductID(String line) {
        return line.replace(PROD_ID_PREFIX, "");
    }


    private Boolean unnecessaryValue(String line) {
        return line.startsWith(PROFILE_NAME_PREFIX) | line.startsWith(TIME_PREFIX) |
                line.startsWith(SUMMARY_PREFIX) | line.startsWith(REVIEW_ID_PREFIX);
    }

}
