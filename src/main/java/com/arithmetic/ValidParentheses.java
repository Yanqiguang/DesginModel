package com.arithmetic;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-03-21 16:11
 **/

public class ValidParentheses {
    public static boolean isValid(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s.replace("{}", "");
            s.replace("[]", "");
            s.replace("()", "");
        }
        return s.equals("");
    }


    public static void main(String[] args) {
        person asd=new ValidParentheses().new person();
        asd.setName(null);
        System.out.println(StringUtils.isEmpty(asd.getName()));
    }


    class person{
        private String name ;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
