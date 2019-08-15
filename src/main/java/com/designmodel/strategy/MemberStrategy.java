package com.designmodel.strategy;

import java.math.BigDecimal;
/**
 * @program: DesignModel
 * @description: 根据会员级别计算不同费用
 * @author: lester.yan
 * @create: 2018-12-04 20:11
 **/

public interface MemberStrategy {

    BigDecimal getPrice(BigDecimal booksPrice);

}
