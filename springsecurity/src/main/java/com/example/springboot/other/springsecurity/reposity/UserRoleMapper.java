package com.example.springboot.other.springsecurity.reposity;

import com.example.springboot.other.springsecurity.pojo.Role;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    /**
     * 根据用户名来查找角色列表
     * @param userName
     * @return
     */
    List<Role> findRoleByUserName(@Param("userName") String userName);

}
