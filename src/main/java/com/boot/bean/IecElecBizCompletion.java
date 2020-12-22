package com.boot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/10/14
 * <h>受理竣工表</h>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iec_elec_biz_completion")
@Builder
public class IecElecBizCompletion implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 工作单编号
     */
    private String orderCode;
    /**
     * 工程项目名称
     */
    private String projectName;
    /**
     * 客户工程标志，来自供电方案表
     */
    private String customerEngineeringMark;
    /**
     * 客户工程概况
     */
    private String projectIntroduction;
    /**
     * 受理人
     */
    private String receiver;
    /**
     * 受理日期
     */
    private LocalDate receiverDate;
    /**
     * 开工日期
     */
    private LocalDate startDate;
    /**
     * 完工日期
     */
    private LocalDate completeDate;
    /**
     * 客户自检结论
     */
    private String selfTest;
    /**
     * 是否分期报竣工 0-否 1-是
     */
    private Integer isInstallment;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 单位信息ids，关联的单位信息数据转化成的JSON数据
     */
    private String unitIds;
    /**
     * 关联的单位信息集合
     */
    @TableField(exist = false)
    private List<IecElecBizUnitDetails> iecElecBizUnitDetails;
}