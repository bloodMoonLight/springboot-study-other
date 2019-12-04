package com.example.springboot.other.springsecurity.reposity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.other.springsecurity.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
