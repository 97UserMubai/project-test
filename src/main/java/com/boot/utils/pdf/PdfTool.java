package com.boot.utils.pdf;

import com.itextpdf.text.Document;

import java.io.OutputStream;

/**
 * @author wangbaitao
 * <h>pdf工具基础类</h>
 * @Date 2021/02/05
 */
public class PdfTool {
    //pdf基本容器
    protected Document document;
    //输出流
    protected OutputStream os;

    public Document getDocument() {
        if (document == null) {
            document = new Document();
        }
        return document;
    }
}