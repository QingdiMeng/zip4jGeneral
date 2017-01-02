package com.mengqingdi.zip4jGeneral.model;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class DigitalSignature {

    private int headerSignature;

    private int sizeOfData;

    private String signatureData;

    public int getHeaderSignature() {
        return headerSignature;
    }

    public void setHeaderSignature(int headerSignature) {
        this.headerSignature = headerSignature;
    }

    public int getSizeOfData() {
        return sizeOfData;
    }

    public void setSizeOfData(int sizeOfData) {
        this.sizeOfData = sizeOfData;
    }

    public String getSignatureData() {
        return signatureData;
    }

    public void setSignatureData(String signatureData) {
        this.signatureData = signatureData;
    }
}
