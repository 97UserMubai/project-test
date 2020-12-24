package com.boot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 业扩-现场勘查-电流互感器/电压互感器
 * </p>
 *
 * @author huanghebin
 * @since 2020-12-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iec_elec_biz_device_inductance_transformer")
@Builder
public class IecElecBizDeviceInductanceTransformer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 运行互感器标识
     */
    private String sign;

    /**
     * 变更标志（新装、拆除、更换前、更换后、暂停、恢复、抄表前、抄表后、移入、移出、换抽头前、换抽头后）
     */
    private String changeSign;

    /**
     * 计量点编号
     */
    private String calculateNumber;

    /**
     * 拆装标志（0否；1是）
     */
    private Boolean installSign;

    /**
     * 产权归属代码（01 局属、02 用户）
     */
    private String ownershipCode;

    /**
     * 执行班组（组织部门）
     */
    private String executor;

    /**
     * 资产编号
     */
    private String code;

    /**
     * 设备类型代码（电流互感器；电压互感器；组合式互感器）
     */
    private String typeCode;

    /**
     * 额定电压（220V
     * 3×220/380V
     * 3×57.7/100V
     * 3×380V
     * 3×100V）
     */
    private String voltage;

    /**
     * 相别（A相
     * B相
     * C相
     * AB相
     * AC相
     * BC相
     * AB相+ BC相）
     */
    private String phase;

    /**
     * TA变比
     */
    private String rateTa;

    /**
     * TV变比
     */
    private String rateTv;

    /**
     * TA准确度等级
     */
    private String precisionTa;

    /**
     * TV准确度等级
     */
    private String precisionTv;

    /**
     * 类型（0-TV；1-TA）
     */
    private Boolean type;

    /**
     * 工单号
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
