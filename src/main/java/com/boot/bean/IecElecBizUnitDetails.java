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
 * @Date 2020/12/14
 * <h>单位信息表</h>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iec_elec_biz_unit_details")
@Builder
public class IecElecBizUnitDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 单位名称
     */
    private String name;
    /**
     * 类型：0-施工单位 1-试验单位 2-设计单位
     */
    private String type;
    /**
     * 业务范围
     */
    private String businessScope;
    /**
     * 资质证书编号
     */
    private String certificateCode;
    /**
     * 资质等级
     */
    private String certificateLevel;
    /**
     * 关联企业
     */
    private String relatedEnterprise;
    /**
     * 有效期限
     */
    private LocalDate effectiveDate;
    /**
     * 法人代表
     */
    private String legalPerson;
    /**
     * 颁发机构
     */
    private String issuingAuthority;
    /**
     * 联系人名称
     */
    private String concatName;
    /**
     * 联系电话
     */
    private String phone;
}