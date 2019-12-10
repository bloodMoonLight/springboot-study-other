package com.example.springboot.other.springmvc.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.File;
import java.util.regex.Pattern;

/**
 * office转PDF工具类
 * @ClassName Office2PDFUtils
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月10日19:34:01 19:33
 * Version 1.0
 **/
public class Office2PDFUtils {

    public static void WordToPDF(String srcPath,String desPath){
        OpenOfficeConnection connection = null;
        Process p = null;
        try {
            // 源文件目录
            File inputFile = new File(srcPath);
            if (!inputFile.exists()) {
                System.out.println("源文件不存在！");
                return;
            }
            // 输出文件目录
            File outputFile = new File(desPath);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().exists();
            }
            // 调用openoffice服务线程
            String command = "C:\\Program Files (x86)\\OpenOffice 4\\program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
            p = Runtime.getRuntime().exec(command);

            // 连接openoffice服务
            connection = new SocketOpenOfficeConnection(
                    "127.0.0.1", 8100);
            connection.connect();

            // 转换word到pdf
            DocumentConverter converter = new OpenOfficeDocumentConverter(
                    connection);
            converter.convert(inputFile, outputFile);

            System.out.println("转换完成！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                // 关闭连接
                connection.disconnect();
            }
            if (p != null) {
                // 关闭进程
                p.destroy();
            }
        }

    }

    public static void main(String[] args) {
        String srcPath = "D:\\test.doc";
        String desPath = "D:\\test.pdf";
        WordToPDF(srcPath,desPath);
    }



}
