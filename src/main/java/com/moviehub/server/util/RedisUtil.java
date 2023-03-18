package com.moviehub.server.util;
import net.sf.jsqlparser.expression.StringValue;
import redis.clients.jedis.Jedis;

public class RedisUtil {
    private static Jedis jedis = new Jedis("ruanzaisheng.com");

    public static Jedis getJedis() {
        jedis.auth("MovieHub666!");
        return jedis;
    }
}
