package com.lambda.study.closure;

import java.util.function.IntSupplier;

/**
 * 测试闭包的类
 */
public class Closure1 {

    int i;

    /**
     * 该方法中的i++是可以通过的，因为i的初始值始终没有变过，类似于final修饰的
     * Lambda表达式引用的局部变量必须是final修饰或等同于final的，所以此时没有报错
     * @param x
     * @return
     */
    IntSupplier makeFun(int x){
        return () -> x + i++;
    }
}
