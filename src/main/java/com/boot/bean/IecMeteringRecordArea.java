package com.boot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/11/25
 * <h>抄表区域表实体</h>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IecMeteringRecordArea {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 抄表区段编号
     */
    private String code;
    /**
     * 抄表区段名称
     */
    private String name;
    /**
     * 抄表方式
     */
    private String type;
    /**
     * 抄表顺序号
     */
    private Integer number;
    /**
     * 供电单位
     */
    private String supplier;
    /**
     * 管理人部门id
     */
    private String manageDepartmentId;
    /**
     * 管理人部门名称
     */
    private String manageDepartmentName;
    /**
     * 管理人Id
     */
    private String managePersonId;
    /**
     * 管理人名称
     */
    private String managePersonName;
    /**
     * 抄表部门id
     */
    private String recordDepartmentId;
    /**
     * 抄表部门名称
     */
    private String recordDepartmentName;
    /**
     * 抄表人id
     */
    private String recordPersonId;
    /**
     * 抄表人名称
     */
    private String recordPersonName;
    /**
     * 用途
     */
    private String usingType;
    /**
     * 抄表周期:0-不抄表，1-每月一次抄表，2-单月抄表，3-双月抄表，4-每月多次抄表，5-不定期抄表
     */
    private String period;
    /**
     * 抄表例日
     */
    private Integer recordDay;
    /**
     * 抄表次数:每次抄表后这个字段进行乐观锁更新
     */
    private Integer recordTimes;
    /**
     * 区段用户类型
     */
    private String custType;
    /**
     * 结算类型:0-分次，1-结算
     */
    private String settleType;
    /**
     * 计划电费发行日
     */
    private Integer planElecChargeDay;
    /**
     * 违约金起始日
     */
    private Integer penaltyBeginDay;
    /**
     * 状态:0-审核中，1-审核被退回，2-正常，3-审核未通过，4-注销
     */
    private String status;
    /**
     * 生效时间:0-当月生效，1-下月生效；当月生效：抄表轮换开始日期为当月1日；下月生效：抄表轮换开始日期为下月1日；
     */
    private String effectDayType;
    /**
     * 抄表轮换周期月数
     */
    private Integer recordCycleMonths;
    /**
     * 抄表轮换开始日期
     */
    private LocalDate recordCycleBeginDay;
    /**
     * 抄表轮换结束日期
     */
    private LocalDate recordCycleEndDay;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 备注
     */
    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IecMeteringRecordArea that = (IecMeteringRecordArea) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(number, that.number) &&
                Objects.equals(supplier, that.supplier) &&
                Objects.equals(manageDepartmentId, that.manageDepartmentId) &&
                Objects.equals(manageDepartmentName, that.manageDepartmentName) &&
                Objects.equals(managePersonId, that.managePersonId) &&
                Objects.equals(managePersonName, that.managePersonName) &&
                Objects.equals(recordDepartmentId, that.recordDepartmentId) &&
                Objects.equals(recordDepartmentName, that.recordDepartmentName) &&
                Objects.equals(recordPersonId, that.recordPersonId) &&
                Objects.equals(recordPersonName, that.recordPersonName) &&
                Objects.equals(usingType, that.usingType) &&
                Objects.equals(period, that.period) &&
                Objects.equals(recordDay, that.recordDay) &&
                Objects.equals(recordTimes, that.recordTimes) &&
                Objects.equals(custType, that.custType) &&
                Objects.equals(settleType, that.settleType) &&
                Objects.equals(planElecChargeDay, that.planElecChargeDay) &&
                Objects.equals(penaltyBeginDay, that.penaltyBeginDay) &&
                Objects.equals(status, that.status) &&
                Objects.equals(effectDayType, that.effectDayType) &&
                Objects.equals(recordCycleMonths, that.recordCycleMonths) &&
                Objects.equals(recordCycleBeginDay, that.recordCycleBeginDay) &&
                Objects.equals(recordCycleEndDay, that.recordCycleEndDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, type, number, supplier, manageDepartmentId, manageDepartmentName,
                managePersonId, managePersonName, recordDepartmentId, recordDepartmentName,
                recordPersonId, recordPersonName, usingType, period, recordDay, recordTimes,
                custType, settleType, planElecChargeDay, penaltyBeginDay, status, effectDayType,
                recordCycleMonths, recordCycleBeginDay, recordCycleEndDay);
    }
}