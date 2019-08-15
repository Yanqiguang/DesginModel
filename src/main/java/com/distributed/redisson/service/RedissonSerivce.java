package com.distributed.redisson.service;

import com.distributed.redisson.model.CustomerVO;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-18 15:56
 **/

public interface RedissonSerivce {

    Long create(CustomerVO customerVO);

    Long createByRetryer(CustomerVO customerVO);

    Long Transactional(CustomerVO customerVO);


    void Transactional2();
}
