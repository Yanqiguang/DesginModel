package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: DesignModel
 * @description: 反射
 * @author: lester.yan
 * @create: 2019-05-07 11:28
 **/

public class ReflectInfo {


    public static void main(String[] args) throws Exception {
        String str = "";
        //获取Class 三种方式 以String为例
        Class classBypackage = Class.forName("java.lang.String");
        Class classByclass = String.class;
        Class classByinstance = str.getClass();

        //-----------------------------获取类名-----------------------
        //java.lang.String
        String name = classBypackage.getName();
        //java.lang.String
        String canonicalName = classBypackage.getCanonicalName();
        //String
        String simpleName = classBypackage.getSimpleName();

        //-----------------------------获取属性-----------------------
        //获取所有变量
        Field[] declaredFields=classBypackage.getDeclaredFields();
        //获取所有public变量
        Field[] fields=classBypackage.getFields();
        //根据字段名获取对应变量
        Field declaredFieldhash=classBypackage.getDeclaredField("CASE_INSENSITIVE_ORDER");
        //根据字段名获取对应public变量
        Field fieldhash=classBypackage.getField("CASE_INSENSITIVE_ORDER");

        //-----------------------------获取普通方法-----------------------
        Method[] methods=classBypackage.getMethods();
        //当方法有一个或多个参数时，需要设置参数类型
        Class[] params = new Class[1];
        params[0] = String.class;
        Method method=classBypackage.getMethod("lastIndexOf",params);

        //-----------------------------调用构造方法-----------------------
        //获取实例
        String strInstance=(String)classBypackage.newInstance();
        //获取构造方法
        Constructor constructor=classBypackage.getConstructor(String.class);
        String string=(String)constructor.newInstance("123");

        //-----------------------------调用普通方法-----------------------
        String strInstance1=(String)classBypackage.newInstance()+"ABC";
        Method method1=classBypackage.getDeclaredMethod("indexOf",String.class);
        int result=(int)method1.invoke(strInstance1,"A");

        //-----------------------------修改属性---------------------------
        Field field=classBypackage.getField("hash");
        String strInstance2=(String)classBypackage.newInstance();
        field.setAccessible(true);
        field.set(field,1010101);




    }




}
