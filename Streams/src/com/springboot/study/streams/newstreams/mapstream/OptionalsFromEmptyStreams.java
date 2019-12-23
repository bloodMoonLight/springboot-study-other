package com.springboot.study.streams.newstreams.mapstream;

import java.util.stream.Stream;

public class OptionalsFromEmptyStreams {
    public static void main(String[] args) {
        // 生成空流。   findFirst()返回一个包含第一个元素的的Optianl对象，如果流为空则返回Optional.empty();
        System.out.println(Stream.<String>empty().findFirst());
        // findAny()；  返回包含任意元素的Optianl对象，如果为空则返回Optional.empty()
        System.out.println(Stream.<String>empty().findAny());

        System.out.println(Stream.<String>empty().max(String.CASE_INSENSITIVE_ORDER).isPresent());


    }
}
