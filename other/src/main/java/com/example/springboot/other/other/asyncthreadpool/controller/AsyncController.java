package com.example.springboot.other.other.asyncthreadpool.controller;

import com.example.springboot.other.other.asyncthreadpool.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/page")
    @ResponseBody
    public String asyncPage(){
        System.out.println("请求线程名称：" + "【"+ Thread.currentThread().getName() + "】");
        asyncService.generateReport();
        return "async";
    }

}
