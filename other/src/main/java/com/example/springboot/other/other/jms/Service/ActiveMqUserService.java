package com.example.springboot.other.other.jms.Service;

import com.example.springboot.other.other.jms.pojo.User;

public interface ActiveMqUserService {

    void sendUser(User user);

    void receiveUser(User user);
}
