package webdata.Table;

import java.util.ArrayList;
import java.util.Collections;

public class ConcatenatedStringOLD {
    private String concatString;
    private Boolean KinKFC;
    private ArrayList<String> terms;

    ConcatenatedStringOLD(Integer k){
        this.terms = new ArrayList<>();
        this.concatString = "";
        this.KinKFC = k != null;
    }

    /**
     * Sorts the terms in the terms arraylist
     */
    private void sortTerms(){
        Collections.sort(this.terms);
    }

    /**
     * Concatenate all the sorted terms into one string concatString
     */
    private void concatTerms(){
        StringBuilder str = new StringBuilder();
        for(String term: this.terms){
            str.append(term);
        }
        this.concatString = str.toString();
    }

    /**
     * run k-1 in k front encoding on long sorted terms
     */
    private void doBlockedFC(){


    }

    public String getConcatenatedString(){
        sortTerms();
        if(this.KinKFC){
            doBlockedFC();
        }
        concatTerms();
        return this.concatString;
    }
}
