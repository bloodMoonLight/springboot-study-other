package com.example.springboot.other.springmvc.exceptioncustom.exceptionadvice;

import com.example.springboot.other.springmvc.exceptioncustom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(
        //指定拦截的包
        basePackages = {"com.example.springboot.other.springmvc.controller.*"},
        //限定被标注为@Controller和@RestController的类才被拦截
        annotations = {Controller.class, RestController.class}
)
public class VoControllerAdvice {
    // 异常处理，可以定义异常类型进行拦截
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    //定义为服务器状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> exception(HttpServletRequest request, NotFoundException ex){
        Map<String,Object> map = new HashMap<>(16);
        //获取异常信息
        map.put("code",ex.getCode());
        map.put("message",ex.getCustomMsg());
        return map;
    }
}
