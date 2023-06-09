package com.lhf.library.controller;

import com.lhf.library.common.JsonResult;
import com.lhf.library.service.ex.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author : lihuifeng
 * @create 2023/5/8 19:47
 */
public class ExceptionController {
    @ExceptionHandler({ServiceException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UserException) {
            result.setState(4000);
            result.setMessage(e.getMessage());
        } else if (e instanceof OrderException) {
            result.setState(6000);
            result.setMessage(e.getMessage());
        } else if (e instanceof InsufficientBalance) {
            result.setState(5001);
            result.setMessage(e.getMessage());
        } else if (e instanceof PermissionsException) {
            result.setMessage(e.getMessage());
            result.setState(5002);
        }
        return result;
    }
}