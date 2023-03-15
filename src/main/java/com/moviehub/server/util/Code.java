package com.moviehub.server.util;

/**
 * Created by wsh on 23/3/14.
 */
public class Code {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    public static final String SERVER_ADDRESS = "http://118.190.210.121";

    /**
     * 存放Authorization的header字段
     */
    public static final String TOKEN = "token";

    /**
     * 存放sig的header字段
     */
    public static final String SIG = "sig";

    /**
     * 存放tokenType的header字段
     */
    public static final String TOKEN_TYPE = "tokenType";

    /**
     * 存放sig的header字段
     */
    public static final String REQUEST_TIME = "requestTime";

    public static final int NOT_LOGIN_ERROR = 2;

    public static final int ARTICLE_NEW = 1;

    public static final int ARTICLE_VALID = 0;

    public static final int NO_SEX = 0;

    public static final int MALE = 1;

    public static final int FEMALE = 2;

    public static final String ERROR_PAGE = "/error.jsp";

    public static final String MES_PWD = "78$d3e36";

    public static final String MES_SN = "SDK-WSS-010-08624";

    public static final String LEANCLOUD_APP_ID = "NnyPylB5FOiET7PX4xMOCxIo-gzGzoHsz";

    public static final String LEANCLOUD_APP_KEY = "uXALt9Upksj4bdn2OC3H1lz0";

    public static final String LEANCLOUD_E_APP_ID = "dppo1wsrYOeFA12pabQeATM6-gzGzoHsz";

    public static final String LEANCLOUD_E_APP_KEY = "WLAC22lW35TrkYqL2lhtX6vz";

    public static final String DEVICE_INSTALL_URL = "https://leancloud.cn/1.1/installations";

    public static final String MESSAGE_URL = "https://leancloud.cn/1.1/push";

    public static final String LEANCLOUD_MASTER_KEY = "xinHNv7najD3N82wFNa0fdSS";

    public static final String PING_CODE = "sk_live_zT8ur1nXzTaDbjnLS89m5izL";

//    public static final String PING_PRIVATE_KEY = "MIICXAIBAAKBgQDKt70b2h8d3PgEgYpsY1N94nkHmYTRS20IEnp5GUkQNbyXxFvN\n" +
//            "iYCLZyAra5WVFW9xEgBa34ovQrkPzfAE7Ol2YMS7ZLYQgMeO3dj9AoFjE+Du2yDP\n" +
//            "xpg1ohQ3x1JAJAlnI3UC5shCTVi9CfXOiBmRUANKOkoJDO1Rc0M9PWdVjwIDAQAB\n" +
//            "AoGAPXlOrb2Nph62T5eBBLFyRkCBd16EuntnCwWfgk26rGG/WT7AoCnMg3m4dbJg\n" +
//            "YB9p9h4BYY1ceEyBlltbKAM4IJUIy1BLqFNFSTHUNQ3ipzx5kC2QV+Ac6cgxyiPg\n" +
//            "LaCMaSdetirbq/Q85oJXBlkuM/LSru/An8NlQiIlYX27vSECQQD7u2M3/6qwoUi+\n" +
//            "1MBHKDRG2w9jCjnlVWfdEa0xi7PzmCEKJvI4IZu6AmnlL8cvJZ/7MNBx7Bi6cZzW\n" +
//            "N72CDQIZAkEAziecU+Jq4FVPVGdb9F02inFe3/satFjbkFt37/22Cyn3tPz/Rb+7\n" +
//            "kq6g2j/MWWI+CctRH8i60EsL7fh3HOYZ5wJAWZ2tRctD1dulDSKqTOq4KZ1kzepf\n" +
//            "EBCmiCH52VCVwJug739L7cWxLbgcQNYQf+1SFdeb7WKSrxUxM2XmljzRSQJAXvw5\n" +
//            "Nx03iS1FY+pLoAfivV8HC8Qyxa27XIQIevc3DWXE0AtRwt1Ym3kCfAyxJu3xD6oy\n" +
//            "MqbWDGrHkFnO5+3NOQJBAI5lyfbjXUVoo00zl9P4WfagwfsYf+sIFr6K+djimjtH\n" +
//            "vrdP5DmKJmfvihrUJRM9+XGb5RpCraKCEWc0uth/0NE=";

    public static final String PING_PRIVATE_KEY = "MIICXQIBAAKBgQDeQjskdv1IA2GozdaFHdB8qHJ+bYzsY1qVSxi+CwohXOjsrKYF\n" +
            "1a+eIXb4PDSDC7HSBPBx9pzFM2sAgqBSGK/IhYLZRo9lGAMZtAC2dAn5rIWtV7pD\n" +
            "VvKHmuHXa2fPdu5NxnagvY7mP1A+Xwbrt9gJ9r1iF8ZfXPzAEpfq2h5VIQIDAQAB\n" +
            "AoGANMsIn1XAPe3HscAK952oWyPr5koBUlkbOCEZZHLc2iXmkelEmlgymd4bE0XB\n" +
            "t2r8twVDU96fSFw5S2q7yCRjBE4XTTW5bApi/ggzzUl72NSv2VvzJ57IJRvLd7GS\n" +
            "yl9LJ2QDyl6B0XHA/7YBe9DUxVDGMLdJ5Jd2EmG16kHAiVECQQDwwKqSzcY4NfzQ\n" +
            "YrRgvBKJIVMdSzlw58+LPF5N/zrUwBLCYkogDl4ZHuayYMPWOpvnAQU2XuXhbq9J\n" +
            "88EqDmhtAkEA7FW48FRY3u3ZGXYFrBOZm4c/giRinuNVvLiS/6r8kJeZX/R2JuW4\n" +
            "D4Yw9nOCC1fELNoFiHFkkz3Ij4oIPnaXBQJBAL0R0eNwjtbeLylIGvhNOxFOhoNe\n" +
            "7EFrC2cBhd4jLwSdwx29KxF5txRrXCmCLMo1RPJprrpAVLBxyF9/epeYsRECQQCV\n" +
            "VzHvRA2IiZoHecnpy9TkL9nU3jMnnhY+lA/n2V+R6wozVWIYAE+IF8723VTO4B4K\n" +
            "DZczehwI4yDKe3nsJroVAkBEqpdsVjxzJF6eZ2ZHoJQWqDUezttDB2az0URc7dsh\n" +
            "cCIojKV1C53Da2eGgU+sneuXPJwd+VlTFfwaikQs3Xpw";

    public static final String PING_APP_CODE = "app_zHW5eTyHuXr54yXv";

    public static final String PING_APP_CODE_E = "app_500qTGGSmzDO58mz";

    public static final String NETEASE_IM_APP_KEY = "8b860f93e7348141d633ba254f64bb6d";

    public static final String NETEASE_IM_APP_SECRET = "41b892cb728b";
}