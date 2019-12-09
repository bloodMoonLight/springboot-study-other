package com.springboot.study.streams;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class Randoms {
    public static void main(String[] args) {
        /**
         * ints(int x,int y); ints方法产生一个流，并且ints方法有多种重载方法--两个参数限定了数值的产生边界
         * distinct(); 去重方法
         * limit(); 获取前7个元素
         */
        new Random(47)   //创建一个Random对象，取0 - 47之间的数据
                .ints(5,20)  //获取int类型的值，只取第5到20个之间的数据
                    .distinct()  //去重
                        .limit(7) //只取7个
                            .sorted()  //排序
                                .forEach(System.out::println);  //循序打印



        randomsss();

    }


    static void randomsss(){
        Random random = new Random(47);
        SortedSet<Integer> set = new TreeSet<>();
        while(set.size() < 7){
            int i = random.nextInt(20);
            if(i < 5 ){
                continue;
            }
            set.add(i);
        }
        System.out.println(set);
    }
}
