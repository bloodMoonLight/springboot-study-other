package com.springboot.study.streams.newstreams.mapstream;

import java.util.Random;
import java.util.stream.Stream;

public class Frobnitz {

    int size;

    public Frobnitz(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Frobnitz{" +
                "size=" + size +
                '}';
    }

    static Random random = new Random(47);
    static final int BOUND = 100;

    static Frobnitz supply(){
        return new Frobnitz(random.nextInt(BOUND));
    }
}


class Reduce{
    public static void main(String[] args) {
        Stream.generate(Frobnitz::supply) // 生成了一个对象流
                .limit(10) //取前10个对象
                .peek(System.out::println) //输出一下对象的内容
                .reduce((r0,r1) -> r0.size < 50 ? r0 : r1) //reduce 第一个参数为上一个对象的结果，第二个参数为新的对象
                .ifPresent(System.out::println);
    }
}