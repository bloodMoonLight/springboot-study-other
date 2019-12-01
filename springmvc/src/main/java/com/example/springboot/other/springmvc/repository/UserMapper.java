package com.example.springboot.other.springmvc.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.other.springmvc.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月1日09:34:52 9:34
 * Version 1.0
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
}
