package com.example.springboot.other.springmvc.validator;

import com.example.springboot.other.springmvc.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o == null){
            errors.rejectValue("",null,"用户不能为空");
            return;
        }
        //强制转换
        User user = (User) o;
        if(StringUtils.isEmpty(user.getUserName())){
            errors.rejectValue("userName",null,"用户名不能为空");
        }
    }
}
