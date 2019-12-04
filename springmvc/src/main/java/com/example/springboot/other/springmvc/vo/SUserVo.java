package com.example.springboot.other.springmvc.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SUserVo implements Serializable {

    private Long id;

    private String userName;

    private int sexCode;

    private String sexName;

    private String note;


}
