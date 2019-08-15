package com.distributed.redis.lock;


import com.distributed.redis.ThreadIdUtils;
import com.distributed.redis.lock.RedisLockUtil;

/**
 * @program: DesignModel
 * @description: 分布式锁
 * @author: lester.yan
 * @create: 2019-01-29 15:51
 **/

public class UniqueLockUtil {

    public UniqueLockUtil() {
    }
    /**
     * 锁定,默认锁定10分钟
     * @param key 锁的信息
     * @return 是否加锁成功
     */
    public static boolean lock(String key) {
        return RedisLockUtil.setNXWithSeconds(key, ThreadIdUtils.get(), 600L);
    }
    /**
     * 锁定
     * @param key 锁的信息
     * @param milliseconds 锁定毫秒数
     * @return 是否加锁成功
     */
    public static boolean lockWithMilli(String key, long milliseconds) {
        return RedisLockUtil.setNXWithMilli(key, ThreadIdUtils.get(), milliseconds);
    }
    /**
     * 锁定
     * @param key 锁的信息
     * @param seconds 锁定秒数
     * @return 是否加锁成功

     */
    public static boolean lockWithSeconds(String key, long seconds) {
        return RedisLockUtil.setNXWithSeconds(key, ThreadIdUtils.get(), seconds);
    }
    /**
     * 释放锁
     * @param key 锁的信息
     * @return 是否释放锁成功
     */
    public static boolean release(String key) {
        return RedisLockUtil.del(key, ThreadIdUtils.get());
    }
}
