package com.test;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-05-17 14:09
 **/

public class test {


    public static void main(String[] args) throws Exception {
        Date startDate = DateUtils.parseDate("2000-12-13", "YYYY-MM-dd");
        Date endDate = DateUtils.parseDate("2019-5-17", "YYYY-MM-dd");
        Long diff = endDate.getTime() - startDate.getTime();
        Double year = Math.ceil(diff / (1000 * 24 * 60 * 60) / 365);
        int years = year.intValue();
        BigDecimal benjin = new BigDecimal(10000);
        BigDecimal lixi = BigDecimal.ZERO;
        BigDecimal lilv = new BigDecimal(0.04015);
        for (int i = 0; i < years; i++) {
            lixi = benjin.multiply(lilv);
            benjin=benjin.add(lixi);
        }
        System.out.println(benjin.add(lixi));
    }
}
