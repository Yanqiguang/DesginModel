package com.rest.dto;


import java.util.Date;

import com.alipay.sofa.rpc.common.json.JSON;
import com.distributed.redisson.mapper.RedissonMapper;
import com.distributed.redisson.model.CustomerVO;
import com.rest.RestMethodsFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-28 20:51
 **/
@Service
public class RestMethodsFacadeImpl implements RestMethodsFacade {

    private final static Logger logger = LoggerFactory.getLogger(RestMethodsFacadeImpl.class);

    @Autowired
    private RedissonMapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String jsonTestMethod(Request request) {
        CustomerVO customerVO=new CustomerVO();
        customerVO.setBirthdate(new Date());
        customerVO.setCertiCode("11111111");
        customerVO.setCertiType(1);
        customerVO.setFirstName("111111111");
        customerVO.setGender("M");
        mapper.create(customerVO);
        mapper.update(customerVO);
        return JSON.toJSONString(request);
    }
}
