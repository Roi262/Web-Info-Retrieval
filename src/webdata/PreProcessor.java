package webdata;

import webdata.WriteToDisk.FeaturesDict;
import webdata.WriteToDisk.TotalCounts;

import java.io.*;
import java.util.*;

import static webdata.Constants.PrefixConstants.*;

public class PreProcessor {

    /***************Constants***************/

    public static final int NUMERATOR = 0, DENOMINATOR = 1;

    /******************************/

    private ArrayList<String> terms;
    private BufferedReader reader;
    private File dataFile;
    private Map<String, PostingList> tokensDict;

    private FeaturesDict featuresDict;
    private TotalCounts totalCounts;

    /***************GETTERS***************/
    public FeaturesDict getFeaturesDict() {
        return featuresDict;
    }
    public Map<String, PostingList> getTokensDict() {
        return tokensDict;
    }

    /*********************************/


    public PreProcessor(String inputFilePath) throws FileNotFoundException {
        this.dataFile = new File(inputFilePath);
        this.reader = new BufferedReader(new FileReader(dataFile));
        this.featuresDict = new FeaturesDict();
        this.tokensDict = new HashMap<>();
        this.totalCounts = new TotalCounts();
    }

    public void preProcess() throws IOException {
        String line;
        String productID = null;
        Integer score = null;
        Integer helpNumerator = null, helpDenominator = null;
        Integer reviewLen = null;
        Integer reviewID = 0;

        while ((line = reader.readLine()) != null) {
            if (unnecessaryValue(line)) {
                continue;
            } else if (line.startsWith(PROD_ID_PREFIX)) {
                productID = handleProductID(line);
            } else if (line.startsWith(HELP_PREFIX)) {
                Integer[] help = handleHelpfulness(line);
                helpNumerator = help[NUMERATOR];
                helpDenominator = help[DENOMINATOR];
            }
//          make the score an integer string instead of float
            else if (line.startsWith(SCORE_PREFIX)) {
                score = handleScore(line);
            } else if (line.startsWith(TEXT_PREFIX)) {
                reviewLen = handleText(line, reviewID);
            } else { // i.e., end of review TODO check in debug
                featuresDict.add(productID, score, helpNumerator, helpDenominator, reviewLen);
                reviewID++;
            }
        }
        totalCounts.setTotalNumOfReviews(reviewID + 1);
        totalCounts.setTotalNumOfTokens(tokensDict.size());
    }

    /**
     * function to update Map<String, PostingList> tokensDict
     * example of an element: <term: PostingList>
     * So for each token in tokens
     * First - create PostingList
     * Second - update tokensDict dictionary
     * <p>
     * note that this function iterates over the text twice - bad effect on runtime!!
     * TODO if there is time try to minimize
     *
     * @param line a line of the text of the review
     * @return
     */
    private Integer handleText(String line, Integer reviewID) {
        line = line.replace(TEXT_PREFIX, "");
        line = cleanString(line);

        ArrayList<String> lineTokens = new ArrayList<>(getText(line));
        Map<String, Integer> tokensCount = new HashMap<>();

//        count number of occurences in review for each token
        for (String token : lineTokens) {
            Integer count = tokensCount.getOrDefault(token, 0);
            tokensCount.put(token, count + 1);
        }

        for (Map.Entry<String, Integer> entry : tokensCount.entrySet()) {
            String token = entry.getKey();
            if (token.equals("")) {
                continue;
            }
            PostingList postingList = tokensDict.getOrDefault(token, new PostingList());
            postingList.update(entry.getValue(), reviewID);
            tokensDict.put(token, postingList);
        }

        return lineTokens.size();
    }

    /**
     * @param line a cleaned text line
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
