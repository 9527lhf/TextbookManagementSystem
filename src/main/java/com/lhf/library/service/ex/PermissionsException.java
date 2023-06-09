package com.lhf.library.service.ex;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 19:33
 */
//权限异常
public class PermissionsException extends ServiceException{
    public PermissionsException() {
        super();
    }

    public PermissionsException(String message) {
        super(message);
    }

    public PermissionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionsException(Throwable cause) {
        super(cause);
    }

    protected PermissionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}