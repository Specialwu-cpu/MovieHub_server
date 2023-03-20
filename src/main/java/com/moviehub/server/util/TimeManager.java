package com.moviehub.server.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeManager {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
    /**
    这是用于获得现在的时间戳“xxxx-xx-dd, HH:mm:ss”
     **/
    public static Timestamp getNowDateTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    /**
    获得传参timestamp与现在时间相差的秒数
    @Param timestamp
     **/
    public static int getLapseHitherto(Timestamp timestamp) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long t1 = 0L;
        long t2 = 0L;
        t1 = now.getTime();
        t2 = timestamp.getTime();
        int second = (int) ((t1 - t2) / 1000);
        return second;
    }

    public static void main(String[] args) {
        System.out.println(getLapseHitherto(Timestamp.valueOf("2023-03-16 14:01:10")));
    }
}
