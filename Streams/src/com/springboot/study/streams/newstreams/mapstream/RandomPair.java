package com.springboot.study.streams.newstreams.mapstream;


import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;

public class RandomPair {

   Random random = new Random(47);

    Iterator<Character> capChars =  random
            .ints(65,91)
            .mapToObj(i -> (char)i).iterator();

    public Stream<Pair> stream(){
        return random
                .ints(100,1000)
                .distinct()
                .mapToObj(i -> new Pair(capChars.next(),i)); //生成迭代器，并将随机产生的数字传入其中，生成Pair对象流
    }


}
