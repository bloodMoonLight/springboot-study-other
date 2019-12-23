package com.springboot.study.streams.newstreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileToWordsBuilder {

    Stream.Builder<String> builder = Stream.builder();

    public FileToWordsBuilder(String filePath) throws IOException {
        Files.lines(Paths.get(filePath))
                .skip(1) //过滤第一行注释行
                .forEach(line -> {
                    for(String w : line.split("[ ,?.]+")){
                        builder.add(w);
                    }
        });
    }

    /**
     * 构建String类型的流
     * @return
     */
    Stream<String> stream(){
        return builder.build();
    }

    public static void main(String[] args) throws IOException {
        new FileToWordsBuilder("G:\\Cheese.dat")
                .stream()
                    .limit(7)
                        .map( w -> w + " ")
                            .forEach(System.out::print);
    }



}
