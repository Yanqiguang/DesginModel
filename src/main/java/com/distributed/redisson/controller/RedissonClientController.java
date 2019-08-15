package com.distributed.redisson.controller;

import java.util.concurrent.TimeUnit;

import com.distributed.redisson.service.RedissonSerivce;
import com.distributed.redisson.model.CustomerVO;
import com.utils.json.JsonUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-18 14:08
 **/

@RestController
@RequestMapping("/redisson")
public class RedissonClientController {

    private int count=0;

    private static Logger logger = LoggerFactory.getLogger(RedissonClientController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedissonSerivce redissonSerivce;

    @ResponseBody
    @RequestMapping("/lock")
    public String redissonLock(@RequestParam("requestId") String requestId) {
        Long counter = redisTemplate.opsForValue().increment("COUNTER", 1);
        RLock lock = redissonClient.getFairLock(("TEST"));
        Long startTime = System.currentTimeMillis();

        try {
            boolean flag = lock.tryLock(200, 200, TimeUnit.MILLISECONDS);
            Long endTime = System.currentTimeMillis();
            logger.info(requestId + "获取锁花费了+++++++++++++++" + (endTime - startTime) + "时间");
            logger.info("Request Thread - " + counter + "[" + requestId + "] locked and begun...");
            Thread.sleep(20000);
            logger.info(requestId + "执行操作了额乐乐乐乐乐乐乐乐");
            logger.info("Request Thread - " + counter + "[" + requestId + "] ended successfully...");
        } catch (InterruptedException e) {
            logger.info("Request Thread - " + counter + "[" + requestId + "] error...");
        } finally {
            lock.unlock();
            logger.info("Request Thread - " + counter + "[" + requestId + "] unlocked...");
        }
        return "lock-" + counter + "[" + requestId + "]";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/createCustomer")
    public String create(HttpEntity<String> requestEntity) throws Exception{
        Long party_id = 0L;
        String requestEntityBody = requestEntity.getBody();
        CustomerVO customerVO = new CustomerVO();
        try {
            customerVO = JsonUtil.json2Object(requestEntityBody, CustomerVO.class);
            party_id = redissonSerivce.Transactional(customerVO);
        } catch (Exception e) {
            party_id = 0000000000L;

        }
        return Long.toString(party_id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/transaction")
    public String testTransactional(HttpEntity<String> requestEntity) throws Exception{
        redissonSerivce.Transactional2();
        return "123";
    }





}
