package com.example.study.mybatisstreams.service;

import com.example.study.mybatisstreams.pojo.Flowchart;

import java.util.List;

public interface FlowchatService {

    List<Flowchart> changeData();

    /**
     * 将取出对象的content属性设置为null，且按照nodeId排序
     * @return
     */
    List<Flowchart> setContentIsNullAndByNodeIdOrder();


}
