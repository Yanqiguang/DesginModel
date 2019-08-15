package com.arithmetic;

/**
 * @program: DesignModel
 * @description: 最长公共前缀
 * @author: lester.yan
 * @create: 2019-03-21 15:39
 **/

public class LongestCommonPrefix {

    private static String longestCommonPrefix(String[] strs) {
            String result = "";
            String prefix = "";
            if (strs.length > 0) {
                char[] chars = strs[0].toCharArray();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < chars.length; i++) {
                    prefix = stringBuffer.append(chars[i]).toString();
                    boolean flag = true;
                    for (int j = 0; j < strs.length; j++) {
                        if (!strs[j].startsWith(prefix)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        result = prefix;
                    }else{
                        break;
                    }
                }
            }

            return result;
    }

    public static void main(String[] args) {
        String[] strs = {"asdbc", "abhfgh", "abogh"};
        System.out.println(longestCommonPrefix(strs));
    }
}
