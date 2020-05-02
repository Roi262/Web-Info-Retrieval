package webdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class PreprocessorOLD {
    public static final String PROD_ID_PREFIX = "product/productId:";
    public static final String REVIEW_ID_PREFIX = "review/userId:";
    public static final String PROFILE_NAME_PREFIX = "review/profileName:";
    public static final String HELP_PREFIX = "review/helpfulness:";
    public static final String SCORE_PREFIX = "review/score:";
    public static final String TIME_PREFIX = "review/time:";
    public static final String SUMMARY_PREFIX = "review/summary:";
    public static final String TEXT_PREFIX = "review/text:";

    private ArrayList<String> terms;
    private ArrayList<String> stringDocs;
    private BufferedReader reader;
    private File dataFile;
    private int totalNumOfReviews;
    private int totalNumOfTokens;


    public PreprocessorOLD(String inputFilePath) throws FileNotFoundException {
        this.dataFile = new File(inputFilePath);
        this.stringDocs = new ArrayList<>();
        this.reader = new BufferedReader(new FileReader(dataFile));
    }

    private String cleanString(String str) {
        str = str.replaceAll("\\W", " ");
        str = str.toLowerCase();
        return str;
    }

    /**
     * Receives path to valid input file consisting of data.
     * Manages preprocessing of the file.
     *
     * @param inputFilePath - path to input data file
     * @return a list of strings, where each string represents one document.
     * @throws Exception readline
     */
    public Map<String, ArrayList<String>> preprocess() throws Exception {
//        TODO add all that is needed to the dictionaries!!!
        String line;
        String review = "";
        while ((line = reader.readLine()) != null) {
            if (unnecessaryValue(line)) {
                continue;
            }
            if (line.startsWith(PROD_ID_PREFIX) & !review.equals("")) {
//                stringDocs.add(cleanString(review));
                review = line;
            }
            if (line.startsWith(REVIEW_ID_PREFIX)){
                handleReviewID(line);
            }
            if (line.startsWith(HELP_PREFIX)){
                handleHelpfulness(line);
            }
//          make the score an integer string instead of float
            if (line.startsWith(SCORE_PREFIX)) {
                line = handleScore(line);
            }
            if (line.startsWith(TEXT_PREFIX)){
                handleText(line);
            }
            else {
                review += " " + line;
            }
        }
        Map<String, ArrayList<String>> reviewContents = new HashMap<>();
        reviewContents.put("Reviews as strings", stringDocs);
        reviewContents.put("Terms", terms);
        return reviewContents;
    }

    private String handleScore(String line){
//        todo add score to features dict
        return line.replace(".0", "");

    }

    private void handleText(String line){
//        todo more todo here
        List<String> words = getText(line, TEXT_PREFIX);
        terms.addAll(words);




    }
    /**
     *
     * @param line
     * @param prefix
     * @return sorted list of all terms
     */
    private List<String> getText(String line, String prefix){
        String text = line.replace(prefix, "");
        return Arrays.asList(text.split(" "));
    }

    private Boolean unnecessaryValue(String line) {
        return line.startsWith(PROFILE_NAME_PREFIX) | line.startsWith(TIME_PREFIX) |
                line.startsWith(SUMMARY_PREFIX);
    }

}
