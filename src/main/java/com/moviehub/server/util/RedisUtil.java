package com.moviehub.server.util;
import redis.clients.jedis.Jedis;

public class RedisUtil {
    private static final Jedis jedis = new Jedis("ruanzaisheng.com");
    public static Jedis getJedis() {
        jedis.auth("MovieHub666!");
        return jedis;
    }
}
