package com.whz.Config;

import com.whz.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MyPC
 * @description
 * @since 2024/8/13 23:02
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /*
     * @description 注册拦截器
     * @param null
     * @return null
     * @date
    **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录接口和注册接口 不进行拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login", "/user/register");
    }
}
