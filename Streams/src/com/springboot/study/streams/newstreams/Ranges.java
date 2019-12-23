package com.springboot.study.streams.newstreams;

import static java.util.stream.IntStream.range;

/**
 * IntStream类的range()提供了生成整形序列的流
 */
public class Ranges {
    public static void main(String[] args) {
        //普通循环累加
        int result = 0;
        for(int i = 10; i < 20;i++){
            result += i;
        }
        System.out.println(result);

        result = 0;

        //foreach循环遍历数组
        for(int i : range(10,20).toArray()){
            result += i;
        }
        System.out.println(result);

        //intStream流处理
        System.out.println(range(10,20).sum());


    }
}
