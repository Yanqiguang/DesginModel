package com.zookeeper.distributedlock;

import java.util.concurrent.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: DesignModel
 * @description: zookeeper 实现分布式锁
 * @author: lester.yan
 * @create: 2019-02-01 15:03
 **/

public class DistributedLockByZK {

    /**
     * 线程池
     **/
    private static ExecutorService executorService = null;
    private static final int THREAD_NUM = 5;
    private static int threadNo = 0;
    private static CountDownLatch threadCompleteLatch = new CountDownLatch(THREAD_NUM);

    /**
     * ZK的相关配置常量
     **/

    private static final String CONNECTION_STRING = "127.0.0.1:2181";
    private static final int SESSION_TIMEOUT = 10000;
    /**
     * 此变量在LockWatcher中也有一个同名的静态变量，正式使用的时候，提取到常量类中共同维护即可。
     */
    private static final String LOCK_ROOT_PATH = "/myDisLocks";


    @Autowired private
    LockWatcher watcher;


    /**
     * @Description: 模拟并发任务
     * @Param: []
     * @return: void
     * @Author: lester.yan
     * @Date: 2019/2/1
     */
    public static void startProcess() {
        Runnable disposeBusinessRunnable = new Thread(()->{
                String threadName = Thread.currentThread().getName();
                LockWatcher lock = new LockWatcher(threadCompleteLatch);
                try {
                    lock.createConnection(CONNECTION_STRING, SESSION_TIMEOUT);
                    synchronized (DistributedLockByZK.class) {
                        lock.createPersistentPath(LOCK_ROOT_PATH, "该节点由" + threadName + "创建", true);
                    }
                    lock.getLock();
                } catch (Exception e) {

                }
        });
    }

    public static void main(String[] args) {
        // 定义线程池
        executorService = Executors.newFixedThreadPool(THREAD_NUM, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                String name = String.format("第[%s]个测试线程", ++threadNo);
                Thread ret = new Thread(Thread.currentThread().getThreadGroup(), r, name, 0);
                ret.setDaemon(false);
                return ret;
            }
        });
        // 启动线程
        if (executorService != null) {
            startProcess();
        }
    }
}
