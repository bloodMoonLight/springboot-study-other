package com.example.springboot.other.springmvc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @ClassName PDF2SWF
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月10日20:16:45 20:16
 * Version 1.0
 **/
public class PDF2SWF {

    private static String psf2swf = "D:\\SWFTools\\pdf2swf.exe";

    public static int convertPDF2SWF(String sourcePath, String destPath, String fileName) throws Exception {
        //目标路径不存在则建立目标路径
        File dest = new File(destPath);
        if (!dest.exists()){ dest.mkdirs();}

        //源文件不存在则返回
        File source = new File(sourcePath);
        if (!source.exists()){ return 0;}

        //调用pdf2swf命令进行转换
        String command = psf2swf + " -o \"" + destPath + "\\" + fileName + "\"  -s flashversion=9 \"" + sourcePath + "\"";

        Process pro = Runtime.getRuntime().exec(command);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        while (bufferedReader.readLine() != null){};

        try {
            pro.waitFor();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return pro.exitValue();

    }

    public static void main(String []args) throws Exception {
        /*String sourcePath = "D:\\test.pdf";
        String destPath = "D:\\";
        String fileName = "test.swf";
        PDF2SWF.convertPDF2SWF(sourcePath, destPath, fileName);*/


        String filePath = "新建文本文档.txt";
        String substring = filePath.substring(filePath.lastIndexOf("."));
        System.out.println(substring);
    }
}
