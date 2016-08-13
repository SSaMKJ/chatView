package com.kjslab.chat.view.crypt;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jcajce.provider.digest.SHA3;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * Created by kimjinsam on 2016. 8. 13..
 */

public class SHA3Security {

    private final static SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);

    public static String sha3(String text) throws InvalidKeyException {
        if (StringUtils.isBlank(text)) return null;
        sha3.update(text.getBytes());
        byte[] kkk = sha3.digest();
        return byteArrayToHex(kkk);
    }


    private static String byteArrayToHex(byte[] ba) throws InvalidKeyException {
        String s = "";
        try {

            StringBuilder sb = new StringBuilder();
            for (byte b : ba) {
                sb.append(String.format("%02X", b));
            }
            s = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return s;
    }

    public static String generateToken() throws InvalidKeyException, NoSuchAlgorithmException {
        String seed = ""+System.currentTimeMillis();
        return hmac(seed);
    }

    /**
     * seed 가 들어가는 sha3 (seed 가 없으면 자동으로 생성)
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String hmac(String text) throws NoSuchAlgorithmException, InvalidKeyException {
        if (StringUtils.isBlank(text)) return null;
        String seed = ""+new Random().nextGaussian();
        return hmac(seed, text);
    }

    /**
     * seed 가 들어가는 sha3
     * @param seed
     * @param text
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String hmac(String seed, String text) throws InvalidKeyException, NoSuchAlgorithmException {
        if (StringUtils.isBlank(seed)) return null;
        if (StringUtils.isBlank(text)) return null;

        String hash = null;
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(seed.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            hash = Base64.encodeBase64String(sha256_HMAC.doFinal(text.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (InvalidKeyException e) {
            throw e;
        }
        return hash;
    }
}