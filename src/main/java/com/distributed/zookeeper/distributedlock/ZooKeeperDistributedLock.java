package com.distributed.zookeeper.distributedlock;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.ZooKeeper;

/**
 * @program: DesignModel
 * @description: zk 分布式锁 公平锁
 * @author: lester.yan
 * @create: 2019-04-18 17:58
 **/

public class ZooKeeperDistributedLock {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zookeeper;
    private CountDownLatch latch;

    /**
     * 初始化连接集群
     */
    public ZooKeeperDistributedLock() {
        try {
            this.zookeeper = new ZooKeeper("192.168.31.187:2181,192.168.31.19:2181,192.168.31.227:2181", 50000, new ZooKeeperWatcher());
            try {
                connectedSemaphore.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ZooKeeper session established......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
