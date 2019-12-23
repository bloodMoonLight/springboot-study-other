package com.springboot.study.streams.newstreams.fibonacci;

import java.util.stream.Stream;

public class Fibonacci {
    int x= 1;
    Stream<Integer> numbers(){
        return Stream.iterate(0,i ->{
            int result = i + x;
            x = i;
            return result;
        });
    }


    public static void main(String[] args) {
        new Fibonacci().numbers()
                .skip(20)       //过滤前20个
                .limit(10)     //取10个
                .forEach(System.out::println);
    }
}
