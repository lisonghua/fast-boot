package com.sj.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;

/**
 * Created by sunxyz on 2017/3/17.
 */
public final class EncryptionUtils {

    private static final String salt = "SAN_JI_1688@SJ";

    private EncryptionUtils() {
    }

    public static String getMd5(String source) {
        String hex = new Md5Hash(source + salt).toHex();
        return hex;
    }

    public static String getSha512Hash(String password) {
        String encodedPassword = new Sha512Hash(password, salt).toBase64();
        return encodedPassword;
    }

}
