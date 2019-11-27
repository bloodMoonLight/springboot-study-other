package com.example.study.springboot.redis.redislistener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Redis的消息监听器
 */
@Component
public class RedisMessageLitener implements MessageListener {
    /**
     * onMessage方法为 得到消息后的处理方法，
     * @param message  Redis发送过来的消息
     * @param pattern  渠道名
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //消息体
        String body = new String(message.getBody());
        // 渠道名称
        String topic = new String(pattern);
        switch (topic){
            case "topic1":
                System.out.println(body + "topic");
                System.out.println(topic);
                break;
            case "topic2":
                System.out.println(body + "topic1");
                System.out.println(topic);
                break;
            case "topic3":
                System.out.println(body + "topic2");
                System.out.println(topic);
                break;
            case "topic4":
                System.out.println(body + "topic3");
                System.out.println(topic);
                break;
            case "topic5":
                System.out.println(body + "topic4");
                System.out.println(topic);
                break;
        }

    }
}
