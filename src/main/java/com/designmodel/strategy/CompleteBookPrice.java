package com.designmodel.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: DesignModel
 * @description: 计算书籍价格
 * @author: lester.yan
 * @create: 2018-12-04 20:17
 **/

public class CompleteBookPrice {

    /**
     * //持有一个具体的策略对象
     */
    private MemberStrategy strategy;

    public CompleteBookPrice(MemberStrategy strategy) {
        this.strategy = strategy;
    }

    public BigDecimal getPrice(BigDecimal bookPrice) {
        return this.strategy.getPrice(bookPrice);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(0);
        map.put("list", new ArrayList<>().add("1"));
        if ( map.size() == 1) {
            return;
        }
        List<String> list1 = (ArrayList) map.get("list2");
        MemberStrategy memberStrategy = new AdvancedMemberStrategy();
        CompleteBookPrice price = new CompleteBookPrice(memberStrategy);
        BigDecimal finalPrice = price.getPrice(new BigDecimal("60"));
        System.out.println(finalPrice);
    }
}
