package webdata.WriteToDisk.Table;

import webdata.DeltaCompressor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * A serializeable middle row
 */
public class SerializableMidRow implements Row {
    private int freq;
    private String compressedBinaryStringPostingList;
    private int length;
    private int prefixSize;

    public SerializableMidRow(){
    }




}
