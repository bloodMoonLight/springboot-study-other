package com.springboot.study.streams.newstreams.mapstream;

import java.io.IOException;

public class FileToWordsTest {
    public static void main(String[] args) throws IOException {
        FileToWords.stream("G:\\Cheese.dat").limit(7).forEach(s -> System.out.format("%s ",s));
    }
}
