package com.springboot.study.streams.newstreams.mapstream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileToWords {
    /**
     * 利用传入文件的路径生成字符串流，其中过滤第一行，并将每一行的单词分割成字符串流
     * @param filePath
     * @return
     * @throws IOException
     */
    public static Stream<String> stream(String filePath) throws IOException {
        Pattern compile = Pattern.compile("\\w+");
        return Files.lines( Paths.get(filePath))
                .skip(1)
                .flatMap(lins -> compile.splitAsStream(lins));
    }
}
