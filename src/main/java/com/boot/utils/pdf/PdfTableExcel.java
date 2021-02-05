package com.boot.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbaitao
 * <h>将excel 和 value+type转换成pdf cell</h>
 * @Date 2021/02/05
 */
public class PdfTableExcel {
    // ExcelObject
    protected ExcelObject excelObject;
    // excel
    protected Excel excel;
    protected boolean setting = false;

    /**
     * <p>
     * Description: Constructor
     * </p>
     *
     * @param excelObject iText excel Object
     */
    public PdfTableExcel(ExcelObject excelObject) {
        this.excelObject = excelObject;
        this.excel = excelObject.getExcel();
    }

    /**
     * <p>
     * Description: 获取转换过的Excel内容Table
     * </p>
     *
     * @return PdfPTable
     * @throws BadElementException   BadElementException
     * @throws MalformedURLException MalformedURLException
     * @throws IOException           IOException
     */
    public PdfPTable getTable() throws MalformedURLException, IOException, DocumentException {
        Sheet sheet = this.excel.getSheet();
        return toParseContent(sheet);
    }

    /**
     * 进行sheet 内容的转换
     *
     * @param sheet sheet
     * @return PdfPTable
     * @throws IOException       IOException
     * @throws DocumentException DocumentException
     */
    protected PdfPTable toParseContent(Sheet sheet) throws IOException, DocumentException {
        int rowlength = sheet.getLastRowNum() + 1;
        List<PdfPCell> cells = new ArrayList<>();
        float mw = 0;
        List<Float> columnWidths = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
            columnWidths.add(0f);
        int columnCount = 0;
        for (int i = 1; i < rowlength; i++) {
            Row row = sheet.getRow(i);
            float[] cws = new float[row.getLastCellNum()];
            int colCount = row.getLastCellNum();
            for (int j = 0; j < colCount; j++) {
                if (colCount > columnCount)
                    columnCount = colCount;
                Cell cell = row.getCell(j);
                // POI 的 单元格为空时进行创建
                if (cell == null) {
                    cell = row.createCell(j);
                }
                float cw = getPOIColumnWidth(cell);
                cws[cell.getColumnIndex()] = cw;
                columnWidths.set(cell.getColumnIndex(), cw);
                if (isUsed(cell.getColumnIndex(), row.getRowNum())) {
                    continue;
                }
                //cell.setCellType(Cell.CELL_TYPE_STRING);
                CellRangeAddress range = getColspanRowspanByExcel(row.getRowNum(), cell.getColumnIndex());
                int rowspan = 1;
                int colspan = 1;
                if (range != null) {
                    rowspan = range.getLastRow() - range.getFirstRow() + 1;
                    colspan = range.getLastColumn() - range.getFirstColumn() + 1;
                }
                // PDF单元格
                PdfPCell pdfpCell = new PdfPCell();

                int[] rgb = getBackgroundColor(cell.getCellStyle());
                pdfpCell.setBackgroundColor(new BaseColor(rgb[0], rgb[1], rgb[2]));
                pdfpCell.setColspan(colspan);
                pdfpCell.setRowspan(rowspan);
                pdfpCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfpCell.setPhrase(getPhrase(cell));
                pdfpCell.setMinimumHeight(row.getHeightInPoints() / 28.6f * 26f);
                // 设置自动换行
                pdfpCell.setNoWrap(false);
                pdfpCell.setPaddingBottom(3);

                addBorderByExcel(pdfpCell, cell.getCellStyle());
                addImageByPOICell(pdfpCell, cell);
                //
                cells.add(pdfpCell);
                j += colspan - 1;
            }
            // excel 的 columns不足，会导致次行的单元格*缺少的元素数 向上一行拼接，这里需要进行pdf cell的补偿
            // 另外：如果同一列的单元格数据不一致的时候，也会出现这种情况
            // e.g  行1~20的columns = 19 行20的columns = 20 , 则 前面19 行的empty column = 1，前面行会自动填充，
            // 导致前面行需要补充的数据为20，最后错位
            int emptyColumns = columnCount - colCount;
            if (emptyColumns > 0) {
                PdfPCell pdfpCell = new PdfPCell();
                pdfpCell.setColspan(emptyColumns);
                pdfpCell.setRowspan(1);
                cells.add(pdfpCell);
            }
            float rw = 0;
            for (float cw : cws) {
                rw += cw;
            }
            if (rw > mw || mw == 0) {
                mw = rw;
            }
        }

        PdfPTable table = new PdfPTable(columnCount);
        // 設定表格的欄位寬度
        float[] colWidths = new float[columnCount];
        for (int i = 0; i < columnCount; i++)
            colWidths[i] = columnWidths.get(i);
        table.setWidths(colWidths);
        table.setWidthPercentage(100);
        // table.setLockedWidth(true);
        for (PdfPCell pdfpCell : cells) {
            table.addCell(pdfpCell);
        }
        return table;
    }

    /**
     * 进行cell内容的字符串转换
     *
     * @param cell 单元格
     * @return Phrase
     */
    protected Phrase getPhrase(Cell cell) {
        XSSFCellStyle cellStyle = (XSSFCellStyle) cell.getCellStyle();
        String formatStr = cellStyle.getDataFormatString();
        double cellNumberValue = 0;
        // 判断是否是数字
        boolean isNumber = cell.getCellType() == Cell.CELL_TYPE_NUMERIC;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            try {
                cellNumberValue = cell.getNumericCellValue();// Double.parseDouble(cellValue);
                isNumber = true;
            } catch (Exception e) {
                isNumber = false;
            }
        }
        // 判断是否有独特的format格式
        if (!"general".equals(formatStr.toLowerCase()) && isNumber) {
            String numberFormat = formatStr;
            int firstFormatIdx = formatStr.indexOf(";");
            if (firstFormatIdx > 0)
                numberFormat = formatStr.substring(0, firstFormatIdx);
            String formattedValue = new CellNumberFormatter(numberFormat).format(cellNumberValue);
            Phrase phrase = new Phrase(formattedValue, getFontByExcel(cell.getCellStyle()));
            return phrase;
        }
        // 进行string 转换
        cell.setCellType(Cell.CELL_TYPE_STRING);
        if (this.setting || this.excelObject.getAnchorName() == null) {
            return new Phrase(cell.getStringCellValue(), getFontByExcel(cell.getCellStyle()));
        }
        Anchor anchor = new Anchor(cell.getStringCellValue(), getFontByExcel(cell.getCellStyle()));
        anchor.setName(this.excelObject.getAnchorName());
        this.setting = true;
        return anchor;
    }

    /**
     * 添加图像
     *
     * @param pdfpCell pdfpCell
     * @param cell     cell
     * @throws BadElementException BadElementException
     * @throws IOException         IOException
     */
    protected void addImageByPOICell(PdfPCell pdfpCell, Cell cell) throws BadElementException, IOException {
        POIImage poiImage = new POIImage().getCellImage(cell);
        byte[] bytes = poiImage.getBytes();
        if (bytes != null) {
            pdfpCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            Image image = Image.getInstance(bytes);
            pdfpCell.setImage(image);
        }
    }

    /**
     * <p>
     * Description:获取excel的列宽像素，无法精确实现，需要优化
     * </p>
     *
     * @param cell cell
     * @return 像素宽
     */
    protected int getPOIColumnWidth(Cell cell) {
        int colWidthpoi = excel.getSheet().getColumnWidth(cell.getColumnIndex());
        int widthPixel;
        if (colWidthpoi >= 416) {
            widthPixel = (int) (((colWidthpoi - 416.0) / 256.0) * 8.0 + 13.0 + 0.5);
        } else {
            widthPixel = (int) (colWidthpoi / 416.0 * 13.0 + 0.5);
        }
        return widthPixel;
    }

    protected CellRangeAddress getColspanRowspanByExcel(int rowIndex, int colIndex) {
        CellRangeAddress result = null;
        Sheet sheet = excel.getSheet();
        int num = sheet.getNumMergedRegions();
        for (int i = 0; i < num; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            if (range.getFirstColumn() == colIndex && range.getFirstRow() == rowIndex) {
                result = range;
            }
        }
        return result;
    }

    protected boolean isUsed(int colIndex, int rowIndex) {
        boolean result = false;
        Sheet sheet = excel.getSheet();
        int num = sheet.getNumMergedRegions();
        for (int i = 0; i < num; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            if (firstRow < rowIndex && lastRow >= rowIndex) {
                if (firstColumn <= colIndex && lastColumn >= colIndex) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * 获取字体设置
     *
     * @param style CellStyle
     * @return Font
     */
    protected Font getFontByExcel(CellStyle style) {
        Workbook wb = excel.getWorkbook();
        // 字体样式索引
        short index = style.getFontIndex();
        org.apache.poi.ss.usermodel.Font font = wb.getFontAt(index);
        // 进行iText Font 转换
        Font itextFont = Resource.getFont((XSSFFont) font);
        assert itextFont != null;
        itextFont.setStyle(Font.NORMAL);
        // 字体颜色
        int colorIndex = font.getColor();
        HSSFColor color = HSSFColor.getIndexHash().get(colorIndex);
        if (color != null) {
            int rbg = POIUtil.getRGB(color);
            itextFont.setColor(new BaseColor(rbg));
        }
        // 下划线
        FontUnderline underline = FontUnderline.valueOf(font.getUnderline());
        if (underline == FontUnderline.SINGLE) {
            String ulString = Font.FontStyle.UNDERLINE.getValue();
            itextFont.setStyle(ulString);
        }
        return itextFont;
    }

    /**
     * 获取背景颜色
     *
     * @param style style
     * @return rgb数组
     */
    protected int[] getBackgroundColor(CellStyle style) {
        Color color = style.getFillForegroundColorColor();
        return POIUtil.getColorRGB(color);
    }

    /**
     * 进行Border设置
     *
     * @param cell  cell
     * @param style style
     */
    protected void addBorderByExcel(PdfPCell cell, CellStyle style) {
        Workbook wb = excel.getWorkbook();
        cell.setBorderColorLeft(new BaseColor(POIUtil.getBorderRBG(wb, style.getLeftBorderColor())));
        cell.setBorderColorRight(new BaseColor(POIUtil.getBorderRBG(wb, style.getRightBorderColor())));
        cell.setBorderColorTop(new BaseColor(POIUtil.getBorderRBG(wb, style.getTopBorderColor())));
        cell.setBorderColorBottom(new BaseColor(POIUtil.getBorderRBG(wb, style.getBottomBorderColor())));
        // 设置四个边线
        cell.enableBorderSide(PdfPCell.LEFT);
        cell.enableBorderSide(PdfPCell.RIGHT);
        cell.enableBorderSide(PdfPCell.TOP);
        cell.enableBorderSide(PdfPCell.BOTTOM);
    }
}