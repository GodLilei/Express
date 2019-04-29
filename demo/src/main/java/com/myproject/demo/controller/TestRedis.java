package com.myproject.demo.controller;

import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;

public class TestRedis {
    public static void main(String[] args){

//        Jedis redis = new Jedis("10.129.220.28", 6377, 6000);
//        Jedis redis1 = new Jedis("10.129.220.28", 7130, 6000);
//        Jedis redis2 = new Jedis("10.129.220.28", 7131, 6000);
//        Jedis redis3 = new Jedis("10.129.220.28", 7132, 6000);
//
//        redis3.ltrim("T_STL_TRANSFER_FRONT:NOTICE_0",-1,0);

//        System.out.println("redis -------------------------------------");
//        System.out.println("T_STL_TRANSFER_FRONT:NOTICE_7 SIZE：" + redis.llen("T_STL_TRANSFER_FRONT:NOTICE_1"));
//        List<String> list = redis.lrange("T_STL_TRANSFER_FRONT:NOTICE_1",0,80);
//        for (String str: list) {
//            System.out.println(str);
//        }
//
//        Jedis jedis = new Jedis("10.129.220.28",7060,60000);
//        jedis.llen("NOTICE:COD_DELIVERY_REDIS_LIST");
//        List<String> list = jedis.lrange("NOTICE:COD_DELIVERY_REDIS_LIST",0,80);
//
//        for (String str: list) {
//            System.out.println(str);
//        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(2);
        poolConfig.setMaxIdle(1);
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        JedisShardInfo shardInfo1 = new JedisShardInfo("10.129.220.28", 7041, 60000);//揽收
        JedisShardInfo shardInfo2 = new JedisShardInfo("10.129.220.28", 7043, 60000);//派件
        JedisShardInfo shardInfo3 = new JedisShardInfo("10.129.220.28", 7045, 60000);//签收
        JedisShardInfo shardInfo4 = new JedisShardInfo("10.129.220.28", 7054, 60000);//录单

        List<JedisShardInfo> infoList = Arrays.asList(shardInfo3);
        ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig,infoList);

        try (ShardedJedis jedis1 = jedisPool.getResource()) {
            String test = jedis1.get("DD9906639628");
//            jedis1.del("DD9906639628");
        }
    }
}
