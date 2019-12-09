package com.springboot.study.streams.newstreams;

import java.util.stream.Stream;

/**
 * 创建流的一种操作
 */
public class StreamOf {
    public static void main(String[] args) {
        /**
         * Stream.of:将一组数组转换为流
         */
        Stream.of(new Bubble(1),new Bubble(2),new Bubble(3)).forEach(System.out::println);

        Stream.of("zhang","hong","zhi","shi ","da ","ben", " dan" ).forEach(System.out::println);
    }
}
