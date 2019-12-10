package com.example.springboot.other.other.jms.Service.impl;

import com.example.springboot.other.other.jms.Service.ActiveMqUserService;
import com.example.springboot.other.other.jms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqUserServiceImpl implements ActiveMqUserService {
    @Autowired
    private JmsTemplate jmsTemplate  = null;
    //自定义地址
    private static final String myDestination = "my-destination";


    @Override
    public void sendUser(User user) {
        System.out.println("发送消息【" + user +"】");
        //使用自定义地址发送对象
        jmsTemplate.convertAndSend(myDestination,user);
    }

    @Override
    @JmsListener(destination = myDestination)
    public void receiveUser(User user) {
        System.out.println("接收到消息：【" + user + "】");
    }
}
