package com.boot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 业扩-现场勘查-电能表
 * </p>
 *
 * @author huanghebin
 * @since 2020-12-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iec_elec_biz_device_meter")
@Builder
public class IecElecBizDeviceMeter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 运行电能表标识
     */
    private String sign;

    /**
     * 变更标志（新装、拆除、更换前、更换后、暂停、恢复、抄表前、抄表后、移入、移出、换抽头前、换抽头后）
     */
    private String changeSign;

    /**
     * 拆装标志（0-否；1-是）
     */
    private Boolean installSign;

    /**
     * 综合倍率
     */
    private BigDecimal rate;

    /**
     * 计量点唯一编号
     */
    private String calculateNumber;

    /**
     * 主副表标志（1-主表，0-副表）
     */
    private Boolean masterSign;

    /**
     * 产权归属代码（01 局属、02 用户）
     */
    private String ownershipCode;

    /**
     * 执行班组
     */
    private String executor;

    /**
     * 资产编号
     */
    private String code;

    /**
     * 设备类型代码（单相感应式长寿命电能表
     * 三相三线感应式长寿命有功电能表
     * 三相三线感应式长寿命无功电能表
     * 三相四线感应式长寿命有功电能表
     * 三相四线感应式长寿命无功电能表
     * RS-485接口单相电子式电能表
     * 单相载波电子式电能表
     * 单相费控电子式电能表
     * 单相复费率电能表
     * 三相四线电子式电能表
     * 三相三线电子式电能表
     * 三相四线载波电子式电能表
     * 三相三线多功能电能表
     * 三相四线多功能电能表
     * 三相三线多功能电能表（费控）
     * 三相四线多功能电能表（费控）
     * 三相三线多功能电能表（进口）
     * 三相四线多功能电能表（进口）
     * 三相三线多功能电能表（0.2S）
     * 三相四线多功能电能表（0.2S）
     * 三相三线多功能电能表（0.2S/进口）
     * 三相四线多功能电能表（0.2S/进口））
     */
    private String type;

    /**
     * 是否进口的标志（0：否，1：是）
     */
    private Boolean inport;

    /**
     * 拉闸标志（0-否；1-是）
     */
    private Boolean shutSign;

    /**
     * 相线类别（单相、三相四线、三相三线）
     */
    private String phaseLine;

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
     * 相位代码（A相、B相、C相）
     */
    private String phase;

    /**
     * 工单编号
     */
    private String orderCode;

    /**
     * 用电户编号
     */
    private String elecUserCode;

    /**
     * 线路图元件id
     */
    private String diagramId;
}
