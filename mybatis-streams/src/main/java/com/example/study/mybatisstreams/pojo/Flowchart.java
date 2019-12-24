package com.example.study.mybatisstreams.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("my_lx_case_flowchart")
@Data
public class Flowchart implements Comparable<Flowchart>{
    @TableId(type=IdType.AUTO)
    private Long id;

    @TableField("flowcharts_id")
    private Integer flowchartsId;

    @TableField("node_id")
    private Integer nodeId;

    @TableField("type")
    private String type;

    @TableField("role_id")
    private String roleId;

    @TableField("content")
    private String content;

    @TableField("info_id")
    private Integer infoId;

    @TableField("old_id")
    private Integer oldId;

    @TableField("answer_type")
    private Integer answerType;

    @TableField("level")
    private Integer level;

    @TableField("parentId")
    private String parentId;

    public Flowchart(Long id, Integer nodeId, String type, String roleId) {
        this.id = id;
        this.flowchartsId = flowchartsId;
        this.nodeId = nodeId;
        this.type = type;
        this.roleId = roleId;
    }

    @Override
    public int compareTo(Flowchart o) {
        if(this.nodeId > o.getNodeId()){
            return 1;
        }else if(this.nodeId < o.getNodeId()){
            return -1;
        }else{
            return 0;
        }

    }
}
