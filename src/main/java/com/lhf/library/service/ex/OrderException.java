package com.lhf.library.service.ex;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 15:52
 */
public class OrderException extends ServiceException{
    public OrderException() {
        super();
    }

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(cause);
    }

    protected OrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}