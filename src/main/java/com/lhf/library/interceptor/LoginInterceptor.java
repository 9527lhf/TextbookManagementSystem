package com.lhf.library.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 14:48
 */
//登录拦截器,未登录无法访问

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String code = (String) request.getSession().getAttribute("code");
        if (code == null) {
            response.sendRedirect("./login.html");
            return false;
        }
        return true;
    }
}