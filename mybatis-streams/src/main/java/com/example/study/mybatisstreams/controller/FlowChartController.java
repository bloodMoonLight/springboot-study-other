package com.example.study.mybatisstreams.controller;

import com.example.study.mybatisstreams.pojo.Flowchart;
import com.example.study.mybatisstreams.service.FlowchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/data")
public class FlowChartController {

    @Autowired
    private FlowchatService flowchatService;

    @ResponseBody
    @RequestMapping("/a")
    public List<Flowchart> findSelect(){
        return flowchatService.changeData();
    }

    @ResponseBody
    @RequestMapping("/b")
    public List<Flowchart> setContentIsNullAndByNodeIdOrder(){
        return flowchatService.setContentIsNullAndByNodeIdOrder();
    }


}
