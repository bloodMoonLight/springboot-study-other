package com.example.springboot.other.other.asyncthreadpool.service.impl;

import com.example.springboot.other.other.asyncthreadpool.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
    /**
     * 该方法被@Async注解修饰：
     * @Async注解表示该方法将会被Spring以异步的方式调用
     */
    @Override
    @Async
    public void generateReport() {
        System.out.println("报表线程名称："+ "【" + Thread.currentThread().getName() + "】");
    }
}
