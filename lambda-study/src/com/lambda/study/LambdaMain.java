package com.lambda.study;

import com.lambda.study.pojo.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Lambda表达式学习
 */
public class LambdaMain {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p = new Person("zhanghongzhi",18,"xxx.qq.com");
        Person p1 = new Person("zhanghongzhi",19,"xxx.qq.com");
        Person p2 = new Person("zhanghongzhi",20,"xxx.qq.com");
        Person p3 = new Person("zhanghongzhi",21,"xxx.qq.com");
        Person p4 = new Person("zhanghongzhi",14,"xxx.qq.com");
        list.add(p);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        xxxxx(list,pw -> pw.getAge() > 15);
    }



    public static void xxxxx(List<Person> person, Predicate<Person> predicate){
        for (Person p: person) {
            if(predicate.test(p)){
                System.out.println(p);
            }
        }
    }
}
