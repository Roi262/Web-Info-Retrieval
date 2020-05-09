package webdata;

import java.io.*;
import java.util.ArrayList;


/**
 * Class to serialize and write objects to Disk memory
 */
public class Serializer {
    Serializer(){

    }

    public void WriteObjectToFile(Object serObj, String filepath) {

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


    public void recoverDictionaryRecordsFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File(_dictionaryRecordsFilePath));
        ObjectInputStream oi = new ObjectInputStream(fi);
        _blocksList = (ArrayList<IDictionaryBlock>)oi.readObject();
    }


}



