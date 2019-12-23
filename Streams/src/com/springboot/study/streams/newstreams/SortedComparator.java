package com.springboot.study.streams.newstreams;

import java.io.IOException;
import java.util.Comparator;

public class SortedComparator {
    public static void main(String[] args) throws IOException {
      new   FileToWordsBuilder("G:\\Cheese.dat")
              .stream()
                .skip(10)
                     .limit(10)
                         .sorted(Comparator.reverseOrder())   //中间操作排序操作
                            .map(w -> w + " ")
                                .forEach(System.out::print);
    }
}
