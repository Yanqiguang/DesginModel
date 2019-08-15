package com.jvm.gc;

/**
 * @program: DesignModel
 * @description: GC 对象并没被回收
 * @author: lester.yan
 * @create: 2019-06-23 10:58
 **/

public class ReferenceGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[5 * _1MB];

    public static void main(String[] args) {
        ReferenceGC A = new ReferenceGC();
        ReferenceGC B = new ReferenceGC();
        A.instance = B;
        B.instance = A;

        A = null;
        B = null;
        System.gc();
    }
}
