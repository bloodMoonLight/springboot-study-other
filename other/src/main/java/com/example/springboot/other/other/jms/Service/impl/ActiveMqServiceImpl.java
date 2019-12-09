package com.example.springboot.other.other.jms.Service.impl;

import com.example.springboot.other.other.jms.Service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqServiceImpl implements ActiveMqService {
    /***
     * 由SpringBoot自动生成的消息中间件的模板对象
     */
    @Autowired
    private JmsTemplate jmsTemplate = null;

    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息【"+ message + "】");
        jmsTemplate.convertAndSend(message);
        //自定义发送地址
        //jmsTemplate.convertAndSend("your-destination",message);
    }

    @Override
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMsg(String message) {
        System.out.println("接收到消息：【" + message+ "】");
    }
}
