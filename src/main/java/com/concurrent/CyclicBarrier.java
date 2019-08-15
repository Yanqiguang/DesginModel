package com.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @program: DesignModel  让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用  可以重用
 * @description: CyclicBarrier 用法
 * @author: lester.yan
 * @create: 2019-02-02 15:38
 **/

public class CyclicBarrier {

    public static void main(String[] args) {
        int parties = 4;
        java.util.concurrent.CyclicBarrier barrier = new java.util.concurrent.CyclicBarrier(parties,
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("当前线程" + Thread.currentThread().getName() + "在所有其他线程任务执行完，再执行。");
                    }
                }

        );
        for (int i = 0; i < parties; i++) {
            if (i < parties - 1) {
                new Writer(barrier).start();

            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Writer(barrier).start();
            }
        }
    }


    static class Writer extends Thread {
        private java.util.concurrent.CyclicBarrier cyclicBarrier;

        public Writer(java.util.concurrent.CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                //cyclicBarrier.await();
                cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
