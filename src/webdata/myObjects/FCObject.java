package webdata.myObjects;

public class FCObject {
    private int freq;
    private InvertedIndex invertedIndex;
    private int length;
    private int prefixSize;
    private int termPtr;

    public FCObject() {
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public InvertedIndex getInvertedIndex() {
        return invertedIndex;
    }

    public void setInvertedIndex(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrefixSize() {
        return prefixSize;
    }

    public void setPrefixSize(int prefixSize) {
        this.prefixSize = prefixSize;
    }

    public int getTermPtr() {
        return termPtr;
    }

    public void setTermPtr(int termPtr) {
        this.termPtr = termPtr;
    }
}
