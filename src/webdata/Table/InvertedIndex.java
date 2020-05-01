package webdata.Table;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class that compresses the posting list
 */
public class InvertedIndex {
    private String term;
    private ArrayList<Integer> postingList; // a list of document IDs
    private ArrayList<String> compressedPostingList;
    private

    public InvertedIndex(ArrayList<Integer> postingList) {
        this.postingList = postingList;
    }

    public ArrayList<Integer> getPostingList() {
        return postingList;
    }

    public void create(){
        sortPostingList();
        updatePostingListToGaps();
        compressList();
    }

    protected void sortPostingList(){
        Collections.sort(this.postingList);
    }
    protected void updatePostingListToGaps(){
        ArrayList<Integer> gaps = new ArrayList<>(this.postingList.size());
        gaps.set(0, this.postingList.get(0));
        for (int i = 1; i < this.postingList.size(); i++){
            Integer diff = this.postingList.get(i) - this.postingList.get(i - 1);
            gaps.set(i, diff);
        }
        this.postingList = gaps;
    }

    /**
     * compresses posting List
     */
    private void compressList(){


    }

    private void deltaEncode(){

    }

    private String gammaEncode(Integer num){
        String numInBinary = Integer.toBinaryString(num);
        return getGammaLengthInUnary(numInBinary) + getGammaOffset(numInBinary);
    }

    private String getGammaLengthInUnary(String num){
        int len = num.length();
        return
    }

    private String getGammaOffset(String num){

    }

    private String getUnary(String num){
        return "1".repeat(Math.max(0, num.length() - 1)) + "0";
    }


}
