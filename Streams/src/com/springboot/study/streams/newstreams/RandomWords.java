package com.springboot.study.streams.newstreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RandomWords implements Supplier<String> {

    List<String> words = new ArrayList<>();
    Random random = new Random(47);
    RandomWords(String name) throws IOException {
        // Files.readAllLines读取文件中的所有行
        List<String> lines = Files.readAllLines(Paths.get(name));
        //略过第一行
        for(String line : lines.subList(1,lines.size())){
            //将每一行的字符串再次分割
            for(String word : line.split("[ .?,]")){
                words.add(word.toLowerCase());
            }
        }
    }


    @Override
    public String get() {
        //返回随机获取的某一行
        return words.get(random.nextInt(words.size()));
    }

    @Override
    public String toString() {
        return words.stream().collect(Collectors.joining(" "));
    }

    public static void main(String[] args) throws IOException {
        System.out.println(
                Stream.generate(
                        new RandomWords("G:\\Cheese.dat"))
                                .limit(10)
                                    .collect(Collectors.joining(" ")));
    }
}
