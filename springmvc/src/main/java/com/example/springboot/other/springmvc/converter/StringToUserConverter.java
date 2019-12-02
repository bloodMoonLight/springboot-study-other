package com.example.springboot.other.springmvc.converter;

import com.example.springboot.other.springmvc.pojo.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义字符串类型转换为User对象
 * Converter接口 是一种一对一转换器
 */
@Component
public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String s) {
        User user = new User();
        String[] split = s.split("-");
        long id = Long.parseLong(split[0]);
        String userName = split[1];
        String note = split[2];
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        return user;
    }
}
