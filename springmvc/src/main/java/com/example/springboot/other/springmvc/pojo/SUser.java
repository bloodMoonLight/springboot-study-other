package com.example.springboot.other.springmvc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.example.springboot.other.springmvc.emun.SexEnum;
import lombok.Data;

import java.io.Serializable;

@TableName("t_user")
@Data
public class SUser implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_name")
    private String userName;

    private SexEnum sex;

    @TableField("note")
    private String note;

}
