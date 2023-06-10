package com.moviehub.server.util;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESEncryptor {
    public static KeyGenerator keygen;
    public static SecretKey secretKey;

    public static Cipher aesCipher;

    public static AlgorithmParameters parameters;
    static {
        try {
            keygen = KeyGenerator.getInstance("AES");
            keygen.init(128);
            secretKey = keygen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            parameters = aesCipher.getParameters();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }






    public static String cipher(String text) throws IllegalBlockSizeException, BadPaddingException {
        byte[] stringToByte = text.getBytes(StandardCharsets.UTF_8);
        byte[] cipherText = aesCipher.doFinal(stringToByte);
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decipher(String ciphertext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey, parameters);
        byte[] cipherText = Base64.getDecoder().decode(ciphertext);
        return new String(aesCipher.doFinal(cipherText));
    }

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        System.out.println(decipher("WTqJMYpGRbwQtlj6UU8w8l0dzqkWsXnnM/urHmjw/Ur5yL8OTkJDOXFz4iZUVkCh"));
    }

}
