package com.example.springboot.other.springmvc.interceptors;


import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户的自定义拦截器，
 * 有了该拦截器SpringMVC并不会发现他，还需要对它进行注册才能够拦截处理器，
 * 需要声明一个配置来来实现WebMvcConfigurer，并重写addInterceptors方法，进行注册拦截器
 */
public class UserInterceptor implements HandlerInterceptor {
    /**
     * 处理器执行前方法，就是在Controller方法执行前执行的方法
     * @param request 请求对象
     * @param response 响应对象
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是控制器方法
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod =  (HandlerMethod) handler;
            Integer id = Integer.parseInt(request.getParameter("id"));
            id += 10;
            System.out.println("执行方法之后的参数为:" + id);

        }
        System.out.println("处理器前执行");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("处理器后的方法");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("处理器完成方法");
    }
}
