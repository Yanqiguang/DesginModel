package com.designmodel.strategy;

import java.math.BigDecimal;

/**
 * @program: DesignModel
 * @description: primary 初级会员
 * @author: lester.yan
 * @create: 2018-12-04 20:11
 **/

public class PrimaryMemberStrategy implements MemberStrategy {

    @Override
    public BigDecimal getPrice(BigDecimal booksPrice) {
        return booksPrice.multiply(new BigDecimal("0.9"));
    }
}
