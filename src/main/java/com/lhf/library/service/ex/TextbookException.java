package com.lhf.library.service.ex;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 21:11
 */
public class TextbookException extends ServiceException{
    public TextbookException() {
        super();
    }

    public TextbookException(String message) {
        super(message);
    }

    public TextbookException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextbookException(Throwable cause) {
        super(cause);
    }

    protected TextbookException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}