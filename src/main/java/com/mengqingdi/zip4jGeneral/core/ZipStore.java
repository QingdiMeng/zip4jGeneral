package com.mengqingdi.zip4jGeneral.core;

import com.mengqingdi.zip4jGeneral.exception.ZipException;
import com.mengqingdi.zip4jGeneral.exception.ZipExceptionConstants;
import com.mengqingdi.zip4jGeneral.model.UnzipParameters;
import com.mengqingdi.zip4jGeneral.model.ZipModel;
import com.mengqingdi.zip4jGeneral.unzip.Unzip;
import com.mengqingdi.zip4jGeneral.util.InternalZipConstants;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.InflaterInputStream;


/**
 * Created by mengqingdi on 17-1-2.
 */
public class ZipStore {

    private Map<String, byte[]> rawStore;
    private String file;
    private int mode;
    private ZipModel zipModel;
    private boolean isEncrypted;
    private String fileNameCharset;

    public ZipStore(String zipFile, Map<String, byte[]> rawStore) throws ZipException {
        if (zipFile == null) {
            throw new ZipException("Input zip file parameter is not null",
                    ZipExceptionConstants.inputZipParamIsNull);
        }

        this.file = zipFile;
        this.mode = InternalZipConstants.MODE_UNZIP;
        this.rawStore = rawStore;
    }

    public void extractAll() throws ZipException {
        extractAll(null);
    }

    public void extractAll(UnzipParameters unzipParameters) throws ZipException {
        if (zipModel == null) {
            readZipInfo();
        }

        if (zipModel == null) {
            throw new ZipException("Internal error occurred when extracting zip file");
        }

        Unzip unzip = new Unzip(zipModel);
        unzip.extractAll(unzipParameters);
    }

    private void readZipInfo() throws ZipException {
        if (this.mode != InternalZipConstants.MODE_UNZIP) {
            throw new ZipException("Invalid mode");
        }

        DataInputStream dis = null;

        dis = new DataInputStream(new ByteArrayInputStream(rawStore.get(file)));

        if (zipModel == null) {

            HeaderReader headerReader = new HeaderReader(dis);
            zipModel = headerReader.readAllHeaders(this.fileNameCharset);
            if (zipModel != null) {
                zipModel.setZipFile(file);
                zipModel.setRawStore(rawStore);
            }
        }

        try {
            dis.close();
        } catch (IOException e) {
            //ignore
        }
    }

}
