package com.example.springboot.other.springmvc.interfaces;


import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 定义DPF导出接口。
 * 因为各个控制层有不同的导出逻辑，为了适应不同控制器的自定义导出，所以定义一个导出接口
 */
public interface PdfExportService {


    /**
     * 定义PDF导出接口
     * @param model 数据模型
     * @param document 表示一个PDF文档
     * @param writer PDF写入器
     * @param request 请求对象
     * @param response 响应对象
     */
    void make(Map<String,Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response);
}
