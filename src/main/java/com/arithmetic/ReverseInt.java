package com.arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: DesignModel
 * @description: 整数反转、给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * @author: lester.yan
 * @create: 2019-03-20 16:05
 **/

public class ReverseInt {


    private static int reverse(int param) {
        String paramStr = String.valueOf(param);
        char[] paramChars = paramStr.toCharArray();
        //判断正负
        boolean flag = paramChars[0] == '-' ? true : false;
        int end = flag ? 1 : 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = paramChars.length - 1; i >= end; i--) {
            stringBuffer.append(paramChars[i]);
        }
        int result = 0;
        try {
            result = Integer.parseInt(stringBuffer.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
        return result = flag ? (-result) : result;
    }

    /**
     * 在反转整数的时候，通过取个位数依次在进行*10 再相加
     *
     * @param number
     * @return
     */
    private static int reverse1(int number) {
        int result = 0;
        while (number != 0) {
            int last = number % 10;
            number = number / 10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && last > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && last < -8)) {
                return 0;
            }
            result = result * 10 + last;
        }
        return result;
    }


    public static void main(String[] args) {
        int result = reverse1(-1463847412);
        System.out.println(result);
    }


}
