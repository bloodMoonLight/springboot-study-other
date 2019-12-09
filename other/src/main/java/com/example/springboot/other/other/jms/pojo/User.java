package com.example.springboot.other.other.jms.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {

    private Long id;

    private String userName;

    private String note;

    public User() {
    }

    public User(Long id, String userName, String note) {
        this.id = id;
        this.userName = userName;
        this.note = note;
    }
}
