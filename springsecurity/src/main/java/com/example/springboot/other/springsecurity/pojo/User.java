package com.example.springboot.other.springsecurity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_user")
public class User implements Serializable {

    @TableId(type=IdType.AUTO)
    private Long id;

    @TableField("user_name")
    private String userName;

    @TableField("pwd")
    private String pwd;

    @TableField("available")
    private int available;

    @TableField("note")
    private String note;
    /** 关联数据，一个用户可以对应多个Role */
   // private List<Role> roleList;

}
