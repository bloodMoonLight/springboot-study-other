package com.example.study.springboot.redis.redisController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/redis")
public class RedisController {
    /**
     * 这个使用默认Jdk序列化的
     */
    @Autowired
    private RedisTemplate redisTemplate = null;
    /**
     * 这个使用String序列化
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @ResponseBody
    @RequestMapping("/stringAndHash")
    public Map<String,Object> testStringAndHash(){
        //这里使用默认的jdk序列化器
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("int_key","1");
        //这里使用设置的String的序列化器
        stringRedisTemplate.opsForValue().set("int","1");
        //使用运算
        stringRedisTemplate.opsForValue().increment("int",1);
        // 减1操作RedisTemplate不支持，所以需要获取底层连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        //执行减一操作(对该键的值进行减一操作)
        jedis.decr("int");
        Map<String, String> hash = new HashMap<>();
        hash.put("fild1","value1");
        hash.put("fild2","value2");
        // 存入一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash",hash);
        //新增一个字段
        stringRedisTemplate.opsForHash().put("hash","fild3","fild3");
        //绑定一个散列操作的key，对该key进行连续操作
        BoundHashOperations  hashOps = stringRedisTemplate.boundHashOps("hash");
        //执行删除操作，该方法可以输入多个key
        hashOps.delete("fild1","fild2");
        hashOps.put("fild4","fild4");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success",true);
        return hashMap;
    }



}
