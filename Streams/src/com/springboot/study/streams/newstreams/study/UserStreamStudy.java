package com.springboot.study.streams.newstreams.study;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserStreamStudy {

    private static User user= new User();

    public static void main(String[] args) {
        test1();
    }

    /**
     * 对用户按年龄进行排序
     */
    public static void test1(){
        /**初始化用户信息  */
        List<User> userDatas =  user.createUserDatas();
        System.out.println("传统方式-----------");
        long time1 = System.currentTimeMillis();
        //按照集合工具类的sort方法，将设置的比较器传入其中
        Collections.sort(userDatas,user.getAgeComparator());
        System.out.println("传统方式排序耗时 ==> "+ (System.currentTimeMillis() - time1));
        userDatas.stream().forEach(s -> System.out.println(s.toString()));


        System.out.println("Streams流方式-----------");
        long time2 = System.currentTimeMillis();
        List<User> collect = userDatas.stream().sorted(user.getAgeComparator()).collect(Collectors.toList());
        System.out.println("传统方式排序耗时 ==> "+ (System.currentTimeMillis() - time2));
        collect.forEach(s -> System.out.println(s.toString()));

    }


}
