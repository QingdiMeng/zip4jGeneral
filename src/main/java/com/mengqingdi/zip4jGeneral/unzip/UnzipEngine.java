package com.mengqingdi.zip4jGeneral.unzip;

import com.mengqingdi.zip4jGeneral.core.HeaderReader;
import com.mengqingdi.zip4jGeneral.crypto.IDecrypter;
import com.mengqingdi.zip4jGeneral.exception.ZipException;
import com.mengqingdi.zip4jGeneral.model.FileHeader;
import com.mengqingdi.zip4jGeneral.model.LocalFileHeader;
import com.mengqingdi.zip4jGeneral.model.UnzipParameters;
import com.mengqingdi.zip4jGeneral.model.ZipModel;
import com.mengqingdi.zip4jGeneral.util.InternalZipConstants;
import com.mengqingdi.zip4jGeneral.util.Raw;
import com.mengqingdi.zip4jGeneral.util.Zip4jConstants;
import com.mengqingdi.zip4jGeneral.util.Zip4jUtil;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.ZipInputStream;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class UnzipEngine {

    private ZipModel zipModel;
    private FileHeader fileHeader;
    private int currSplitFileCounter = 0;
    private LocalFileHeader localFileHeader;
    private IDecrypter decrypter;
    private CRC32 crc;

    public UnzipEngine(ZipModel zipModel, FileHeader fileHeader) throws ZipException {
        if (zipModel == null || fileHeader == null) {
            throw new ZipException("Invalid parameters passed to StoreUnzip. One or more of the parameters were null");
        }

        this.zipModel = zipModel;
        this.fileHeader = fileHeader;
        this.crc = new CRC32();
    }

    public void unzipFile(UnzipParameters unzipParameters) throws ZipException {
        if (zipModel == null || fileHeader == null) {
            throw new ZipException("Invalid parameters passed during unzipping file. One or more of the parameters were null");
        }

        InputStream is = null;
        OutputStream os = null;
        try {
            byte[] buff = new byte[InternalZipConstants.BUFF_SIZE];
            int readLength = -1;

            is = getInputStream();
//            os = getOutputStream(outPath, newFileName);
            while ((readLength = is.read(buff)) != -1) {

            }
        } catch (IOException e) {
            throw new ZipException(e);
        } catch (Exception e) {
            throw new ZipException(e);
        } finally {
//            closeStreams(is, os);
        }
    }

    public ZipInputStream getInputStream() throws ZipException {
        if (fileHeader == null) {
            throw new ZipException("file header is null, cannot get inputstream");
        }

        DataInputStream dis = null;
        try {
            dis = createFileHandler();
            String errMsg = "local header and file header do not match";

            if (!checkLocalHeader())
                throw new ZipException(errMsg);

            init(dis);

            long comprSize = localFileHeader.getCompressedSize();
            long offsetStartOfData = localFileHeader.getOffsetStartOfData();

            if (localFileHeader.isEncrypted()) {
//                if (localFileHeader.getEncryptionMethod() == Zip4jConstants.ENC_METHOD_AES) {
//                    if (decrypter instanceof AESDecrypter) {
//                        comprSize -= (((AESDecrypter)decrypter).getSaltLength() +
//                                ((AESDecrypter)decrypter).getPasswordVerifierLength() + 10);
//                        offsetStartOfData += (((AESDecrypter)decrypter).getSaltLength() +
//                                ((AESDecrypter)decrypter).getPasswordVerifierLength());
//                    } else {
//                        throw new ZipException("invalid decryptor when trying to calculate " +
//                                "compressed size for AES encrypted file: " + fileHeader.getFileName());
//                    }
//                } else if (localFileHeader.getEncryptionMethod() == Zip4jConstants.ENC_METHOD_STANDARD) {
//                    comprSize -= InternalZipConstants.STD_DEC_HDR_SIZE;
//                    offsetStartOfData += InternalZipConstants.STD_DEC_HDR_SIZE;
//                }
            }

            int compressionMethod = fileHeader.getCompressionMethod();
            if (fileHeader.getEncryptionMethod() == Zip4jConstants.ENC_METHOD_AES) {
                if (fileHeader.getAesExtraDataRecord() != null) {
                    compressionMethod = fileHeader.getAesExtraDataRecord().getCompressionMethod();
                } else {
                    throw new ZipException("AESExtraDataRecord does not exist for AES encrypted file: " + fileHeader.getFileName());
                }
            }

            dis.reset();
            dis.skip(offsetStartOfData);

            switch (compressionMethod) {
//                case Zip4jConstants.COMP_STORE:
//                    return new ZipInputStream(new PartInputStream(raf, offsetStartOfData, comprSize, this));
//                case Zip4jConstants.COMP_DEFLATE:
//                    return new ZipInputStream(new InflaterInputStream(raf, offsetStartOfData, comprSize, this));
                default:
                    throw new ZipException("compression type not supported");
            }
        } catch (ZipException e) {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e1) {
                    //ignore
                }
            }
            throw e;
        } catch (Exception e) {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e1) {
                    //ignore
                }
            }
            throw new ZipException(e);
        }
    }

    private void init(DataInputStream dis) throws ZipException {

        if (localFileHeader == null) {
            throw new ZipException("local file header is null, cannot initialize input stream");
        }

        try {
            initDecrypter(dis);
        } catch (ZipException e) {
            throw e;
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    private void initDecrypter(DataInputStream dis) throws ZipException {

        if (localFileHeader == null) {
            throw new ZipException("local file header is null, cannot init decrypter");
        }

        if (localFileHeader.isEncrypted()) {
            if (localFileHeader.getEncryptionMethod() == Zip4jConstants.ENC_METHOD_STANDARD) {
//                decrypter = new StandardDecrypter(fileHeader, getStandardDecrypterHeaderBytes(raf));
            } else if (localFileHeader.getEncryptionMethod() == Zip4jConstants.ENC_METHOD_AES) {
//                decrypter = new AESDecrypter(localFileHeader, getAESSalt(raf), getAESPasswordVerifier(raf));
            } else {
                throw new ZipException("unsupported encryption method");
            }
        }
    }

    private boolean checkLocalHeader() throws ZipException {
        DataInputStream disForLH = null;

        disForLH = checkSplitFile();

        if (disForLH == null) {
            disForLH = new DataInputStream(new ByteArrayInputStream(this.zipModel.getRawStore().get(zipModel.getZipFile())));
        }

        HeaderReader headerReader = new HeaderReader(disForLH);
        this.localFileHeader = headerReader.readLocalFileHeader(fileHeader);

        if (localFileHeader == null) {
            throw new ZipException("error reading local file header. Is this a valid zip file?");
        }

        //TODO Add more comparision later
        if (localFileHeader.getCompressionMethod() != fileHeader.getCompressionMethod()) {
            return false;
        }

        if (disForLH != null) {
            try {
                disForLH.close();
            } catch (IOException e) {
                //Ignore this
            } catch (Exception e) {
                //Ignore this
            }
        }

        return true;
    }


    private DataInputStream checkSplitFile() throws ZipException {
        if (zipModel.isSplitArchive()) {
            int diskNumberStartOfFile = fileHeader.getDiskNumberStart();
            currSplitFileCounter = diskNumberStartOfFile + 1;
            String curZipFile = zipModel.getZipFile();
            String partFile = null;
            if (diskNumberStartOfFile == zipModel.getEndCentralDirRecord().getNoOfThisDisk()) {
                partFile = zipModel.getZipFile();
            } else {
                if (diskNumberStartOfFile >= 9) {
                    partFile = curZipFile.substring(0, curZipFile.lastIndexOf(".")) + ".z" + (diskNumberStartOfFile + 1);
                } else {
                    partFile = curZipFile.substring(0, curZipFile.lastIndexOf(".")) + ".z0" + (diskNumberStartOfFile + 1);
                }
            }

            try {
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(this.zipModel.getRawStore().get(partFile)));

                if (currSplitFileCounter == 1) {
                    byte[] spligSig = new byte[4];
                    dis.read(spligSig);
                    if (Raw.readIntLittleEndian(spligSig, 0) != InternalZipConstants.SPLITSIG) {
                        throw new ZipException("invalid first part split file signature");
                    }
                }
                return dis;
            } catch (IOException e) {
                throw new ZipException(e);
            }
        }
        return null;
    }

    private DataInputStream createFileHandler() throws ZipException {
        if (this.zipModel == null || !Zip4jUtil.isStringNotNullAndNotEmpty(this.zipModel.getZipFile())) {
            throw new ZipException("input parameter is null in getFilePointer");
        }

        try {
            DataInputStream dis = null;
            if (zipModel.isSplitArchive()) {
                dis = checkSplitFile();
            } else {
                dis = new DataInputStream(new ByteArrayInputStream(this.zipModel.getRawStore().get(this.zipModel.getZipFile())));
            }
            return dis;
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    public FileHeader getFileHeader() {
        return fileHeader;
    }

    public IDecrypter getDecrypter() {
        return decrypter;
    }

    public ZipModel getZipModel() {
        return zipModel;
    }

    public LocalFileHeader getLocalFileHeader() {
        return localFileHeader;
    }
}
