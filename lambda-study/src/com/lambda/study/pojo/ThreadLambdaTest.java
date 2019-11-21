package com.lambda.study.pojo;

public class ThreadLambdaTest {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("我是通过Lambda表达式创建的")).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是匿名内部类创建的");
            }
        }).start();
    }
}
