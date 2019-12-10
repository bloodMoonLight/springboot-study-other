package com.lambda.study.zidingyiFunction;

public class TriFunctionalTest {
    static int f(int i, long l,double d){
        return 22;
    }
    static  int functionsss(TriFunctional<Integer,Long,Double,Integer> a, int b){
        System.out.println(a);
        return b;
    }

    public static void main(String[] args) {
        TriFunctional<Integer,Long,Double,Integer> f =  TriFunctionalTest::f;
        f = (i,l,d) -> 12;
        System.out.println(f.applay(1,1L,1.1));

        int functionsss = functionsss((i, l, d) -> {
            return 25;
        }, 52);
        System.out.println(functionsss);


    }
}
