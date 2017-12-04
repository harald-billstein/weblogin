package com.weblogin.utilities;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.SecureRandom;

public class SaltGenerator {

    private final static int KEY_LENGTH = 64;
    private static SecureRandom random;

    public static String getSalt() {
        random = new SecureRandom();
        byte[] byteArray = new byte[KEY_LENGTH];
        random.nextBytes(byteArray);
        return Base64.encode(byteArray);
    }
}