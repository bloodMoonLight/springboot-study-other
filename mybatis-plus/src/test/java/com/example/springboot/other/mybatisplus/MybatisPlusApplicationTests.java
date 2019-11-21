package com.example.springboot.other.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.other.mybatisplus.Entity.User;
import com.example.springboot.other.mybatisplus.mapper.UserMapper;
import net.minidev.json.writer.UpdaterMapper;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println("------selectAll method test -----");
        //selectList中的参数为Mybatis-Plus内置的条件封装器 Wrapper，不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
    @Test
    void MapperInsertService(){
        System.out.println("------ insert method test -----");
        userMapper.insert(new User(6L,"zhanghongzhi",23,"953639281@qq.com"));
        User user = userMapper.selectById(6);
        System.out.println(user);
    }

    @Test
    void MapperDeleByIdService(){
        System.out.println("----- delete method test -----");
        userMapper.deleteById(5);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        List<Long> ids = new ArrayList<Long>();
        ids.add(3L);
        ids.add(4L);
        //测试Mapper接口的批量删除
        userMapper.deleteBatchIds(ids);
        List<User> users1 = userMapper.selectList(null);
        users1.forEach(System.out::println);

        //添加测试数据
        userMapper.insert(new User(3L,"wangzi",26,"xxxxxxxxqq.com"));
        userMapper.insert(new User(4L,"zhanghongzhi",23,"888888xxqq.com"));
        userMapper.insert(new User(5L,"huangdatao",22,"66666666xqq.com"));
        //测试Map条件删除数据
        Map<String,Object> map = new HashMap<>();
        map.put("age",23);
        map.put("email","888888xx.com");
        int i = userMapper.deleteByMap(map);
        if(i > 0){
            System.out.println("通过条件删除了数据");
        }else{
            System.out.println("没有删除数据,因为条件不符合");
        }


        //通过条件封装器来删除数据
        Integer delete = userMapper.delete(new QueryWrapper<User>().eq("name", "huangdatao"));
        if(delete != null || delete > 0){
            System.out.println("通过条件删除成功");
        }else {
            System.out.println("通过条件删除失败");
        }


        List<User> users2 = userMapper.selectList(null);
        users2.forEach(System.out::println);

    }


    /**
     * Mybatis-plus    Mapper接口中的更新相关方法
     */
    @Test
    void MapperUpdateService(){
        System.out.println("------ update method test ----------");
        userMapper.updateById(new User(5L,"zhanghongzhi",28,"953639281@qq.com"));
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        //使用MapperService接口的update方法
        User u = new User();
        u.setEmail("8848@qq.com");
        u.setName("黄大涛");
        //第一个参数需要修改的对象的值，第二个参数为更新的条件封装器
        userMapper.update(u,new UpdateWrapper<User>().eq("id",4L));

        List<User> users1 = userMapper.selectList(null);
        users1.forEach(System.out::println);

        ArrayList<Long> longs = new ArrayList<>();
        longs.add(2L);
        longs.add(3L);
        longs.add(4L);
        // MapperSelectService接口相关方法
        List<User> users2 = userMapper.selectBatchIds(longs);
        users2.forEach(System.out::println);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","Jack");

        List<User> users3 = userMapper.selectByMap(map2);
        users3.forEach(System.out::println);

        userMapper.updateById(new User(4L,"黄大涛",20,"8848@qq.com"));

        //selectOne方法  需要注意的是，如果查询出来的结果不唯一，并不会为你自动添加limit 1,需要自己手动添加
        //不加limit1
        //User user = userMapper.selectOne(new QueryWrapper<User>().eq("age", 20));
        //加last("limit 1")
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("age", 20).last("limit 1"));
        System.out.println(user);

        // select count方法
        Integer integer = userMapper.selectCount(null);
        System.out.println(integer);


        Integer id = userMapper.selectCount(new QueryWrapper<User>().between("id", 0, 100));

        IPage<User> objectPage = new Page<User>(1,3,id);
        IPage<User> id1 = userMapper.selectPage(objectPage, new QueryWrapper<User>().between("id", 0, 100));
        System.out.println("分页查询");
        id1.getRecords().forEach(System.out::println);


    }


    private void selectUserPrint(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Test
    void WapperSelectTest(){
        System.out.println("-------- Wrapper Select method ------------");
        //先查询
        selectUserPrint();

        //将一个用户的年龄改成20
        userMapper.updateById(new User(4L,"zhanghongzhi",20,"qq@.com"));

        selectUserPrint();

        Map<String,Object> map = new HashMap<String,Object>(16);
        map.put("age",20);
        // 查询年龄为20岁的用户
        List<User> users = userMapper.selectList(new QueryWrapper<User>().allEq(map));
        users.forEach(System.out::println);


        // 修改5条数据中两天数据的值为null
        userMapper.updateById(new User(1L,"Jone",null,null));
        userMapper.updateById(new User(3L,"Jone",22,null));
        selectUserPrint();

        Map<String,Object> map1 = new HashMap<String,Object>(16);
        map.put("age",22);
        map.put("email",null);
        List<User> users1 = userMapper.selectList(new QueryWrapper<User>().allEq(map1, true));

        System.out.println("------------- 测 试 allEq -------------------");
        users1.forEach(System.out::println);

        List<User> users2 = userMapper.selectList(new QueryWrapper<User>().groupBy());


    }









}
