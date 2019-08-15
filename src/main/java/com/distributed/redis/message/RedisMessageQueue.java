package com.distributed.redis.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @program: DesignModel
 * @description: redis 实现消息队列
 * @author: lester.yan
 * @create: 2019-08-06 16:25
 **/

public class RedisMessageQueue {



    /**
     * 存放消息的队列
     */
    @Autowired
    private BoundListOperations<String, String> boundListOperations;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public RedisMessageQueue() {
        this.boundListOperations = stringRedisTemplate.boundListOps("RedisMessageKey");
    }
}
