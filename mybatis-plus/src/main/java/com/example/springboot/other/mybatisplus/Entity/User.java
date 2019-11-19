package com.example.springboot.other.mybatisplus.Entity;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

    public User() {
    }

    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
