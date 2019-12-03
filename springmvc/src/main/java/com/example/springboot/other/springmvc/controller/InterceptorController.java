package com.example.springboot.other.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {
    @GetMapping("/aaaa")
    public String start(Integer id,String userName){
        System.out.println("执行拦截器后的id参数"+ id);
        System.out.println("执行拦截器后的userName参数" + userName);
        System.out.println("执行处理器逻辑");
        return "/user/welcome";
    }

}
