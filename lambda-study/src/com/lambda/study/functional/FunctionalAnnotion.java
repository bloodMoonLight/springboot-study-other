package com.lambda.study.functional;

public class FunctionalAnnotion {

    public String  goodbye(String msg){
        return "Good、、" + msg;
    }

    public static void main(String[] args) {
        FunctionalAnnotion fa = new FunctionalAnnotion();
        Functional functional = fa::goodbye;   //不能传参数，使用方法引用
        FunctionalNoAnn functionalNoAnn = fa::goodbye;
        Functional lambada =  a -> "good bye :" + a;   //通过lambada表达式可以将参数也传递过去
        FunctionalNoAnn funnoan =  a -> "good job" + a;
    }
}
