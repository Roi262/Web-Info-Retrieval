package webdata;

public class Constants {


    protected static final int PROD_ID = 0, SCORE = 1, HELP_N = 2, HELP_D = 3, REVIEW_LEN = 4;
    protected static final String SEPARATOR = "#";

    public static class PrefixConstants {
        public static final String PROD_ID_PREFIX = "product/productId: ";
        public static final String REVIEW_ID_PREFIX = "review/userId: ";
        public static final String PROFILE_NAME_PREFIX = "review/profileName: ";
        public static final String HELP_PREFIX = "review/helpfulness: ";
        public static final String TIME_PREFIX = "review/time: ";
        public static final String SCORE_PREFIX = "review/score: ";
        public static final String SUMMARY_PREFIX = "review/summary: ";
        public static final String TEXT_PREFIX = "review/text: ";
    }

    enum FCTableColumnValues {
        FREQ
    }


}
