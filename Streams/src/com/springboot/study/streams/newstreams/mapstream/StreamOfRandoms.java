package com.springboot.study.streams.newstreams.mapstream;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 利用flatMapToInt 生成的int类型，产生随机数
 */
public class StreamOfRandoms {
    static Random random = new Random(47);
    public static void main(String[] args) {
        /**
         * Stream.of()生成的流利用flatMapToInt将流扁平化为元素，也就是将1,2,3,4,5传入其中
         * 然后将数据传入ints中生成随机数，利用concat将生成的数据拼接在一起
         * 流中的concat();它以参数的顺序组合两个流。
         */
        Stream.of(1,2,3,4,5).flatMapToInt(
                i -> IntStream.concat(
                        random.ints(0,100)
                                .limit(i),IntStream.of(-1))).forEach(n -> System.out.format("%d ", n));

    }
}
