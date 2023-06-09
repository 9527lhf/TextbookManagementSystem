package com.lhf.library.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lihuifeng
 * @Create 2023/5/31 14:52
 */
// 实现 WebMvcConfigurer 不会导致静态资源被拦截
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置白名单  存放在一个List集合
        List<String> patterns = new ArrayList<>();
        patterns.add("/users/**");
        patterns.add("/login.html");
        patterns.add("/register.html");
        patterns.add("/css/**");
        patterns.add("/font-awesome/**");
        patterns.add("/fonts/**");
        patterns.add("/form-builder/**");
        patterns.add("/fullavatareditor/**");
        patterns.add("/js/**");
        patterns.add("/img/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(patterns);
    }
}