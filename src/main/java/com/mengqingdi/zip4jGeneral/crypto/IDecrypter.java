package com.mengqingdi.zip4jGeneral.crypto;

import com.mengqingdi.zip4jGeneral.exception.ZipException;

/**
 * Created by mengqingdi on 17-1-2.
 */
public interface IDecrypter {

    public int decryptData(byte[] buff, int start, int len) throws ZipException;

    public int decryptData(byte[] buff) throws ZipException;
}
