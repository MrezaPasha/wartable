package org.sadr._core.utils;

import org.bouncycastle.jcajce.provider.digest.MD5;
import org.jasypt.digest.PooledStringDigester;
import org.jasypt.salt.ZeroSaltGenerator;

import java.nio.charset.StandardCharsets;

/**
 * کلاس مربوط به چکیده سازی با استفاده از Jasypt
 *
 * @author masoud
 * @version 1.95.03.31
 */
public class Digester {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    //Function to encrypt password

    /**
     * تابع چکیده ساز با استفاده از Jasypt
     *
     * @param value
     * @return
     */
    public static String digestSHA1(String value) {

        PooledStringDigester digester = new PooledStringDigester();

        digester.setPoolSize(4);
        digester.setAlgorithm("SHA-1");

        digester.setIterations(100);
        digester.setSaltGenerator(new ZeroSaltGenerator());
        return digester.digest(value);
    }

}
