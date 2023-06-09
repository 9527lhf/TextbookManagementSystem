package com.lhf.library.service.ex;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 18:32
 */
//Textbookorder 余额不足异常
public class InsufficientBalance extends ServiceException{
    public InsufficientBalance() {
        super();
    }

    public InsufficientBalance(String message) {
        super(message);
    }

    public InsufficientBalance(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientBalance(Throwable cause) {
        super(cause);
    }

    protected InsufficientBalance(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}