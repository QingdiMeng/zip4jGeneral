package com.mengqingdi.zip4jGeneral.model;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class ExtraDataRecord {

    private long header;

    private int sizeOfData;

    private byte[] data;

    public long getHeader() {
        return header;
    }

    public void setHeader(long header) {
        this.header = header;
    }

    public int getSizeOfData() {
        return sizeOfData;
    }

    public void setSizeOfData(int sizeOfData) {
        this.sizeOfData = sizeOfData;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
