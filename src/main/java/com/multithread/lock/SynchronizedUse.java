package com.multithread.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: DesignModel
 * @description: 同步关键字 synchronized
 * @author: lester.yan
 * @create: 2019-08-08 10:21
 **/

public class SynchronizedUse extends Thread{

    private static Logger logger= LoggerFactory.getLogger(SynchronizedUse.class);

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public synchronized void run() {
        try {
            Thread.sleep(3000);
            logger.info("synchronized 同步加锁 thread_name is {}" ,new Object[]{Thread.currentThread().getName()});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SynchronizedUse synchronizedUseA=new SynchronizedUse();
        Thread threadA = new Thread(synchronizedUseA);
        threadA.setName("AAAAA");
        threadA.start();
        Thread threadB = new Thread(synchronizedUseA);
        threadB.setName("BBBBB");
        threadB.start();
        logger.info("  thread_name is {}" ,new Object[]{Thread.currentThread().getName()});
    }
}
