package com.mengqingdi.zip4jGeneral.model;

import java.util.ArrayList;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class CentralDirectory {

    private ArrayList fileHeaders;

    private DigitalSignature digitalSignature;

    public ArrayList getFileHeaders() {
        return fileHeaders;
    }

    public void setFileHeaders(ArrayList fileHeaders) {
        this.fileHeaders = fileHeaders;
    }

    public DigitalSignature getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(DigitalSignature digitalSignature) {
        this.digitalSignature = digitalSignature;
    }
}
