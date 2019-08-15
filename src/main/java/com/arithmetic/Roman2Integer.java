package com.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: DesignModel
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * @description:
 * @author: lester.yan
 * @create: 2019-03-21 14:53
 **/

public class Roman2Integer {


    private static int Roman2Integer(String str) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chars = str.toCharArray();
        int result = 0;
        for (int i = 0; i <= chars.length - 1; i++) {
            if (i==chars.length-1){
                break;
            }
            if (map.get(chars[i]) >= map.get(chars[i + 1])) {
                result += map.get(chars[i]);
            } else {
                result -= map.get(chars[i]);
            }
        }
        result+=map.get(chars[chars.length-1]);
        return result;
    }


    public static void main(String[] args) {
        System.out.println(Roman2Integer("MCMXCIV"));
    }
}
