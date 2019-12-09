package com.example.springboot.other.other.jms.Service;

/**
 * ActiveMq服务接口
 */
public interface ActiveMqService {
    /**
     * 发送消息
     * @param message
     */
    void sendMsg(String message);

    /**
     * 接收消息
     * @param message
     */
    void receiveMsg(String message);
}
