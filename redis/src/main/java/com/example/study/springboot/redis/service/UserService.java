package com.example.study.springboot.redis.service;

import com.example.study.springboot.redis.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 获取单个用户
     * @param id
     * @return
     */
    User getUser(Long id);

    /**
     * 保存单个用户
     * @param user
     * @return
     */
    User  insertUser(User user);

    /**
     * 修改单个用户
     * @param user
     * @return
     */
    User updateUser(Long id,String userName);

    /**
     * 删除单个用户
     * @param id
     * @return
     */
    int deleteUser(Long id);

    /**
     * 查询用户，指定mybatis的参数名
     * @param username
     * @param note
     * @return
     */
    List<User> findUsers(String username,String note);
}
