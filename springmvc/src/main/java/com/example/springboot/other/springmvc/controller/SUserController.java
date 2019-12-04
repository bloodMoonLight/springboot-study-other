package com.example.springboot.other.springmvc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.other.springmvc.emun.SexEnum;
import com.example.springboot.other.springmvc.exceptioncustom.NotFoundException;
import com.example.springboot.other.springmvc.pojo.SUser;
import com.example.springboot.other.springmvc.repository.SUserMapper;
import com.example.springboot.other.springmvc.vo.SUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/rest")
public class SUserController {
    @Autowired
    private SUserMapper sUserMapper;
    @GetMapping("/restful")
    public String index(){
        return "user/restful";
    }


    /**
     * 使用rest风格插入对象
     * @param vo
     * @return
     */
    @PostMapping("/insert")
    public SUser insert(@RequestBody SUserVo vo){
        int insert = sUserMapper.insert(changToPo(vo));
        return changToPo(vo);
    }

    /**
     * 使用rest风格插入对象
     * @param vo
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<SUserVo> insertStatus(@RequestBody SUserVo vo){
        SUser sUser = changToPo(vo);
        sUserMapper.insert(sUser);
        SUserVo result = changToVo(sUser);
        HttpHeaders headers = new HttpHeaders();
        String success = (result == null || result.getId() == null) ? "false" : "true";
        //设置响应头
        headers.add("success",success);
        //返回创建成功的状态码
        return new ResponseEntity<SUserVo>(result,headers, HttpStatus.CREATED);
    }


    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public SUserVo getUser(@PathVariable("id") Long id){
        SUser sUser = sUserMapper.selectById(id);
        return changToVo(sUser);
    }

    /**
     * 接收所有类型的请求，但只返回普通文本类型的数据
     * @param id
     * @return
     */
    @GetMapping(value = "/user/name/{id}",consumes = MediaType.ALL_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUserName(@PathVariable("id") Long id){
        SUser sUser = sUserMapper.selectById(id);
        return sUser.getUserName();
    }
    /**
     * 接收所有类型的请求，但只返回普通文本类型的数据
     * @param id
     * @return
     */
    @GetMapping(value = "/user/name/name/{id}")
    public String getUserNames(@PathVariable("id") Long id){
        SUser sUser = sUserMapper.selectById(id);
        return sUser.getUserName();
    }



    /**
     * 使用get请求查询符合要求的用户，此处只用一个参数是因为数据库数据太少，参数多了就匹配不到多条数据
     * @param note
     * @return
     */
    @GetMapping("/users/{note}")
    @ResponseBody
    public List<SUserVo> findUsers(@PathVariable("note")String note){
        Map<String,Object> map = new HashMap<>(16);
        map.put("note",note);
        List<SUser> sUsers = sUserMapper.selectByMap(map);
        return changToVoes(sUsers);

    }


    /**
     * 使用HTTP的PUT请求修改用户信息
     * consumes表示限制该方法接收什么类型的请求体
     * produces代表的是限定返回的媒体类型
     * @param id
     * @param vo
     * @return
     */
    @PutMapping(value = "/user/{id}",consumes = MediaType.ALL_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public SUser updateUser(@PathVariable("id")Long id,@RequestBody SUserVo vo){

        SUser sUser = this.changToPo(vo);
        sUser.setId(id);
        sUserMapper.updateById(sUser);
        return sUser;
    }
    @GetMapping("/toPatch")
    public String toPatch(){
        return "user/patch";
    }


    /**
     * 通过HTTP Patch请求修改局部字段的值
     * @param id
     * @param userName
     * @return
     */
    @PatchMapping("/user/{id}/{userName}")
    @ResponseBody
    public ResultVo changeUserName(@PathVariable("id")Long id,@PathVariable("userName") String userName){
        SUser u = new SUser();
        u.setUserName(userName);
        int id1 = sUserMapper.update(u, new QueryWrapper<SUser>().eq("id", id));
        ResultVo vo = new ResultVo(id1 > 0,id1 > 0 ? "更新成功" : "更新用户【"+id+"】失败。");
        return vo;
    }

    @PatchMapping(value="/userss")
    @ResponseBody
    public ResultVo changeUserNames(@RequestBody SUser sUser){
        SUser u = new SUser();
        u.setUserName(sUser.getUserName());
        int id1 = sUserMapper.update(u, new QueryWrapper<SUser>().eq("id", sUser.getId()));
        ResultVo vo = new ResultVo(id1 > 0,id1 > 0 ? "更新成功" : "更新用户【"+sUser.getId()+"】失败。");
        return vo;
    }



    /**
     * 将Vo转换为Po
     * @param vo
     * @return
     */
    private SUser changToPo(SUserVo vo){
        SUser sUser = new SUser();
        sUser.setId(vo.getId());
        sUser.setUserName(vo.getUserName());
        sUser.setSex(SexEnum.getEnumById(vo.getSexCode()));
        sUser.setNote(vo.getNote());
        return sUser;
    }

    /**
     * 将Po转换为Vo
     * @param sUser
     * @return
     */
    private SUserVo changToVo(SUser sUser){
        SUserVo sUserVo = new SUserVo();
        sUserVo.setId(sUser.getId());
        sUserVo.setNote(sUser.getNote());
        sUserVo.setUserName(sUser.getUserName());
        sUserVo.setSexCode(sUser.getSex().getId());
        sUserVo.setSexName(sUser.getSex().getName());
        return sUserVo;
    }

    /**
     * 将Po列表转换为Vo列表
     * @param poList
     * @return
     */
    private List<SUserVo> changToVoes(List<SUser> poList){
        List<SUserVo> voList = new ArrayList<>();
        poList.forEach(po ->{
            SUserVo sUserVo = changToVo(po);
            voList.add(sUserVo);
        });
        return voList;
    }

    public class ResultVo{
        private Boolean success = null;
        private String message = null;

        public ResultVo() {
        }

        public ResultVo(Boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * 必须以json形式返回
     * @param id
     * @return
     */
    @GetMapping(value = "/user/exp/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SUserVo getUserForEx(@PathVariable("id")Long id){
        SUser sUser = sUserMapper.selectById(id);
        if(sUser == null){
            throw new NotFoundException(1L,"找不到用户【"+ id + "】信息");
        }
        SUserVo sUserVo = changToVo(sUser);
        return sUserVo;
    }






}
