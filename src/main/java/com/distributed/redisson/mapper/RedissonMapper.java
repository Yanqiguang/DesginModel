package com.distributed.redisson.mapper;

import java.util.List;

import com.distributed.redisson.model.CustomerVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-18 15:58
 **/
@Mapper
public interface RedissonMapper {

    List<CustomerVO> query(CustomerVO customerVO);

    void update(CustomerVO customerVO);

    void create(CustomerVO customerVO);
}
