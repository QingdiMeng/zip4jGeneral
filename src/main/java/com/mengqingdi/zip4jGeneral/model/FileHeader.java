package com.mengqingdi.zip4jGeneral.model;

import java.util.ArrayList;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class FileHeader {

    private int signature;

    private int versionMadeBy;

    private int versionNeededToExtract;

    private byte[] generalPurposeFlag;

    private int compressionMethod;

    private int lastModFileTime;

    private long crc32;

    private byte[] crcBuff;

    private long compressedSize;

    private long uncompressedSize;

    private int fileNameLength;

    private int extraFieldLength;

    private int fileCommentLength;

    private int diskNumberStart;

    private byte[] internalFileAttr;

    private byte[] externalFileAttr;

    private long offsetLocalHeader;

    private String fileName;

    private String fileComment;

    private boolean isDirectory;

    private boolean isEncrypted;

    private int encryptionMethod;

    private char[] password;

    private boolean dataDescriptorExists;

    private Zip64ExtendedInfo zip64ExtendedInfo;

    private AESExtraDataRecord aesExtraDataRecord;

    private ArrayList extraDataRecords;

    private boolean fileNameUTF8Encoded;

    public FileHeader() {
        encryptionMethod = -1;
        crc32 = 0;
        uncompressedSize = 0;
    }

    public int getSignature() {
        return signature;
    }

    public void setSignature(int signature) {
        this.signature = signature;
    }

    public int getVersionMadeBy() {
        return versionMadeBy;
    }

    public void setVersionMadeBy(int versionMadeBy) {
        this.versionMadeBy = versionMadeBy;
    }

    public int getVersionNeededToExtract() {
        return versionNeededToExtract;
    }

    public void setVersionNeededToExtract(int versionNeededToExtract) {
        this.versionNeededToExtract = versionNeededToExtract;
    }

    public byte[] getGeneralPurposeFlag() {
        return generalPurposeFlag;
    }

    public void setGeneralPurposeFlag(byte[] generalPurposeFlag) {
        this.generalPurposeFlag = generalPurposeFlag;
    }

    public int getCompressionMethod() {
        return compressionMethod;
    }

    public void setCompressionMethod(int compressionMethod) {
        this.compressionMethod = compressionMethod;
    }

    public int getLastModFileTime() {
        return lastModFileTime;
    }

    public void setLastModFileTime(int lastModFileTime) {
        this.lastModFileTime = lastModFileTime;
    }

    public long getCrc32() {
        return crc32;
    }

    public void setCrc32(long crc32) {
        this.crc32 = crc32;
    }

    public byte[] getCrcBuff() {
        return crcBuff;
    }

    public void setCrcBuff(byte[] crcBuff) {
        this.crcBuff = crcBuff;
    }

    public long getCompressedSize() {
        return compressedSize;
    }

    public void setCompressedSize(long compressedSize) {
        this.compressedSize = compressedSize;
    }

    public long getUncompressedSize() {
        return uncompressedSize;
    }

    public void setUncompressedSize(long uncompressedSize) {
        this.uncompressedSize = uncompressedSize;
    }

    public int getFileNameLength() {
        return fileNameLength;
    }

    public void setFileNameLength(int fileNameLength) {
        this.fileNameLength = fileNameLength;
    }

    public int getExtraFieldLength() {
        return extraFieldLength;
    }

    public void setExtraFieldLength(int extraFieldLength) {
        this.extraFieldLength = extraFieldLength;
    }

    public int getFileCommentLength() {
        return fileCommentLength;
    }

    public void setFileCommentLength(int fileCommentLength) {
        this.fileCommentLength = fileCommentLength;
    }

    public int getDiskNumberStart() {
        return diskNumberStart;
    }

    public void setDiskNumberStart(int diskNumberStart) {
        this.diskNumberStart = diskNumberStart;
    }

    public byte[] getInternalFileAttr() {
        return internalFileAttr;
    }

    public void setInternalFileAttr(byte[] internalFileAttr) {
        this.internalFileAttr = internalFileAttr;
    }

    public byte[] getExternalFileAttr() {
        return externalFileAttr;
    }

    public void setExternalFileAttr(byte[] externalFileAttr) {
        this.externalFileAttr = externalFileAttr;
    }

    public long getOffsetLocalHeader() {
        return offsetLocalHeader;
    }

    public void setOffsetLocalHeader(long offsetLocalHeader) {
        this.offsetLocalHeader = offsetLocalHeader;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileComment() {
        return fileComment;
    }

    public void setFileComment(String fileComment) {
        this.fileComment = fileComment;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    public int getEncryptionMethod() {
        return encryptionMethod;
    }

    public void setEncryptionMethod(int encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public boolean isDataDescriptorExists() {
        return dataDescriptorExists;
    }

    public void setDataDescriptorExists(boolean dataDescriptorExists) {
        this.dataDescriptorExists = dataDescriptorExists;
    }

    public Zip64ExtendedInfo getZip64ExtendedInfo() {
        return zip64ExtendedInfo;
    }

    public void setZip64ExtendedInfo(Zip64ExtendedInfo zip64ExtendedInfo) {
        this.zip64ExtendedInfo = zip64ExtendedInfo;
    }

    public AESExtraDataRecord getAesExtraDataRecord() {
        return aesExtraDataRecord;
    }

    public void setAesExtraDataRecord(AESExtraDataRecord aesExtraDataRecord) {
        this.aesExtraDataRecord = aesExtraDataRecord;
    }

    public ArrayList getExtraDataRecords() {
        return extraDataRecords;
    }

    public void setExtraDataRecords(ArrayList extraDataRecords) {
        this.extraDataRecords = extraDataRecords;
    }

    public boolean isFileNameUTF8Encoded() {
        return fileNameUTF8Encoded;
    }

    public void setFileNameUTF8Encoded(boolean fileNameUTF8Encoded) {
        this.fileNameUTF8Encoded = fileNameUTF8Encoded;
    }
}
