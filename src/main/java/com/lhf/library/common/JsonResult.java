package com.lhf.library.common;

import java.io.Serializable;

/**
 * json格式的数据响应
 */
public class JsonResult<V> implements Serializable {
    //  状态码
    private Integer state;
    //  描述信息
    private String message;
    //  数据
    private Object data;

    public JsonResult(){}

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e){
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, Object data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
