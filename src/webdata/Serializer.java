package webdata;

import webdata.Objects.FeaturesDict;
import webdata.Objects.ProductDict;
import webdata.Table.FCTable;
import java.io.Serializable;



/**
 * Class to serialize and write objects to Disk memory
 */
public class Serializer implements Serializable {
    Serializer(){

    }

    public serializeTotalNumOfReviews(int totalNumOfReviews){

    }

    public serializeTotalNumOfTokens(int totalNumOfTokens){

    }

    public serializeProductDict(ProductDict dict){

    }


    public serializeFeatureDict(FeaturesDict dict){

    }


    public serializeTermsDict(FCTable dict){

    }
}



    public void recoverDictionaryRecordsFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File(_dictionaryRecordsFilePath));
        ObjectInputStream oi = new ObjectInputStream(fi);
        _blocksList = (ArrayList<IDictionaryBlock>)oi.readObject();
    }