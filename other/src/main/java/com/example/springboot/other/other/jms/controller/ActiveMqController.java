package com.example.springboot.other.other.jms.controller;

import com.example.springboot.other.other.jms.Service.ActiveMqService;
import com.example.springboot.other.other.jms.Service.ActiveMqUserService;
import com.example.springboot.other.other.jms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/activemq")
public class ActiveMqController {


    @Autowired
    private ActiveMqService activeMqService;

    @Autowired
    private ActiveMqUserService activeMqUserService;

    @ResponseBody
    @GetMapping("/msg")
    public Map<String,Object> msg(String message){
        activeMqService.sendMsg(message);
        return result(true,message);
    }

    @ResponseBody
    @GetMapping("/user")
    public Map<String,Object> sendUser(Long id,String userName,String note){
        User u = new User(id,userName,note);
        activeMqUserService.sendUser(u);
        return result(true,u);
    }


    private Map<String,Object> result(boolean success,Object message){
        Map<String,Object> map =new HashMap<>();
        map.put("success",success);
        map.put("message",message);
        return map;

    }

}
