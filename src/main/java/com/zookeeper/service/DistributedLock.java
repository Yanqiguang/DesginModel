package com.zookeeper.service;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 分布式锁
 * @Param:
 * @return:
 * @Author: lester.yan
 * @Date: 2019/2/2
 */
public interface DistributedLock {

    /**
     * 尝试获取锁
     * @param time 时间
     * @param unit 时间类型
     * @return
     * @throws Exception
     */
    boolean tryLock(String rootPath,long time, TimeUnit unit) throws Exception;

    /**
     * 加锁
     * @throws Exception
     */
    void lock() throws Exception;

    /**
     * 加锁
     * @param time 时间
     * @param unit 时间类型
     * @return
     * @throws Exception
     */
    public boolean lock(long time, TimeUnit unit,String rootPath) throws Exception;

    /**
     * 解锁
     * @throws Exception
     */
    public void unLock() throws Exception;
}
