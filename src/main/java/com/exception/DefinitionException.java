package com.exception;

/**
 * @program: DesignModel
 * @description: 自定义异常
 * @author: lester.yan
 * @create: 2018-12-13 16:56
 **/

public class DefinitionException extends Exception {


    public DefinitionException() {
    }

    public DefinitionException(String message) {
        super("我的自定义异常的信息是"+message);
    }

    public DefinitionException(String message, Throwable cause) {
        super(message, new Throwable());
    }

    public DefinitionException(Throwable cause) {
        super(cause);
    }

    public DefinitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
