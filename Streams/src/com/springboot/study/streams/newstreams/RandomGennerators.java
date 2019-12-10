package com.springboot.study.streams.newstreams;

import java.util.Random;
import java.util.stream.Stream;

/**
 * 随机数流
 */
public class RandomGennerators {
    static <T> void show(Stream<T> stream){
        stream.limit(4).forEach(System.out::println);
        System.out.println("++++++++++++");
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        show(random.ints().boxed()); // 不设置上下限
        show(random.longs().boxed());
        show(random.doubles().boxed());
        //设置上下限
        show(random.ints(10,20).boxed());
        show(random.longs(50,100).boxed());
        show(random.doubles(20,30).boxed());
        //控制流的大小，ints();为一个参数时，是设置流的大小，两个参数时是设定上下限，三个参数时是设定流的大小和上下限
        show(random.ints(2).boxed());
        show(random.longs(2).boxed());
        show(random.doubles(2).boxed());
        //控制流的大小和边界
        show(random.ints(3,3,9).boxed());
        show(random.longs(3,3,9).boxed());
        show(random.doubles(3,3,9).boxed());

    }
}
