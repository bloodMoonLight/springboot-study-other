package com.lambda.study.closure;

import java.util.function.IntSupplier;

public class SharedStorage {
    public static void main(String[] args) {
        Closure1 closure1 = new Closure1();
        // 此时数据是共享的，所以Closure1中的i是等同于Final修饰的常量
        IntSupplier intSupplier = closure1.makeFun(0);
        IntSupplier intSupplier1 = closure1.makeFun(0);
        IntSupplier intSupplier2 = closure1.makeFun(0);
        System.out.println(intSupplier.getAsInt());
        System.out.println(intSupplier1.getAsInt());
        System.out.println(intSupplier2.getAsInt());
    }
}
