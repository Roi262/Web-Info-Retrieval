package webdata.Table;

public class ConcatenatedString {
    private StringBuilder stringBuilder;
    private String concatStr;

    protected void update(String term){
        this.stringBuilder.append(term);
    }

    public String getConcatStr() {
        this.concatStr = this.stringBuilder.toString();
        return concatStr;
    }
}
