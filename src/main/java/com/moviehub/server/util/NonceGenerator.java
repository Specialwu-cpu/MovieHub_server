package com.moviehub.server.util;
import java.security.SecureRandom;
import java.math.BigInteger;

public class NonceGenerator {
    private static SecureRandom secureRandom = new SecureRandom();


    public static String generateNonce(){
        return new BigInteger(130, secureRandom).toString();
    }
}
