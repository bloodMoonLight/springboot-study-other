package com.lambda.study.recursion;

/**
 * @ClassName RecursionMain
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月8日21:16:42 21:16
 * Version 1.0
 **/
public class RecursionMain {
    static IntCall fact;
    public static void main(String[] args) {
        fact = n -> n == 0 ? 1 : n * fact.Call(n - 1);
        for (int i = 0; i <= 10; i++){
            System.out.println(fact.Call(i));
        }


    }
}
