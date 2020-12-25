package com.simple.add.core.utils;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : zenghao
 * @Title:
 * @description : 初始化自己的redis命令
 * @version: 1.0
 * @date : 2019/07/30 09:50
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public class RedisUtils {

    private static final String SHARDED = "SHARDED";

    private static final String CLUSTER = "CLUSTER";

    private static String redisType = PropertiesUtils.getProperty("redis.type");

    private static String redisHost = PropertiesUtils.getProperty("redis.node.addres", "127.0.0.1:6379");

    private static String redisPoolSize = PropertiesUtils.getProperty("redis.pool.size", "8");

    private static ShardedJedisPool jedisPool;
    private static JedisCluster jedisCluster;

    /**
     * 初始化jedisPool&jedisCluster
     */
    static {
        if (SHARDED.equalsIgnoreCase(redisType)) {
            JedisPoolConfig config = new JedisPoolConfig();
            jedisPool = new ShardedJedisPool(buildConfig(Integer.parseInt(redisPoolSize)), getJedisShardInfos(redisHost));
        } else if (CLUSTER.equalsIgnoreCase(redisType)) {
            jedisCluster = new JedisCluster(getNodes(redisHost));
        } else {
            throw new IllegalStateException("redis configuration may not init correct!please check it out!");
        }
    }

    public static JedisCommands getRedisCommands() {
        if (SHARDED.equals(redisType)) {
            return jedisPool.getResource();
        } else if (CLUSTER.equals(redisType)) {
            return jedisCluster;
        } else {
            throw new RuntimeException("redis type does not exist,please check the configuration of service.properties");
        }

    }
    public static void close(JedisCommands commands) {
        if (commands != null) {
            if (commands instanceof ShardedJedis) {
                ((ShardedJedis) commands).close();
            }
        }
    }

    private static Set<HostAndPort> getNodes(String redisHost) {
        String[] hosts = redisHost.split(",");
        Set<HostAndPort> hostAndPorts = new HashSet<>(hosts.length);
        for (String host : hosts) {
            String[] split = host.split(":");
            hostAndPorts.add(new HostAndPort(split[0], Integer.parseInt(split[1])));
        }
        return hostAndPorts;
    }

    private static JedisPoolConfig buildConfig(Integer redisPoolMaxTotal) {
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxTotal(redisPoolMaxTotal);
        jpc.setMaxWaitMillis(1000L);
        jpc.setTestOnBorrow(true);
        return jpc;
    }

    private static List<JedisShardInfo> getJedisShardInfos(String hostsAndPorts) {
        List<JedisShardInfo> rs = new ArrayList();
        String[] split = redisHost.split(":");
        rs.add(new JedisShardInfo(split[0], Integer.parseInt(split[1])));
        return rs;
    }

}
