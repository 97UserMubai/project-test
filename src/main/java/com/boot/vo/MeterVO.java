package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h>计量点-电能表VO</h>
 * @Date 2020/12/22
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeterVO {
    /**
     * 计量点唯一编号
     */
    private String calculateNumber;

    /**
     * 变更标志（新装、拆除、更换前、更换后、暂停、恢复、抄表前、抄表后、移入、移出、换抽头前、换抽头后）
     */
    private String changeSign;

    /**
     * 主副表标志（1-主表，0-副表）
     */
    private String masterSign;

    /**
     * 资产编号
     */
    private String code;

    /**
     * 综合倍率
     */
    private BigDecimal rate;
    /**
     * 额定电压（220V
     * 3×220/380V
     * 3×57.7/100V
     * 3×380V
     * 3×100V）
     */
    private String voltage;
    /**
     * 标定电流（dataType）
     */
    private String current;
    /**
     * 准确度等级（dataType）
     */
    private String precisionLevel;
    /**
     * 设备类型代码（单相感应式长寿命电能表
     */
    private String type;
    /**
     * 计量方式:1-高供高计、2-高供低计、3-低供低计
     */
    private String meterage;

}
