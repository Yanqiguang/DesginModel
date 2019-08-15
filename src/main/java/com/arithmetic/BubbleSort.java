package com.arithmetic;

import java.util.List;

import org.assertj.core.util.Lists;

/**
 * @program: DesignModel
 * @description: Bubble Sort 时间复杂度为O(n^2) 空间复杂度为O（1）
 * @author: lester.yan
 * @create: 2019-03-18 16:47
 **/

public class BubbleSort {

    public static List<Integer> sort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            //内部遍历
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j)>list.get(j+1)){
                    int temp=list.get(j+1);
                    list.set(j+1,list.get(j));
                    list.set(j,temp);
                }
            }
            System.out.println("第"+i+"次排序："+list.toString());
        }
        return list;
    }


    public static void main(String[] args) {
        List<Integer> list= Lists.newArrayList();
        list.add(37);
        list.add(41);
        list.add(87);
        list.add(12);
        list.add(51);
        sort(list);
    }
}

