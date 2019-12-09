package com.lambda.study.commonfunctional;

import java.util.function.*;

public class FunctionalVariants {
    static Function<Foo,Bar> f1 =  f -> new Bar(f); // 通过Lambada表达式创建对象
    static IntFunction<IBaz> f2 = i -> new IBaz(i); //创建接收基本类型int，返回不同类型的接口
    static LongFunction<LBaz> f3 = l -> new LBaz(l); //创建接收基本类型long，返回不同类型的接口
    static DoubleFunction<DBaz> f4 = d -> new DBaz(d);//创建接收基本类型double，返回不同类型的接口
    static ToIntFunction<IBaz> f5 = ib -> ib.i;   //创建一个接收基本类型int，结果也为int的函数式接口
    static ToLongFunction<LBaz> f6 = lb -> lb.i;  //创建一个接受基本类型long，结果也为long的函数式接口
    static ToDoubleFunction<DBaz> f7 = f -> f.d; // 创建一个接受基本类型double，结果也为double的函数式接口
    static IntToLongFunction f8 = f -> f;    //接收一个int类型的数据，返回一个long类型
    static IntToDoubleFunction f9 = f ->f;   // 接收int，返回double
    static LongToIntFunction f10 =  f -> (int) f;  // 大类型转小类型还是需要强转

    public static void main(String[] args) {
        // 测试
        Bar bar = f1.apply(new Foo());
        IBaz iBaz = f2.apply(11);
        LBaz lBaz = f3.apply(11);
        DBaz dBaz = f4.apply(11);
        int i = f5.applyAsInt(iBaz);
        long l = f6.applyAsLong(lBaz);
        double v = f7.applyAsDouble(dBaz);
        long l1 = f8.applyAsLong(22);
        double v1 = f9.applyAsDouble(65);
        f10.applyAsInt(99);


    }
}
