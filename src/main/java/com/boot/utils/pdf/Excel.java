package com.boot.utils.pdf;

import lombok.Data;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author wangbaitao
 * <h>excel数据实体</h>
 * @Date 2021/02/05
 */
@Data
public class Excel {

    protected Workbook wb;
    protected Sheet sheet;

    public Excel(Workbook wb) {
        try {
            this.wb = wb;
            this.sheet = wb.getSheetAt(wb.getActiveSheetIndex());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Sheet getSheet() {
        return sheet;
    }

    public Workbook getWorkbook() {
        return wb;
    }
}
