package com.example.springboot.other.springsecurity.securitys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class MySecurityOne extends WebSecurityConfigurerAdapter {

    @Value("${system.user.password.secret}")
    private String secret = null;


    @Autowired
    private UserDetailsService userDetailsService = null;
    /**
     * 用来配置用户签名服务。如用户名、密码、角色
     * 签名服务分为：内存签名，数据库签名，自定义签名
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //密码编译器
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(secret);
        //设置用户密码服务和密码编译器
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * 用来配置过滤器链，可以配置Filter链忽略哪些内容
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 用来配置拦截保护的请求，比如什么请求放行，什么请求需要验证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
