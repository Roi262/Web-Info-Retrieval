package webdata;

import java.util.ArrayList;

class DeltaCompressor {

//    protected Integer deltaDecode(String num){
//    }

    /**
     * compresses posting List
     */
    static String compressList(ArrayList<Integer> list){
        StringBuilder str = new StringBuilder();
        for (Integer integer : list) {
            str.append(deltaEncode(integer));
        }
        return str.toString();
    }

    /**
     * @param num
     * @return binary string of encoded delta value of num
     */
    static String deltaEncode(Integer num){
        String numInBinary = Integer.toBinaryString(num);
        int len = numInBinary.length();
        return gammaEncode(len) + getOffset(numInBinary);
    }

    static String gammaEncode(Integer num){
        String numInBinary = Integer.toBinaryString(num);
        return getGammaLengthInUnary(numInBinary) + getOffset(numInBinary);
    }

    static String getGammaLengthInUnary(String num){
        int len = num.length();
        return getUnary(len);
    }

    static String getOffset(String num){
        return num.substring(1);
    }

    static String getUnary(int num){
        return "1".repeat(Math.max(0, num - 1)) + "0";
    }
}
