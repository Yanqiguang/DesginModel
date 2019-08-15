package com.distributed.redis;

import java.util.UUID;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-02-01 16:46
 **/

public class ThreadIdUtils {

    private static final ThreadLocal<String> threadIdThreadLocal = new ThreadLocal<String>() {
        protected String initialValue() {
            return UUID.randomUUID().toString();
        }
    };

    public ThreadIdUtils() {
    }

    public static String get() {
        return (String) threadIdThreadLocal.get();
    }
}
