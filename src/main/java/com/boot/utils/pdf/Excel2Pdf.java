package com.boot.utils.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;


/**
 * @author wangbaitao
 * <h>excel转pdf</h>
 * @Date 2021/02/05
 */
public class Excel2Pdf extends PdfTool {
    protected ExcelObject object;
    /**
     * PDF 輸出紙張大小
     */
    protected Rectangle pageSize;

    /**
     * <p>
     * Description: 导出单项PDF，不包含目录
     * </p>
     */
    public Excel2Pdf(ExcelObject object, OutputStream os, Rectangle pageSize) {
        this.object = object;
        this.os = os;
        this.pageSize = pageSize;
    }

    /**
     * <p>
     * Description: 转换调用
     * </p>
     *
     * @throws DocumentException     DocumentException
     * @throws MalformedURLException MalformedURLException
     * @throws IOException           IOException
     */
    public void convert() throws DocumentException, MalformedURLException, IOException {
        getDocument().setPageSize(pageSize);
        PdfWriter writer = PdfWriter.getInstance(getDocument(), os);
        writer.setPageSize(pageSize);
        // Open document
        getDocument().open();
        // Single one
        PdfPTable table = this.toCreatePdfTable(this.object);
        getDocument().add(table);
        getDocument().close();
    }

    /**
     * 通过iText的ExcelObject进行PDFCell的转换
     *
     * @param object excel对象
     * @return PdfPTable
     * @throws IOException       IOException
     * @throws DocumentException DocumentException
     */
    protected PdfPTable toCreatePdfTable(ExcelObject object) throws IOException, DocumentException {
        PdfPTable table = new PdfTableExcel(object).getTable();
        table.setKeepTogether(true);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        // 表格置中
        table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
        return table;
    }
}