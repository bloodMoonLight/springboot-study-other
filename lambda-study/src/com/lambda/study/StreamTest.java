package com.lambda.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        //从数组中创建流
        Arrays.stream(new String[]{"hello", "world"}).forEach(System.out::println);


        List<String> list = new ArrayList<>();
        list.add("我的");
        list.add("运气");
        list.add("真差");
        list.stream().forEach(System.out::println);



    }
}
