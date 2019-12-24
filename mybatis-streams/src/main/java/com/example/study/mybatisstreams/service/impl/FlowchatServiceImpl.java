package com.example.study.mybatisstreams.service.impl;

import com.example.study.mybatisstreams.mapper.FlowcharMapper;
import com.example.study.mybatisstreams.pojo.Flowchart;
import com.example.study.mybatisstreams.service.FlowchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowchatServiceImpl implements FlowchatService {

    @Autowired
    private FlowcharMapper flowcharMapper;

    @Override
    public List<Flowchart> changeData() {
        //查询出所有的流程图数据，开始使用Stream流以及lambda表达式操作
        List<Flowchart> flowcharts = flowcharMapper.selectList(null);
        // 取出前8个元素，并且RoleId长度大于8的就收集起来
        List<Flowchart> collect = flowcharts.stream().limit(8).filter(s -> s.getRoleId().length() > 8).collect(Collectors.toList());
        collect.forEach(System.out::print);
        System.out.println();
        return collect;
    }

    @Override
    public List<Flowchart> setContentIsNullAndByNodeIdOrder() {
        //查询出所有的流程图数据，开始使用Stream流以及lambda表达式操作
        List<Flowchart> flowcharts = flowcharMapper.selectList(null);
        List<Flowchart> collect = flowcharts.stream()
                .limit(50)
                .map(s -> new Flowchart(s.getId(),s.getNodeId(),s.getType(),s.getRoleId()))
                .sorted()
                .collect(Collectors.toList());


        return collect;
    }
}
