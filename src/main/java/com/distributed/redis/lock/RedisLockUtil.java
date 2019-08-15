package com.distributed.redis.lock;

import com.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-29 15:55
 **/

public class RedisLockUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(RedisLockUtil.class);
    private static JedisPool jedisPool = null;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String EXPIRE_TIME_UNITS_EX = "EX";
    private static final String EXPIRE_TIME_UNITS_PX = "PX";
    private static final String COMMEND_SUCCESS_RESULT = "OK";
    private static final String COMMEND_FAIL_RESULT = "FAIL";
    private static final String LUA_DEL_KEY_VALUE = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0  end";

    public RedisLockUtil() {
    }

    public static boolean setNXWithMilli(String key, String value, long milliseconds) {
        if (key != null && value != null) {
            try {
                Jedis jedis = getJedisClient();
                Throwable var5 = null;

                boolean var7;
                try {
                    String result = jedis.set(key, value, "NX", "PX", milliseconds);
                    var7 = "OK".equalsIgnoreCase(result);
                } catch (Throwable var17) {
                    var5 = var17;
                    throw var17;
                } finally {
                    if (jedis != null) {
                        if (var5 != null) {
                            try {
                                jedis.close();
                            } catch (Throwable var16) {
                                var5.addSuppressed(var16);
                            }
                        } else {
                            jedis.close();
                        }
                    }

                }

                return var7;
            } catch (Throwable var19) {
                LOGGER.error("[E1][Jedis setNXWithMilli Throwable，key：{}，value：{}，milliseconds：{}]", new Object[]{key, value, milliseconds, var19});
                throw new IllegalArgumentException("[E1][Jedis setNXWithMilli Throwable]", var19);
            }
        } else {
            throw new IllegalArgumentException("the paramter key and value must be not null");
        }
    }

    public static boolean setNXWithSeconds(String key, String value, long seconds) {
        if (key != null && value != null) {
            try {
                Jedis jedis = getJedisClient();
                Throwable var5 = null;

                boolean var7;
                try {
                    String result = jedis.set(key, value, "NX", "EX", seconds);
                    var7 = "OK".equalsIgnoreCase(result);
                } catch (Throwable var17) {
                    var5 = var17;
                    throw var17;
                } finally {
                    if (jedis != null) {
                        if (var5 != null) {
                            try {
                                jedis.close();
                            } catch (Throwable var16) {
                                var5.addSuppressed(var16);
                            }
                        } else {
                            jedis.close();
                        }
                    }

                }

                return var7;
            } catch (Throwable var19) {
                LOGGER.error("[E1][Jedis setNXWithSeconds Throwable，key：{}，value：{}，seconds：{}]", new Object[]{key, value, seconds, var19});
                throw new IllegalArgumentException("[E1][Jedis setNXWithSeconds Throwable]", var19);
            }
        } else {
            throw new IllegalArgumentException("the paramter key and value must be not null");
        }
    }

    public static boolean del(String key, String value) {
        if (key != null && value != null) {
            try {
                Jedis jedis = getJedisClient();
                Throwable var3 = null;

                boolean var5;
                try {
                    Long result = (Long)jedis.eval("if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0  end", 1, new String[]{key, value});
                    var5 = result.equals(1L);
                } catch (Throwable var15) {
                    var3 = var15;
                    throw var15;
                } finally {
                    if (jedis != null) {
                        if (var3 != null) {
                            try {
                                jedis.close();
                            } catch (Throwable var14) {
                                var3.addSuppressed(var14);
                            }
                        } else {
                            jedis.close();
                        }
                    }

                }

                return var5;
            } catch (Throwable var17) {
                LOGGER.error("[E1][redis del Throwable]key:{}，value:{}", new Object[]{key, value, var17});
                throw new IllegalArgumentException("[E1][redis del Throwable]", var17);
            }
        } else {
            throw new IllegalArgumentException("the paramter key and value must be not null");
        }
    }

    public static final Jedis getJedisClient() {
        initJedisPool();

        try {
            return jedisPool.getResource();
        } catch (Throwable var1) {
            LOGGER.error("[E1][get Jedis Client from Pool Throwable]", var1);
            throw new IllegalArgumentException("[E1][请检查Redis数据库连接池配置是否正常]", var1);
        }
    }

    public static void initJedisPool() {
        if (jedisPool == null) {
            Class var0 = RedisLockUtil.class;
            synchronized(RedisLockUtil.class) {
                if (jedisPool == null) {
                    jedisPool = (JedisPool) SpringContextUtils.getBean(JedisPool.class);
                }
            }
        }

    }
}
