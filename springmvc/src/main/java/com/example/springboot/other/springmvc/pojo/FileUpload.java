package com.example.springboot.other.springmvc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName FileUpload
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月10日19:10:27 19:10
 * Version 1.0
 **/
@Data
@TableName("file_upload")
public class FileUpload {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("file_path")
    private String filePath;
    @TableField("file_type")
    private String fileType;
    @TableField("logo")
    private String logo;
    @TableField("swf_path")
    private String swfPath;
}
