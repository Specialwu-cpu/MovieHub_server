package com.moviehub.server.util;
import java.util.Random;

public class verifyCode {


    //一个小工具 生成五位验证码的
    public static String getVerifyCode(){
        Random random = new Random();                         

        String verifyCode = "";                                   

        for (int i = 0; i < 5; i++) {                       

            int a = random.nextInt(3);                            
            switch(a){                                          
                case 0:                                         
                    char s=(char)(random.nextInt(26)+65);
                    verifyCode = verifyCode + s;
                    break;
                case 1:
                    char s1=(char)(random.nextInt(26)+97);
                    verifyCode = verifyCode + s1;
                    break;
                case 2:
                    int s2=random.nextInt(10);
                    verifyCode = verifyCode + s2;
                    break;
            }
        }
        return verifyCode;
    }

}
