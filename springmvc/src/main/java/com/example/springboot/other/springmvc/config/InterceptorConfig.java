package com.example.springboot.other.springmvc.config;

import com.example.springboot.other.springmvc.interceptors.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义拦截器注册配置文件
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 注册拦截器方法，并指定拦截请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器到SpringMVC机制，然后会返回一个拦截器注册器对象
        InterceptorRegistration interceptorRegistration =
                registry.addInterceptor(new UserInterceptor());
        //指定拦截匹配机制，限制拦截机拦截请求，在下列中，只会拦截interceptor/后的所有请求，非interceptor的请求不回拦截
        interceptorRegistration.addPathPatterns("/interceptor/*");
    }
}
