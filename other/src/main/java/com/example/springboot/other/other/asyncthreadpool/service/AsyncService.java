package com.example.springboot.other.other.asyncthreadpool.service;

/**
 * 包含需要异步执行方法的Service
 */
public interface AsyncService {
    /**
     * 模拟报表生成
     */
    void generateReport();
}
