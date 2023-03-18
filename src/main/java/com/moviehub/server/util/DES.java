package com.moviehub.server.util;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

import java.util.Base64;

public class DES {
    private static Key key;

    private static String KEY_STR="myKey";
    private static String CHARSETNAME="UTF-8";
    private static String ALGORITHM="DES";


    static {
        try {
            //生成DES算法对象
            KeyGenerator generator=KeyGenerator.getInstance(ALGORITHM);
            //运用SHA1安全策略
            SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
            //设置上密钥种子
            secureRandom.setSeed(KEY_STR.getBytes());
            //初始化基于SHA1的算法对象
            generator.init(secureRandom);
            //生成密钥对象
            key=generator.generateKey();
            generator=null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /***
     * 获取加密的信息
     * @param str
     * @return
     */
    public static String getEncryptString(String str) {
        //基于BASE64编码，接收byte[]并转换成String
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            //按utf8编码
            byte[] bytes = str.getBytes(CHARSETNAME);
            //获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //加密
            byte[] doFinal = cipher.doFinal(bytes);
            //byte[]to encode好的String 并返回
            return new String(encoder.encode(doFinal), CHARSETNAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /***
     * 获取解密之后的信息
     * @param str
     * @return
     */
    public static String getDecryptString(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            //将字符串decode成byte[]
            byte[] firstLayerDecode = str.getBytes(CHARSETNAME);
            byte[] bytes = decoder.decode(firstLayerDecode);
            //获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化解密信息
            cipher.init(Cipher.DECRYPT_MODE, key);
            //解密
            byte[] doFial = cipher.doFinal(bytes);

            return new String(doFial, CHARSETNAME);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String s1 = DES.getEncryptString("ruanzaishengzhendehenshuai");
        System.out.println(DES.getDecryptString(s1));
    }
}
