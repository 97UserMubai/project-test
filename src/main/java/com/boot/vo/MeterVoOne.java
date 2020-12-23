package com.boot.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class MeterVoOne {
    @ExcelProperty(value = "电能表变更列表", index = 0)
    private String changeList;
    /**
     * 计量点唯一编号
     */
    @ExcelProperty(value = "计量点", index = 1)
    private String calculateNumber;
    /**
     * 变更标志（新装、拆除、更换前、更换后、暂停、恢复、抄表前、抄表后、移入、移出、换抽头前、换抽头后）
     */
    @ExcelProperty(value = "变更标志", index = 3)
    private String changeSign;
    @ExcelProperty(value = "出厂编号", index = 5)
    private String outCode;
    /**
     * 资产编号
     */
    @ExcelProperty(value = "资产编号", index = 7)
    private String code;
    @ExcelProperty(value = "箱(柜)号", index = 10)
    private String boxCode;
    @ExcelProperty(value = "位置号", index = 12)
    private String index;
    /**
     * 主副表标志（1-主表，0-副表）
     */
    @ExcelProperty(value = "主副表", index = 13)
    private String masterSign;

}
