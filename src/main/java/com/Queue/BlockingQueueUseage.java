package com.Queue;

import java.util.concurrent.*;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

/**
 * @program: DesignModel
 * @description: 基于内存的队列  ArrayBlockingQueue LinkedBlockingQueue
 * 在处理消息的时候，阻塞会浪费资源，降低处理效率，所以在没有集成消息队列时，在数据量不大的情况下可以使用内存消息队列
 * @author: lester.yan
 * @create: 2019-04-12 15:31
 **/

public class BlockingQueueUseage {

    private final int QUEUE_LENGTH = 10000 * 10;

    /**
     * 创建消息队列
     */
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>(QUEUE_LENGTH);

    /**
     * 创建计划任务执行器
     */
    private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

    /**
     * 构造函数，执行execute方法
     */
    public BlockingQueueUseage() {
        execute();
    }

    /**
     * 添加信息至队列中
     * @param content
     */
    public void addQueue(String content) {
        queue.add(content);
    }


    /**
     * 初始化执行
     */
    public void execute() {
        //每一分钟执行一次
        es.scheduleWithFixedDelay(new Runnable(){
            @Override
            public void run() {
                try {
                    String content = queue.take();
                    //处理队列中的信息。。。。。
                    System.out.println(content);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, 0, 1, TimeUnit.MINUTES);
    }
}
