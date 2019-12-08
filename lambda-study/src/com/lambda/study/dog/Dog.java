package com.lambda.study.dog;

/**
 * 三种构造方法，使用三种接口，且接口中的方法参数列表与构造参数一致，测试创建对象
 * @ClassName Dog
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月8日21:27:47 21:27
 * Version 1.0
 **/
public class Dog {
    String name;
    int age = 1;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
