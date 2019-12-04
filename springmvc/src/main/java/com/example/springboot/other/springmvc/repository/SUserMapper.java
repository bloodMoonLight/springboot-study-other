package com.example.springboot.other.springmvc.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.other.springmvc.pojo.SUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SUserMapper extends BaseMapper<SUser> {
}
