package com.designmodel.strategy;

import java.math.BigDecimal;

/**
 * @program: DesignModel
 * @description: Advanced 高级会员
 * @author: lester.yan
 * @create: 2018-12-04 20:15
 **/

public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public BigDecimal getPrice(BigDecimal booksPrice) {
        return booksPrice.multiply(new BigDecimal("0.7"));
    }
}
