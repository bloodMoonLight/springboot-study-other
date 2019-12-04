package com.example.springboot.other.springsecurity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@TableName("t_role")
public class Role implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("role_name")
    private String roleName;

    @TableField("note")
    private String note;
}
