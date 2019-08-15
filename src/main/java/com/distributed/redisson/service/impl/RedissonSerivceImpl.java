package com.distributed.redisson.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.*;
import com.distributed.redis.lock.UniqueLockUtil;
import com.distributed.redisson.mapper.RedissonMapper;
import com.distributed.redisson.model.CustomerVO;
import com.distributed.redisson.model.Param;
import com.distributed.redisson.service.RedissonSerivce;
import org.apache.commons.codec.digest.DigestUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-18 15:57
 **/

@Service
public class RedissonSerivceImpl implements RedissonSerivce {

    private static Logger logger = LoggerFactory.getLogger(RedissonSerivceImpl.class);

    @Autowired
    private RedissonMapper redissonMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    private static final ThreadLocal<Param> threadLocal = new ThreadLocal();

    private  ThreadLocal<String> threadLocals = new ThreadLocal();

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    @Retryable(value = {IllegalArgumentException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000L,
            multiplier = 1))
    public Long create(CustomerVO customerVO) {
        RLock lock = null;
        Long customerId = 0L;
        try {
            Long counter = redisTemplate.opsForValue().increment("COUNTER", 1);
            lock = redissonClient.getLock("customer");
            logger.info("Request Thread - " + counter + "[" + customerVO.getCertiCode() + "] locked and begun...");
            boolean flag = lock.tryLock(1000, 2000, TimeUnit.MILLISECONDS);
            if (!flag) {
                logger.info("Request Thread - " + counter + "开始重试");
                throw new IllegalArgumentException("开始重试");
            }
            //先根据5要素查询
            List<CustomerVO> customerVOS = redissonMapper.query(customerVO);
            int size = customerVOS.size();
            if (size == 1) {
                CustomerVO customer = customerVOS.get(0);
                customerVO.setCustomerId(customer.getCustomerId());
                redissonMapper.update(customerVO);
                logger.info("Request updateupdateupdateupdateupdateupdateupdateupdateupdateupdate");
                return customer.getCustomerId();
            } else if (size == 2) {
                throw new IllegalArgumentException(" duplicate customer info");
            } else {
                logger.info("Request createcreatecreatecreatecreatecreatecreatecreatecreatecreate");
                //生成customerId
                Random ra = new Random();
                customerId = Integer.toUnsignedLong(ra.nextInt(99999999));
                customerVO.setCustomerId(customerId);
                redissonMapper.create(customerVO);
            }
        } catch (InterruptedException e) {
            logger.info("========================================");
        } finally {
            try {
                lock.unlock();
            } catch (IllegalMonitorStateException e) {
                logger.info("--------------------------" + customerVO.getCertiCode());
            }
            logger.info("Request Thread - " + "[" + customerVO.getCertiCode() + "] unlocked...");
        }
        logger.info("Request Thread - " + "[" + customerVO.getCertiCode() + "] ended successfully...");
        return customerId;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Long createByRetryer(CustomerVO customerVO) {
        Param param = new Param();
        threadLocal.set(param);
        Retryer<Long> retryer = RetryerBuilder.<Long>newBuilder()
                .retryIfExceptionOfType(IllegalArgumentException.class)
                .withWaitStrategy(WaitStrategies.fixedWait(400, TimeUnit.MILLISECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            return retryer.call(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    Param param = threadLocal.get();
                    Long customerId = 0L;
                    try {
                        String key =
                                DigestUtils.md5Hex(customerVO.getFirstName() + customerVO.getCertiCode() + customerVO.getCertiType() + customerVO.getGender() + customerVO.getBirthdate());
                        param.setKey(key);

                        Long counter = redisTemplate.opsForValue().increment("COUNTER", 1);
                        //param.setLockkk(redissonClient.getLock(key));
                        logger.info("Request Thread - " + counter + "[" + customerVO.getCertiCode() + "] locked and " + "begun" + "...");
                        boolean locked = UniqueLockUtil.lockWithMilli(key, 80000L);
                        //param.setLocked(param.getLockkk().tryLock(2000, TimeUnit.MILLISECONDS));
                        param.setLocked(locked);
                        logger.info(customerVO.getCertiCode() + "锁的名称为==================" );
                        if (!param.isLocked()) {

                            logger.info("Request Thread - " + customerVO.getCertiCode() + "开始重试");
                            throw new IllegalArgumentException("开始重试");
                        }
                        if (param.isLocked()) {
                            //先根据5要素查询
                            List<CustomerVO> customerVOS = redissonMapper.query(customerVO);
                            int size = customerVOS.size();
                            if (size == 1) {
                                CustomerVO customer = customerVOS.get(0);
                                customerVO.setCustomerId(customer.getCustomerId());
                                redissonMapper.update(customerVO);
                                logger.info(customerVO.getCertiCode() + "   Request " +
                                        "updateupdateupdateupdateupdateupdateupdateupdateupdateupdate");
                                return customer.getCustomerId();
                            } else if (size == 2) {
                                throw new IllegalArgumentException(" duplicate customer info");
                            } else {
                                logger.info(customerVO.getCertiCode() + "Request " +
                                        "createcreatecreatecreatecreatecreatecreatecreatecreatecreate");
                                //生成customerId
                                Random ra = new Random();
                                customerId = Integer.toUnsignedLong(ra.nextInt(99999999));
                                customerVO.setCustomerId(customerId);
                                redissonMapper.create(customerVO);
                            }
                            param.setPartyId(customerId);
                        }
                    } catch (IllegalMonitorStateException e) {
                        logger.info("-------------------------------------------" + customerVO.getCertiCode());
                    } finally {
                        //TODO 这里异步会发生现成切换，所以用threadlocal会是不正确
                        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                                                                                      @Override
                                                                                      public void afterCommit() {
                                                                                          Param params =
                                                                                                  threadLocal.get();
                                                                                          if (params.isLocked()) {
                                                                                              //params.getLockkk()
                                                                                              // .unlock();
                                                                                              boolean isRelease =
                                                                                                      UniqueLockUtil.release(param.getKey());
                                                                                              redisTemplate.opsForValue().set(params.getKey(), params.getPartyId().toString());
                                                                                              logger.info(isRelease+
                                                                                                      "--------------------------" + customerVO.getCertiCode());
                                                                                          }
                                                                                          threadLocal.remove();
                                                                                      }
                                                                                  }
                        );

                    }
                    logger.info("Request Thre" +
                            "ad - " + "[" + customerVO.getCertiCode() + "] ended successfully...");
                    return customerId;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return 0L;
        } catch (RetryException e) {
            logger.info("重试多次之后 还是没有获取到锁的" + customerVO.getCertiCode());
            return 0L;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class ,propagation = Propagation.REQUIRED)
    public Long Transactional(CustomerVO customerVO) {
        CustomerVO customerVO1=new CustomerVO();
        customerVO1.setFirstName("11111");
        customerVO1.setCertiCode("11111");
        customerVO1.setGender("M");
        customerVO1.setBirthdate(new Date());
        Random ra = new Random();
        Long customerId = Integer.toUnsignedLong(ra.nextInt(99999999));
        customerVO1.setCustomerId(customerId);
        redissonMapper.create(customerVO1);
        Long partyId=applicationContext.getBean(RedissonSerivceImpl.class).createByRetryer(customerVO);
        int a=10/0;
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk");
        return partyId;
    }

    @Transactional(rollbackFor = Exception.class ,propagation = Propagation.REQUIRES_NEW)
    public void Transactional1(){
        try {
            CustomerVO customerVO=new CustomerVO();
            customerVO.setFirstName("2222");
            customerVO.setCertiCode("33333");
            customerVO.setGender("M");
            customerVO.setBirthdate(new Date());
            Random ra = new Random();
            Long customerId = Integer.toUnsignedLong(ra.nextInt(99999999));
            customerVO.setCustomerId(customerId);
            redissonMapper.create(customerVO);
            Transactional2();
        } finally {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                                                                          @Override
                                                                          public void afterCommit() {
                                                                              System.out.println("2222transaction " +
                                                                                      "commited");
                                                                          }
                                                                      }
            );
        }
    }
    @Override
    public void Transactional2(){
        try {
            threadLocals.set("333333333");
            CustomerVO customerVO=new CustomerVO();
            customerVO.setFirstName("2222");
            customerVO.setCertiCode("33333");
            customerVO.setGender("M");
            customerVO.setBirthdate(new Date());
            Random ra = new Random();
            Long customerId = Integer.toUnsignedLong(ra.nextInt(99999999));
            customerVO.setCustomerId(customerId);
            System.out.println("33333333333333");
        } finally {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                                                                          @Override
                                                                          public void afterCommit() {
                                                                              System.out.println(threadLocals.get());
                                                                          }
                                                                      }
            );
        }
    }

    public static void main(String[] args) {
        Random ra = new Random();
        Long customerId = Integer.toUnsignedLong(ra.nextInt(99999999));
        System.out.println(customerId);
        String key = "142703199312133613";
        key = DigestUtils.md5Hex(key);
        System.out.println(key);

    }
}
