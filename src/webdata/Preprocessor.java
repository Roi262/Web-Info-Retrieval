package webdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Preprocessor {
    public static final String DOC_PREFIX = "product/productId:";

    private String cleanString(String str){
//      TODO remove the .0 in 5.0 (e.g) in score feature
        str = str.replaceAll("\\W", " ");
        str = str.toLowerCase();
        return str;
    }
    /**
     * Receives path to valid input file consisting of data.
     * Manages preprocessing of the file.
     * @param inputFilePath  - path to input data file
     * @return a list of strings, where each string represents one document.
     * @throws Exception readline
     */
    public ArrayList<String> preprocess(String inputFilePath) throws Exception {
        ArrayList<String> stringDocs = new ArrayList<>();
        File dataFile = new File(inputFilePath);
        BufferedReader br = new BufferedReader(new FileReader(dataFile));
        String line;
        String review = "";
        while ((line = br.readLine()) != null) {
            if (line.startsWith(DOC_PREFIX) & !review.equals("")) {
                review = cleanString(review);
                stringDocs.add(review);
            }
            else{
                review += line;
            }
        }
        return stringDocs;


//

    }

}
