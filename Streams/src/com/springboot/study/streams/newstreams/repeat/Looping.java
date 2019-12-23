package com.springboot.study.streams.newstreams.repeat;

import static com.springboot.study.streams.newstreams.repeat.Repeat.repeat;

public class Looping {
    /**
     * 此方法再用方法引用时就类似实现了Runable接口
     */
    static void hi(){
        System.out.println("Hi!");
    }

    public static void main(String[] args) {
        //此时用Lambda表达式实现了Runable接口
        repeat(3,() -> System.out.println("Looping"));
        //通过方法引用，使hi()方法类似于实现了Runable接口，因为该类中也只有一个方法，且方法参数和返回值与Runable接口一致
        repeat(2,Looping::hi);
    }
}
