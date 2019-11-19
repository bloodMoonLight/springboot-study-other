package com.example.study.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RedisApplication {

    @Autowired
    private RedisTemplate redisTemplate = null;

    /**
     * 初始化redisTemplate方法
     */
    @PostConstruct
    private void init(){
        initRedistemplate();
    }

    private void initRedistemplate(){
      RedisSerializer redisSerializer =  redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        /*redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);*/
        redisTemplate.setHashKeySerializer(redisSerializer);


    }



    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
