package com.mengqingdi.zip4jGeneral.io;

import com.mengqingdi.zip4jGeneral.crypto.IDecrypter;
import com.mengqingdi.zip4jGeneral.unzip.UnzipEngine;
import com.mengqingdi.zip4jGeneral.util.Zip4jConstants;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class PartInputStream extends BaseInputStream {

    private DataInputStream dis;
    private long bytesRead, length;
    private UnzipEngine unzipEngine;
    private IDecrypter decrypter;
    private byte[] oneByteBuff = new byte[1];
    private byte[] aesBlockByte = new byte[16];
    private int aesBytesReturned = 0;
    private boolean isAESEncryptedFile = false;
    private int count = -1;

    public PartInputStream(DataInputStream dis, long start, long len, UnzipEngine unzipEngine) {
        this.dis = dis;
        this.unzipEngine = unzipEngine;
        this.decrypter = unzipEngine.getDecrypter();
        this.bytesRead = 0;
        this.length = len;
        this.isAESEncryptedFile = unzipEngine.getFileHeader().isEncrypted() &&
                unzipEngine.getFileHeader().getEncryptionMethod() == Zip4jConstants.ENC_METHOD_AES;
    }

    public int available() {
        long amount = length - bytesRead;
        if (amount > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return (int) amount;
    }

    public int read() throws IOException {
        if (bytesRead >= length)
            return -1;

        if (isAESEncryptedFile) {
            if (aesBytesReturned == 0 || aesBytesReturned == 16) {
                if (read(aesBlockByte) == -1) {
                    return -1;
                }
                aesBytesReturned = 0;
            }
            return aesBlockByte[aesBytesReturned++] & 0xff;
        } else {
            return read(oneByteBuff, 0, 1) == -1 ? -1 : oneByteBuff[0] & 0xff;
        }
    }

    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }
}
