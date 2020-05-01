package webdata.Table;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class that compresses the posting list
 */
public class InvertedIndex {
    private String term;
    private ArrayList<Integer> postingList; // a list of document IDs
    private String deltaCompressedPL;

    public InvertedIndex(ArrayList<Integer> postingList) {
        this.postingList = postingList;
    }

    public String getDeltaCompressedPL() {
        return deltaCompressedPL;
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
        StringBuilder str = new StringBuilder();
        for (Integer integer : postingList) {
            str.append(deltaEncode(integer));
        }
        this.deltaCompressedPL = str.toString();
    }

    private String deltaEncode(Integer num){
        String numInBinary = Integer.toBinaryString(num);
        int len = numInBinary.length();
        return gammaEncode(len) + getOffset(numInBinary);
    }

    private String gammaEncode(Integer num){
        String numInBinary = Integer.toBinaryString(num);
        return getGammaLengthInUnary(numInBinary) + getOffset(numInBinary);
    }

    private String getGammaLengthInUnary(String num){
        int len = num.length();
        return getUnary(len);
    }

    private String getOffset(String num){
        return num.substring(1);
    }

    private String getUnary(int num){
        return "1".repeat(Math.max(0, num - 1)) + "0";
    }


}
