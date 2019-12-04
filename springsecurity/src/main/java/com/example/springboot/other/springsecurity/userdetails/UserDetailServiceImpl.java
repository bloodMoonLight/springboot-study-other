package com.example.springboot.other.springsecurity.userdetails;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.other.springsecurity.pojo.Role;
import com.example.springboot.other.springsecurity.pojo.User;
import com.example.springboot.other.springsecurity.reposity.RoleMapper;
import com.example.springboot.other.springsecurity.reposity.UserMapper;
import com.example.springboot.other.springsecurity.reposity.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper mapper;

    /**
     * 定义返回UserDetails对象
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取数据库用户信息
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", username));
        // 获取该用户名下的所有角色
        List<Role> roleList = mapper.findRoleByUserName(username);
        System.out.println("经过了验证");
        return changToUser(user,roleList);
    }


    /**
     * 创建UserDetails对象，赋予查询出来的用户名、密码与Role（权限）
     * @param user
     * @param roleList
     * @return
     */
    private UserDetails changToUser(User user,List<Role> roleList){
        //权限列表
        List<GrantedAuthority> authorityList = new ArrayList<>();
        //赋予查询到的角色
        roleList.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(authority);
        });
        //创建UserDetails对象，设置用户名、密码和权限
        UserDetails userDetails =  new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPwd(),authorityList);
        return userDetails;
    }
}
