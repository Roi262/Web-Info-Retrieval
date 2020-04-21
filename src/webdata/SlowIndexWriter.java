package webdata;
import java.io.File;

public class SlowIndexWriter {

    /**
     * creates a directory with the given path
     * @param dir path to directory to be created
     */
    private File createDirectory(String dir) {
    }

    /**
     * Given product review data, creates an on disk index
     * inputFile is the path to the file containing the review data
     * dir is the directory in which all index files will be created
     * if the directory does not exist, it should be created
     */
    public void slowWrite(String inputFile, String dir) {
        File indexDir = new File(dir);
        if (!indexDir.isDirectory()) {
            indexDir = createDirectory(dir);
        }

    }

    /**
     * Delete all index files by removing the given directory
     */
    public void removeIndex(String dir) {
    }

    /**
     * function in order to test my program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Starting Slow Index Writer");
        String inputFile = "Small Datasets/100.txt";
        String dir = "Data Index/";
        SlowIndexWriter writer = new SlowIndexWriter();
        writer.slowWrite(inputFile, dir);

    }
}
