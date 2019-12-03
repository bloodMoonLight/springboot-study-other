package com.example.springboot.other.springmvc.view;


import com.example.springboot.other.springmvc.interfaces.PdfExportService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class PdfView extends AbstractPdfView {

    private PdfExportService pdfExportService = null;

    /**
     * 创建对象时载入导出服务接口
     * @param pdfExportService
     */
    public PdfView(PdfExportService pdfExportService){
        this.pdfExportService = pdfExportService;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //调用导出服务接口
        pdfExportService.make(model,document,pdfWriter,httpServletRequest,httpServletResponse);
    }
}
