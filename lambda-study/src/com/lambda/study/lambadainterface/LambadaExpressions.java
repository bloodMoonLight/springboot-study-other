package com.lambda.study.lambadainterface;

/**
 * @ClassName LambadaExpressions
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月8日21:08:32 21:08
 * Version 1.0
 **/
public class LambadaExpressions {
    static Body dob = h -> h + "No parent!";        // 带参数的一种写法
    static Body bod2 = (h) -> h + "More details";    //带参数的一种写法
    static Description desc = () -> "Short info";   // 无参数形式的一种写法
    static Multi multi = (h,n) -> h + n;            //多个参数的写法
    static  Description moreLines = () ->{     //有多条语句时的写法
        System.out.println("MoreLines()");
        return "from moreLines";
    };


    public static void main(String[] args) {
        System.out.println(dob.detailed("Oh! "));
        System.out.println(bod2.detailed("Hi! "));
        System.out.println(desc.broft());
        System.out.println(multi.TwoArg("Pi!",3.1459));
        System.out.println(moreLines.broft());
    }

}
