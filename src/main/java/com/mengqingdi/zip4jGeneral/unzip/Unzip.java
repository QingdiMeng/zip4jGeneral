package com.mengqingdi.zip4jGeneral.unzip;

import com.mengqingdi.zip4jGeneral.exception.ZipException;
import com.mengqingdi.zip4jGeneral.model.CentralDirectory;
import com.mengqingdi.zip4jGeneral.model.FileHeader;
import com.mengqingdi.zip4jGeneral.model.UnzipParameters;
import com.mengqingdi.zip4jGeneral.model.ZipModel;

import java.util.ArrayList;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class Unzip {

    private ZipModel zipModel;

    public Unzip(ZipModel zipModel) throws ZipException {

        if (zipModel == null) {
            throw new ZipException("ZipModel is null");
        }

        this.zipModel = zipModel;
    }

    public void extractAll(final UnzipParameters unzipParameters) throws ZipException {

        CentralDirectory centralDirectory = zipModel.getCentralDirectory();

        if (centralDirectory == null ||
                centralDirectory.getFileHeaders() == null) {
            throw new ZipException("invalid central directory in zipModel");
        }

        final ArrayList fileHeaders = centralDirectory.getFileHeaders();

        initExtractAll(fileHeaders, unzipParameters);
    }

    private void initExtractAll(ArrayList fileHeaders, UnzipParameters unzipParameters) throws ZipException {

        for (int i = 0; i < fileHeaders.size(); i++) {
            FileHeader fileHeader = (FileHeader)fileHeaders.get(i);
            initExtractFile(fileHeader, unzipParameters);
        }
    }

    private void initExtractFile(FileHeader fileHeader, UnzipParameters unzipParameters) throws ZipException {

        if (fileHeader == null) {
            throw new ZipException("fileHeader is null");
        }

        try {

            if (fileHeader.isDirectory()) {
                System.out.println("Directory: " + fileHeader.getFileName());
            } else {
                System.out.println("File: " + fileHeader.getFileName());

                UnzipEngine unzipEngine = new UnzipEngine(zipModel, fileHeader);
                try {
                    unzipEngine.unzipFile(unzipParameters);
                } catch (Exception e) {
                    throw new ZipException(e);
                }
            }

        } catch (ZipException e) {
            throw e;
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

}
