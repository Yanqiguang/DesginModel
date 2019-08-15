package com.concurrent;

/**
 * @program: DesignModel  Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 * @description: Semaphore 用法
 * @author: lester.yan
 * @create: 2019-02-02 16:21
 **/

public class Semaphore {

    public static void main(String[] args) {
        int parities = 8;
        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(5);
        for (int i = 0; i < parities; i++) {
            new Worker(i, semaphore).start();
        }


    }

    static class Worker extends Thread {
        private int num;
        private java.util.concurrent.Semaphore semaphore;
        public Worker(int num,java.util.concurrent.Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
