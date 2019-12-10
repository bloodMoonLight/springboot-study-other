package com.springboot.study.streams.newstreams;

import java.util.*;

/**
 * 集合生成Stream流的方法。调用.stream()方法
 */
public class CollectionToStream {
    public static void main(String[] args) {
        List<Bubble> list = Arrays.asList(new Bubble(1),new Bubble(2),new Bubble(3));
        list.stream().forEach(System.out::println);
        System.out.println(list.stream()
                                .mapToInt(b -> b.i)  //返回一个int类型的流
                                    .sum()); //计算结果


        Set<String> set = new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));
        set.stream()
                // map方法中的参数为Function<T t,U u>,传入两个参数，无返回值，
                //在这里是将原本传入进来的集合后面全部添加一个空格，达到恢复原样的效果
                .map(x -> x + " ")
                .forEach(System.out::print);
        System.out.println();




        Map<String,Double> map = new HashMap<>();
        map.put("pi",3.14159);
        map.put("e",2.718);
        map.put("phi",1.618);

        map.entrySet() //先将map转换成set集合
                .stream() // 创建Stream流
                .map(e -> e.getKey()+ ": " + e.getValue()) //执行中间操作
                .forEach(System.out::println);  //循环打印


    }
}
