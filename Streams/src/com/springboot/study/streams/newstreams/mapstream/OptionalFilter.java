package com.springboot.study.streams.newstreams.mapstream;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class OptionalFilter {

    static String[] elements = {"Foo","","Bar","Baz","Bingo"};

    static Stream<String> testStream(){
        return Arrays.stream(elements);
    }

    /**
     * 打印是否过滤成功
     * @param desrc
     * @param predicate
     */
    static void test(String desrc, Predicate<String> predicate){
        System.out.println("=======" + desrc +"=======");
        for (int i = 0; i < elements.length; i++){
            System.out.println(testStream().skip(i).findFirst().filter(predicate));
        }
    }


    public static void main(String[] args) {
        test("xxxxxxxx",s -> false);
        test("dddddddd",s -> true);
        test("str != \"\"",s -> s != "");
    }

}
