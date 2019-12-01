package com.example.springboot.other.springmvc.controller;

import com.example.springboot.other.springmvc.pojo.User;
import com.example.springboot.other.springmvc.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月1日09:36:09 9:36
 * Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/details")
    public ModelAndView details(Long id){
        User user = userMapper.selectById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/details");
        mv.addObject("user",user);
        return mv;
    }

    @RequestMapping("/detailsForJson")
    public ModelAndView detailsForJson(Long id){
        User user = userMapper.selectById(id);
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);
        modelAndView.addObject("user",user);
        return modelAndView;

    }


    @RequestMapping("/no")
    @ResponseBody
    public Map<String,Object> noAnnotation(Integer intval,Long longval,String str){
        Map<String,Object> map = new HashMap<>();
        map.put("int",intval);
        map.put("Long",longval);
        map.put("string",str);
        return map;
    }

    /**
     * 使用注解传参，其中需要注意的是，注解传参不能为null，不然会出现400
     * @Description TODO
     * @params
     * @Author 张鸿志
     * @Date 2019/12/1 20:35
     * @Return
     **/
    @ResponseBody
    @RequestMapping("/yes")
    public Map<String,Object> yesAnnotation(@RequestParam("int_val") Integer intval,
                                            @RequestParam("long_val") Long longval,
                                            @RequestParam(value = "str_val",required = false) String str){
        Map<String,Object> map = new HashMap<>();
        map.put("int",intval);
        map.put("Long",longval);
        map.put("string",str);
        return map;
    }


    @RequestMapping("/array")
    @ResponseBody
    public Map<String,Object> requestArray(int [] intarr,Long [] longarr,String [] strarr   ){
        Map<String,Object> map = new HashMap<>();
        map.put("int",intarr);
        map.put("Long",longarr);
        map.put("string",strarr);
        return map;
    }

    @GetMapping("/add")
    public String add(){
        return "user/add";
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public User insert(@RequestBody User user){
        userMapper.insert(user);
        System.out.println("返回的zhujianid"+user.getId());
        return user;

    }


}
