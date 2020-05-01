package webdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;


public class Preprocessor {
    public static final String DOC_PREFIX = "product/productId:";
    public static final String USERID_PREFIX = "review/userId:";
    public static final String PROFILE_NAME_PREFIX = "review/profileName:";
    public static final String HELP_PREFIX = "review/helpfulness:";
    public static final String SCORE_PREFIX = "review/score:";
    public static final String TIME_PREFIX = "review/time:";
    public static final String SUMMARY_PREFIX = "review/summary:";
    public static final String TEXT_PREFIX = "review/text:";

    private int totalNumOfReviews;
    private int totalNumOfTokens;


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
    public Map<String, ArrayList<String>> preprocess(String inputFilePath) throws Exception {
//        TODO add all that is needed to the dictionaries!!!
        ArrayList<String> stringDocs = new ArrayList<>();
        ArrayList<String> terms = new ArrayList<>();
        File dataFile = new File(inputFilePath);
        BufferedReader br = new BufferedReader(new FileReader(dataFile));
        String line;
        String review = "";
        while ((line = br.readLine()) != null) {
            if (unnecessaryValue(line)) {
                continue;
            }
            if (line.startsWith(TEXT_PREFIX)){
                terms.addAll(getText(line, TEXT_PREFIX));
            }
//          make the score an integer string instead of float
            if (line.startsWith(SCORE_PREFIX)) {
                line = line.replace(".0", "");
            }
            if (line.startsWith(DOC_PREFIX) & !review.equals("")) {
//                stringDocs.add(cleanString(review));
                review = line;
            } else {
                review += " " + line;
            }
        }
        Map<String, ArrayList<String>> reviewContents = new HashMap<>();
        reviewContents.put("Reviews as strings", stringDocs);
        reviewContents.put("Terms", terms);
        return reviewContents;
    }

//preprocess the terms of the reviews saves them as a sorted list of all the text in all the reviews
//    public ArrayList<String> preprocessText(){
//
//    }

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
