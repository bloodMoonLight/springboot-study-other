package com.example.study.mybatisstreams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.study.mybatisstreams.mapper")
public class MybatisStreamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisStreamsApplication.class, args);
    }

}
