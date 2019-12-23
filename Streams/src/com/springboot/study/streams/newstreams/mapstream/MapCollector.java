package com.springboot.study.streams.newstreams.mapstream;

import java.util.Map;
import java.util.stream.Collectors;

public class MapCollector {
    public static void main(String[] args) {
        Map<Integer, Character> collect = new RandomPair()
                .stream()  //这里是一个Pair对象流
                    .limit(8)
                    .collect(Collectors.toMap(Pair::getI, Pair::getC));
        System.out.println(collect);
    }
}
