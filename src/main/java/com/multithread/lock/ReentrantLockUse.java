package com.multithread.lock;

import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: DesignModel
 * @description: ReentrantLock 用法
 * @author: lester.yan
 * @create: 2019-08-08 10:36
 **/

public class ReentrantLockUse implements Runnable {

    /**
     * 需要保证多个线程使用的是同一个锁
     */
    private ReentrantLock reentrantLock = new ReentrantLock();

    private static Logger logger= LoggerFactory.getLogger(ReentrantLockUse.class);


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            reentrantLock.lock();
            Thread.sleep(3000);
            logger.info("ReentrantLock 同步加锁 thread_name is {}" ,new Object[]{Thread.currentThread().getName()});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.unlock();
    }



    public static void main(String[] args) {
        try {
            ReentrantLockUse reentrantLockUse=new ReentrantLockUse();
            Thread threadA = new Thread(reentrantLockUse);
            threadA.setName("AAAAA");
            threadA.start();
            Thread threadB= new Thread(reentrantLockUse);
            threadB.setName("BBBBB");
            threadB.start();
            logger.info("  thread_name is {}" ,new Object[]{Thread.currentThread().getName()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
