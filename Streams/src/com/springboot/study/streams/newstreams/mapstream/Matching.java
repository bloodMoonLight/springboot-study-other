package com.springboot.study.streams.newstreams.mapstream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Matching {

    static void show(Matcher matcher,int val){
        System.out.println(
                matcher.test(
                        IntStream.rangeClosed(1,9).boxed().peek(n -> System.out.format("%d ", n))
                ,l -> l < val));
    }


    public static void main(String[] args) {
        /**
         * allMatch 在遇到false后中断，并返回false，全部执行完毕，就返回true
         */
        show(Stream::allMatch,10);
        show(Stream::allMatch,4);
        show(Stream::allMatch,2);
        show(Stream::allMatch,0);
        show(Stream::allMatch,5);
        show(Stream::allMatch,0);

    }
}
