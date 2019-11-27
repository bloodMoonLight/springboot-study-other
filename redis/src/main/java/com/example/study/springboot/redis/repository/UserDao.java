package com.example.study.springboot.redis.repository;

import com.example.study.springboot.redis.pojo.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
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
    int  insertUser(User user);

    /**
     * 修改单个用户
     * @param user
     * @return
     */
    int updateUser(User user);

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
    List<User> findUsers(@Param("userName") String username, @Param("note")String note);
}
