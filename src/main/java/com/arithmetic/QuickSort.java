package com.arithmetic;

import java.util.List;

import org.assertj.core.util.Lists;

/**
 * @program: DesignModel
 * @description: Quick Sort 时间复杂度 空间复杂度为O(1)
 * @author: lester.yan
 * @create: 2019-03-18 17:14
 **/

public class QuickSort {

    public static List sort(List<Integer> list,int _left ,int _right) {
        int left = _left;
        int right = _right;
        int temp = 0;
        if (left < right) {
            temp = list.get(left);
            while (left != right) {
                while (right > left && list.get(right) >= temp) {
                    right--;
                    list.set(left,list.get(right));
                }
                while (right < left && list.get(left) <= temp) {
                    left++;
                    list.set(right,list.get(left));
                }
            }
            list.set(right,temp);
            sort(list,_left,left-1);
            sort(list,right,right+1);
        }
        System.out.println("排序结果："+list.toString());
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list= Lists.newArrayList();
        list.add(37);
        list.add(41);
        list.add(87);
        list.add(12);
        list.add(51);
        sort(list,0,list.size()-1);
    }

}
