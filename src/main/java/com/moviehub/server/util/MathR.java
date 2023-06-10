package com.moviehub.server.util;

public class MathR {

    public static float dot(float[] f1, float[] f2) {
        float sum = 0;
        for (int i = 0; i < f1.length; i++) {
            sum += f1[i] * f2[i];
        }
        return sum;
    }

}
