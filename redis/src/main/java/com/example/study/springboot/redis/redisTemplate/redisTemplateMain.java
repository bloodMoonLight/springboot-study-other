package com.example.study.springboot.redis.redisTemplate;

import com.example.study.springboot.redis.config.RedisConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

/**
 * redis测试类
 * @ClassName redisTemplateMain
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年11月11日20:54:52 20:54
 * Version 1.0
 **/
public class redisTemplateMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
        // 获取RedisTmeplate对象
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        //使用SessionCallback和RedisCallback连续操作key
        redisTemplate.execute((RedisConnection ro) -> {
            ro.set("kaokao".getBytes(),"caocao".getBytes());
            return  null;
        });

        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {

                return null;
            }
        });
    }

}
