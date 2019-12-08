package com.lambda.study.functioninterface;

/**
 * @ClassName Soft
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月8日20:50:39 20:50
 * Version 1.0
 **/
public class Soft implements Strategy {

    @Override
    public String approach(String msg) {
        return msg.toLowerCase() + "?";
    }
}
