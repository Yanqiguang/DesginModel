package com.application;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-09 13:34
 **/

public class Test {

    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        StringBuffer order=new StringBuffer();
        for (Object str : list){
            order.append(str);
            order.append("、");
        }
        String as=order.substring(0,order.length()-1);
        System.out.println(as);
    }
}
