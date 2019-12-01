package com.example.springboot.other.springmvc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月1日09:31:47 9:31
 * Version 1.0
 **/
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_name")
    private String userName;
    @TableField(value = "note")
    private String note;

}
