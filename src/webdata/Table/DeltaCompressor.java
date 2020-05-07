package webdata.Table;

import java.util.ArrayList;

public class DeltaCompressor {
    private String deltaCompressedPL;


    public DeltaCompressor() {
    }

    public String getDeltaCompressedPL() {
        return deltaCompressedPL;
    }

//    protected Integer deltaDecode(String num){
//    }


    /**
     * compresses posting List
     */
    protected void compressList(ArrayList<Integer> postingList){
        StringBuilder str = new StringBuilder();
        for (Integer integer : postingList) {
            str.append(deltaEncode(integer));
        }
        this.deltaCompressedPL = str.toString();
    }

    /**
     * @param num
     * @return binary string of encoded delta value of num
     */
    protected String deltaEncode(Integer num){
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
