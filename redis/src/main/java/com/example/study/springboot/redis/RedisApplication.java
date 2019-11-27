package com.example.study.springboot.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching     //开启缓存机制
@MapperScan(basePackages = "com.example.study.springboot.redis.repository", annotationClass = Repository.class)
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
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);


    }
    /** Redis连接工厂  */
    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    /**
     * 自定义Redis缓存管理器对象
     * @return
     */
    @Bean(name="redisCacheManager")
    public RedisCacheManager initRedisCacheManager(){
        //redis加锁的写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        // 启动Redis缓存的默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        //禁用前缀
        config = config.disableKeyPrefix();
        // 设置超时时间 Duration.ofMillis()单位为分钟
        config = config.entryTtl(Duration.ofMillis(1));
        RedisCacheManager manager = new RedisCacheManager(writer,config);
        return manager;
    }


    /** Redis消息监听器 */
    @Autowired
    private MessageListener messageListener = null;
    /** 创建任务池 */
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler = null;

    /**
     * 创建任务池对象
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler taskScheduler(){
        if(taskScheduler != null){
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * 定义Redis监听容器
     * @return
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //redis连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置任务池
        container.setTaskExecutor(taskScheduler());
        //定义一个坚挺渠道集合
        List<Topic>  listTopic = new ArrayList<>();
        // 定义监听渠道
        Topic topic = new ChannelTopic("topic1");
        Topic topic1 = new ChannelTopic("topic2");
        Topic topic2 = new ChannelTopic("topic3");
        Topic topic3 = new ChannelTopic("topic4");
        Topic topic4 = new ChannelTopic("topic5");
        listTopic.add(topic);
        listTopic.add(topic1);
        listTopic.add(topic2);
        listTopic.add(topic3);
        listTopic.add(topic4);


        //使用Redis监听器监听Redis的消息
        container.addMessageListener(messageListener,listTopic);
        return container;
    }





    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
