package com.mengqingdi.zip4jGeneral.io;

import com.mengqingdi.zip4jGeneral.unzip.UnzipEngine;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mengqingdi on 17-1-2.
 */
public abstract class BaseInputStream extends InputStream {

    public int read() throws IOException {
        return 0;
    }

    public void seek(long pos) throws IOException {
    }

    public int available() throws IOException {
        return 0;
    }

    public UnzipEngine getUnzipEngine() { return null; }
}
