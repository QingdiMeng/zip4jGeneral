package com.mengqingdi.zip4jGeneral.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by mengqingdi on 17-1-2.
 */
public class Zip4jUtil {

    public static boolean isStringNotNullAndNotEmpty(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }

        return true;
    }

    public static String decodeFileName(byte[] data, boolean isUTF8) {
        if (isUTF8) {
            try {
                return new String(data, InternalZipConstants.CHARSET_UTF8);
            } catch (UnsupportedEncodingException e) {
                return new String(data);
            }
        } else {
            return getCp850EncodedString(data);
        }
    }

    public static String getCp850EncodedString(byte[] data) {
        try {
            String retString = new String(data, InternalZipConstants.CHARSET_CP850);
            return retString;
        } catch (UnsupportedEncodingException e) {
            return new String(data);
        }
    }
}
