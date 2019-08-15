package com.designmodel.strategy;

import java.math.BigDecimal;

/**
 * @program: DesignModel
 * @description: Intermediate 中级会员
 * @author: lester.yan
 * @create: 2018-12-04 20:14
 **/

public class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public BigDecimal getPrice(BigDecimal booksPrice) {
        return booksPrice.multiply(new BigDecimal("0.8"));
    }
}
