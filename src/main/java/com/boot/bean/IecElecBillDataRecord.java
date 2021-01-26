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
import java.time.LocalDateTime;

/**
 * 抄表数据记录表实体
 *
 * @author fengkunbin
 * @date 2021/1/13 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iec_elec_bill_data_record")
@Builder
public class IecElecBillDataRecord implements Comparable<IecElecBillDataRecord>, Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 用户名称
     */
    private String elecUserName;
    /**
     * 用户编号
     */
    private String elecUserCode;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 抄表业务类别代码
     */
    private String meterBussType;
    /**
     * 抄表区段编号
     */
    private String recordArea;
    /**
     * 抄表顺序号
     */
    private Integer recordNumber;
    /**
     * 抄表计划编号
     */
    private String planCode;
    /**
     * 电费年月yyyyMM
     */
    private String elecBillMonth;
    /**
     * 本期抄表次数
     */
    private Integer recordTimes;
    /**
     * 计量点编号
     */
    private String calcCode;
    /**
     * 计量点用途
     */
    private String calculateUse;
    /**
     * 资产编号
     */
    private String meterCode;
    /**
     * 抄表状态代码
     */
    private String meterStatus;
    /**
     * 抄表异常代码
     */
    private String meterUnusual;
    /**
     * 示数类型 0-正向有功总 1-正向有功尖峰 2-正向有功平 3-正向有功低谷 4-正向无功总 5-需量
     */
    private String type;
    /**
     * 本次表示数
     */
    private BigDecimal currentNum;
    /**
     * 表码位数
     */
    private String meterDigit;
    /**
     * 综合倍率
     */
    private BigDecimal rate;
    /**
     * 旧表电量
     */
    private BigDecimal oldPower;
    /**
     * 加减电量
     */
    private BigDecimal modifiedPower;
    /**
     * 表计电量
     */
    private BigDecimal meterPower;
    /**
     * 冻结时间(日期)
     */
    private LocalDateTime freezingTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 运行电能表标识
     */
    private String sign;

    @Override
    public int compareTo(IecElecBillDataRecord o) {
        int i = this.getCreateTime().compareTo(o.getCreateTime());
        return -i;
    }
}
