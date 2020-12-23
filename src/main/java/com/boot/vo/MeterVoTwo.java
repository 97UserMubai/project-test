package com.boot.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/23
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeterVoTwo {
    /**
     * 计量方式:1-高供高计、2-高供低计、3-低供低计
     */
    @ExcelProperty(value = "计量方式",index = 3)
    private String meterage;
    /**
     * 综合倍率
     */
    @ExcelProperty(value = "综合倍率",index = 5)
    private BigDecimal rate;
    /**
     * 额定电压（220V
     * 3×220/380V
     * 3×57.7/100V
     * 3×380V
     * 3×100V）
     */
    @ExcelProperty(value = "额定电压",index = 7)
    private String voltage;
    /**
     * 标定电流（dataType）
     */
    @ExcelProperty(value = "标定电流",index = 9)
    private String current;
    /**
     * 准确度等级（dataType）
     */
    @ExcelProperty(value = "准确度等级",index = 11)
    private String precisionLevel;

}
