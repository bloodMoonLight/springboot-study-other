package com.example.study.springboot.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置类
 * @ClassName RedisConfig
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年11月11日20:11:25 20:11
 * Version 1.0
 **/
@Configuration
public class RedisConfig {

    private RedisConnectionFactory redisConnectionFactory = null;
    /**
     * 创建Redis连接工厂对象
     * @Description TODO
     * @params
     * @Author 张鸿志
     * @Date 2019/11/11 20:24
     * @Return
     **/
    @Bean(name="redisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if(redisConnectionFactory != null){
            return this.redisConnectionFactory;
        }
        // 创建Jedis连接池对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲数
        jedisPoolConfig.setMaxIdle(30);
        //最大连接数
        jedisPoolConfig.setMaxTotal(50);
        //最大毫秒等待数
        jedisPoolConfig.setMaxWaitMillis(2000);
        //创建Jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //获取单机redis的配置
        RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
        rsCfg.setHostName("127.0.0.1");
        rsCfg.setPort(6379);
        // rsCfg.setPassWord();  有就填没有就不填
        this.redisConnectionFactory = connectionFactory;
        return connectionFactory;
    }
    @Bean(name="redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //获取Redis的String序列化器
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        //设置连接工厂
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        //设置key的序列化器
        redisTemplate.setKeySerializer(stringSerializer);
        //设置Value的序列化器
        redisTemplate.setValueSerializer(stringSerializer);
        //设置hashKey的序列化器
        redisTemplate.setHashKeySerializer(stringSerializer);
        //设置hashValue的序列化器
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }




}
