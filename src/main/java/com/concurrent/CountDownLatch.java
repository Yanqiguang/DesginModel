package com.concurrent;

/**
 * @program: DesignModel  类似于一个计数器，初始化时指定需要等待的任务数， CountDownLatch.countDown(); 方法每次-1 ，在计数为0 时 进行当前任务进行
 * @description: CountDownLatch 用法
 * @author: lester.yan
 * @create: 2019-02-02 15:25
 **/

public class CountDownLatch {

    public static void main(String[] args) {
        final java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(2);

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
