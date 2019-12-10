package com.springboot.study.streams.newstreams;

public class Bubble {

    public final int i;

    public Bubble(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Bubble(" +
                "i=" + i +
                ')';
    }

    private  static int count = 0;

    public static Bubble bubble(){
        return new Bubble(count++);
    }
}
