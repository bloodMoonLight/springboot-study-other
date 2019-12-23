package com.springboot.study.streams.newstreams.mapstream;

import java.util.stream.Stream;

public class MapStreams  {

    public static void main(String[] args) {
        // 生成的是对象流
        Stream.of(1,2,3).map(i -> Stream.of("zhz","lll","ppp")).forEach(System.out::println);
        /**
         * flatMap 将其中的流变为元素，因为仅仅产生元素
         */
        Stream.of(1,2,3).flatMap(i -> Stream.of("qqq","wwww","eeee","rrrr")).forEach(System.out::println);


    }
}
