package org.sadr._core.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Cryptor {

    public static Cipher getEncryptCipher(String key) {
        try {
            IvParameterSpec iv = new IvParameterSpec("_IV_12weASdcvf34".getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            return cipher;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Cipher getDecryptCipher(String key) {
        try {
            IvParameterSpec iv = new IvParameterSpec("_IV_12weASdcvf34".getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            return cipher;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String encrypt(String value) {
        return encrypt("!2#4%6KhFdS^7*_+CdNliv^12$-vaKi6", "_IV_12weASdcvf34", value);
    }

    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String value) {
        return decrypt("!2#4%6KhFdS^7*_+CdNliv^12$-vaKi6", "_IV_12weASdcvf34", value);
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            OutLog.pl(new String(original));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    //======================= Digital Signature

    public static KeyPair loadKeyPair(byte[] encodedPublicKey, byte[] encodedPrivateKey) {

        PublicKey publicKey;
        PrivateKey privateKey;

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            publicKey = keyFactory.generatePublic(publicKeySpec);

            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);

            privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        return new KeyPair(publicKey, privateKey);
    }

    public static byte[] sign(byte[] data, byte[] privateKeyByte) {
        PrivateKey privateKey;
        byte[] digitalSignature = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);

            privateKey = keyFactory.generatePrivate(privateKeySpec);

            ////----------
            Signature signature = Signature.getInstance("SHA256WithDSA");
            SecureRandom secureRandom = new SecureRandom();

            signature.initSign(privateKey, secureRandom);

            signature.update(data);

            digitalSignature = signature.sign();

        } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return digitalSignature;
    }

    public static boolean verifySign(byte[] data, byte[] sign, byte[] publicKeyByte) {
        PublicKey publicKey;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyByte);
            publicKey = keyFactory.generatePublic(publicKeySpec);

            ////----------
            Signature signature = Signature.getInstance("SHA256WithDSA");

            signature.initVerify(publicKey);
            signature.update(data);

            return signature.verify(sign);

        } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }

}
