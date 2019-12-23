package com.springboot.study.streams.newstreams.mapstream;

import com.springboot.study.streams.newstreams.StreamOf;

import java.util.stream.Stream;

public class Numbered {
    final int n;

    public Numbered(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Numbered{" + n +"}";
    }
}


class Function2{
    public static void main(String[] args) {
        Stream.of(1,2,3,4,5,6,7,8,9,10).map(Numbered::new).forEach(System.out::println);
    }
}



class Function3{
    public static void main(String[] args) {
        Stream.of("5","7","9").mapToInt(Integer::parseInt).forEach(n -> System.out.printf("%d ",n));
        System.out.println();
        Stream.of("17","19","23").mapToLong(Long::parseLong).forEach(n -> System.out.printf("%d ",n));

    }



}