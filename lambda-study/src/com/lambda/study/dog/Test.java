package com.lambda.study.dog;

/**
 * @ClassName Test
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月8日21:31:57 21:31
 * Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        MakeArgs mna = Dog::new;   //构造函数的引用
        Make1Args m1a = Dog::new;
        Make2Args m2a = Dog::new;
        // 类似于创建对象
        Dog dn = mna.make();
        Dog d1 = m1a.make("Comet！");
        Dog d2 = m2a.make("Rash", 52);
        System.out.println(dn.toString());
        System.out.println(d1.toString());
        System.out.println(d2.toString());


    }
}
