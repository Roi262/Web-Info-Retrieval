package webdata;
import webdata.Table.FCTable;

import java.io.File;
import java.util.ArrayList;

public class SlowIndexWriter {



    /**
     * function in order to test my program
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Slow Index Writer");
        String inputFilePath = "Small Datasets/100.txt";
        String dir = "Data Index/";
        int k = 4; // the front coding factor TODO play around with
        PreProcessor proc = new PreProcessor(inputFilePath);
        proc.preProcess();
        FCTable table = new FCTable(proc.getTokensDict(), k);
        table.create();
//        now i have a table and a concatenated string



//        SlowIndexWriter writer = new SlowIndexWriter();
//        writer.slowWrite(inputFilePath, dir);

    }

    public writeDictionaries(FCTable table){


    }


    /**
     * Given product review data, creates an on disk index
     * inputFile is the path to the file containing the review data
     * dir is the directory in which all index files will be created
     * if the directory does not exist, it should be created
     */
    /**
     * Delete all index files by removing the given directory
     */
    public void removeIndex(String dir) {

    }

    /**
     * creates a directory with the given path
     * @param dir path to directory to be created
     */
//    private File createDirectory(String dir) {
//    }
    public void slowWrite(String inputFile, String dir) {

    }


    public void WriteObjectToFile(Object serObj) {

        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
