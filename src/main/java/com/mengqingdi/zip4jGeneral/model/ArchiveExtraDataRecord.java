package com.mengqingdi.zip4jGeneral.model;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class ArchiveExtraDataRecord {

    private int signature;

    private int extraFieldLength;

    private String extraFieldData;

    public int getSignature() {
        return signature;
    }

    public void setSignature(int signature) {
        this.signature = signature;
    }

    public int getExtraFieldLength() {
        return extraFieldLength;
    }

    public void setExtraFieldLength(int extraFieldLength) {
        this.extraFieldLength = extraFieldLength;
    }

    public String getExtraFieldData() {
        return extraFieldData;
    }

    public void setExtraFieldData(String extraFieldData) {
        this.extraFieldData = extraFieldData;
    }
}
