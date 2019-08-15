package com.arithmetic;

/**
 * @program: DesignModel
 * @description: 回文数 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * @author: lester.yan
 * @create: 2019-03-21 14:11
 **/

public class PalindromeNumber {

    private static boolean isPalindromeNumber(int x) {
        boolean flag = false;
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        if (chars[0] == '-') {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuffer.append(chars[i]);
        }
        if (str.equals(stringBuffer.toString())) {
            return true;
        }
        return flag;
    }


    private static boolean isPalindromeNumber1(int x) {
        boolean flag = false;
        int start=x;
        int number = 0;
        if (x < 0) {
            return flag;
        }
        while (x != 0) {

            number = number * 10 + x % 10;
            x /= 10;
        }
        if (number == start) {
            return true;
        }
        return flag;
    }


    public static void main(String[] args) {
        System.out.println(isPalindromeNumber1(1463847412));
    }
}
