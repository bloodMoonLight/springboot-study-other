package com.example.study.springboot.redis.redisController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
@RequestMapping("/redis")
public class RedisController {
    /**
     * 这个使用默认Jdk序列化的
     */
    @Autowired
    private RedisTemplate redisTemplate = null;
    /**
     * 这个使用String序列化
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @ResponseBody
    @RequestMapping("/stringAndHash")
    public Map<String,Object> testStringAndHash(){
        //这里使用默认的jdk序列化器
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("int_key","1");
        //这里使用设置的String的序列化器
        stringRedisTemplate.opsForValue().set("int","1");
        //使用运算
        stringRedisTemplate.opsForValue().increment("int",1);
        // 减1操作RedisTemplate不支持，所以需要获取底层连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        //执行减一操作(对该键的值进行减一操作)
        jedis.decr("int");
        Map<String, String> hash = new HashMap<>();
        hash.put("fild1","value1");
        hash.put("fild2","value2");
        // 存入一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash",hash);
        //新增一个字段
        stringRedisTemplate.opsForHash().put("hash","fild3","fild3");
        //绑定一个散列操作的key，对该key进行连续操作
        BoundHashOperations  hashOps = stringRedisTemplate.boundHashOps("hash");
        //执行删除操作，该方法可以输入多个key
        hashOps.delete("fild1","fild2");
        hashOps.put("fild4","fild4");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success",true);
        return hashMap;
    }


    /**
     * 使用Spring操作链表结构
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> testList(){
        List<String> list = new ArrayList<>();
        list.add("v2");
        list.add("v4");
        list.add("v6");
        list.add("v8");
        list.add("v10");
        //从左侧插入链表
        // 此时他的顺序为 v10 v8 v6 v4 v2,从左侧插入为先进后出
        stringRedisTemplate.opsForList().leftPushAll("list1",list);
        //从右侧插入链表
        //此时他的顺序为 v1 v3 v5 v7 v9,从右侧插入为先进先出
        stringRedisTemplate.opsForList().rightPushAll("list2","v1","v3","v5","v7","v9");
        //绑定list2的连续操作
        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");

        BoundListOperations list1 = stringRedisTemplate.boundListOps("list1");

        for(int i = 0; i < list1.size(); i++){
            System.out.println("list1中的数据从左侧弹出为:" + list1.leftPop());
        }



        //从右边弹出一个成员
        Object result1 = listOps.rightPop();
        System.out.println(result1);
        //获取下标为2的数据，redis下标从0开始计算
        Object index = listOps.index(2);
        System.out.println("下标为2的数据为"+ index);
        //从左边插入链表
        listOps.leftPush("v0");
        Long size = listOps.size();
        System.out.println("链表的长度为" + size);
        // 求链表下标区间的数据，链表的取值范围为0-size-1
        List range = listOps.range(0, size - 2);
        range.forEach(System.out::println);
        Map<String,Object> map = new HashMap<>(16);
        map.put("success",true);
        return map;
    }


    @ResponseBody
    @RequestMapping("/set")
    public Map<String,Object> testSet(){
        // 这里的设置set的值时，重复了一条数据，但是redis中集合不允许有重复的数据，此时他在内存中数据的长度为5
        stringRedisTemplate.opsForSet().add("set1","v1","v1","v2","v3","v4","v5");
        stringRedisTemplate.opsForSet().add("set2","v2","v4","v6","v8");

        //绑定set1操作
        BoundSetOperations set1 = stringRedisTemplate.boundSetOps("set1");
        set1.add("v6","v7");
        set1.remove("v1","v7");
        //返回所有的元素
        Set members = set1.members();
        members.forEach(System.out::println);

        Long size = set1.size();
        System.out.println("集合的长度为：" + size);

        //查询与set2集合的交集
        Set intersect = set1.intersect("set2");
        intersect.forEach(p -> System.out.println("与set2的交集为:"  + p));

        //求set1与set2的差集
        Set diff = set1.diff("set2");
        diff.forEach(p1 -> System.out.println("与set2的差集为:" + p1));
        //求差集，并用difff的新集合保存+
        set1.diffAndStore("set2","difff");

        //求并集
        Set union = set1.union("set2");
        union.forEach( p2 -> System.out.println("与Set2的并集为：" + p2));

        Map<String,Object> map = new HashMap<>(16);
        map.put("success",true);
        return map;
    }


    @ResponseBody
    @RequestMapping("/zset")
    public Map<String,Object> ZsetTest(){
        // 有序集合是通过TypedTuple中的score属性来决定的  value为有序集合
        Set<ZSetOperations.TypedTuple<String>> typedTupleHashSet   = new HashSet<>();
        for(int i = 0; i < 9; i++){
            //设置分数
            double score = i * 0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("value"+i,score);
            typedTupleHashSet.add(typedTuple);
        }
        //往有序集合中插入元素
        stringRedisTemplate.opsForZSet().add("zset1",typedTupleHashSet);
        //绑定有序集合的练习操作
        BoundZSetOperations<String, String> zSetOps = stringRedisTemplate.boundZSetOps("zset1");
        //添加一条数据
        zSetOps.add("value10",0.26);
        Set<String> range = zSetOps.range(1, 6);
        range.forEach(p1 -> System.out.println("1 - 6取到的数据为：" + p1));

        //按分数获取有序集合
        Set<String> strings = zSetOps.rangeByScore(0.2, 0.6);
        strings.forEach(p -> System.out.println("按分数0.2--->0.6获取的有序集合数据为："+ p));

        //定义值范围
        RedisZSetCommands.Range range1 = new RedisZSetCommands.Range();
        // 设置大于value3的数据，将他的value当做参数来进行比较，类似于mybatis-plus中的条件
        range1.gt("value3");
       // range1.gte("value3"); //大于等于
       // range1.le("value3");  //小于
       // range1.lte("value3"); //小于等于

        Set<String> strings1 = zSetOps.rangeByLex(range1);
        strings1.forEach(p2 -> System.out.println("通过range对象来查找到的数据为" + p2));
        //删除元素
        zSetOps.remove("value3");

        //通过value值求分数
        Double value8 = zSetOps.score("value8");
        System.out.println("得到的分数为：" + value8);
        // 在设置的下表区间内 按分数排序，并返回value以及score
        Set<ZSetOperations.TypedTuple<String>> tupleSet = zSetOps.rangeWithScores(1, 6);
        tupleSet.forEach(p3 -> System.out.println("按下表取件查找得到的数据为：" + p3.getValue() + "分数为:" + p3.getScore()));
        //再分苏区间内 按照分数排序，返回结果以及分数
        Set<ZSetOperations.TypedTuple<String>> withScores = zSetOps.rangeByScoreWithScores(0.2, 0.7);
        withScores.forEach(p4 -> System.out.println("按分数区间查找得到的数据为：" + p4.getValue() + "分数为:" + p4.getScore()));

        //从大到小排序(在指定区间内)
        Set<String> strings2 = zSetOps.reverseRange(2, 8);
        strings2.forEach(p5 -> System.out.println("在指定区间内按从大到小排序" + p5));


        Map<String,Object> map = new HashMap<>(16);
        map.put("success",true);
        return map;
    }




}
