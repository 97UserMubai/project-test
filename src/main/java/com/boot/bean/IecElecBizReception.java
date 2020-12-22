package com.boot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/10/14
 * <h></h>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iec_elec_biz_reception")
@Builder
public class IecElecBizReception implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 工作单编号
     */
    private String orderCode;

    /**
     * 业务类别
     */
    @NotBlank(message = "业务类型不能为空")
    private String type;

    /**
     * 业务子类
     */
    @NotBlank(message = "业务子类不能为空")
    private String subtype;

    /**
     * 正式受理日期
     */
    @NotNull(message = "受理日期不能为空")
    private LocalDate acceptDate;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 客户编号
     */
    @NotBlank(message = "客户号不能为空")
    private String custCode;

    /**
     * 供电单位
     */
    private String supplier;

    /**
     * 原用户名称
     */
    private String oldCustName;

    /**
     * 客户身份
     */
    private String customerIdentity;

    /**
     * 用户名称
     */
    @NotBlank(message = "用电户名称不能为空")
    private String custName;

    /**
     * 城乡标志
     */
    private String sign;

    /**
     * 用电地址
     */
    @NotBlank(message = "用电地址不能为空")
    private String address;

    /**
     * 单位通邮地址
     */
    private String postAddress;

    /**
     * 关联工作单编号
     */
    @NotBlank(message = "关联工作单编号不能为空")
    private String relatedOrderId;

    /**
     * 所在楼层
     */
    @Min(value = 1, message = "楼层不能小于1")
    @Max(value = 99, message = "楼层不能大于99")
    private Integer floor;

    /**
     * 重要客户标志 0-否 1-是
     */
    private String importantSign;

    /**
     * 行政区域
     */
    private String administration;

    /**
     * 邮政编码
     */
    private String code;

    /**
     * 传真号码
     */
    private String faxNumber;

    /**
     * 工作单来源
     */
    private String source;

    /**
     * 村地址
     */
    private String villageAddress;

    /**
     * 是否上门服务 0-否 1-是
     */
    private String homeService;

    /**
     * 受电工程公示 0-否 1-是
     */
    private String publicity;

    /**
     * 受理单位
     */
    private String accept;

    /**
     * 开始日期
     */
    @NotNull(message = "开始日期不能为空")
    private LocalDate startDate;

    /**
     * 到期日期
     */
    @NotNull(message = "到期日期不能为空")
    private LocalDate endDate;

    /**
     * 抄表周期
     */
    private String cycle;

    /**
     * 经办人
     */
    @NotBlank(message = "经办人不能为空")
    private String manager;

    /**
     * 证件类型
     */
    private String certificateType;

    /**
     * 证件号码
     */
    @NotBlank(message = "证件号码不能为空")
    private String certificateNumber;

    /**
     * 电话
     */
    @Pattern(regexp = "^1(3|4|5|7|8|9)\\d{9}$", message = "手机号码格式不正确！")
    private String phone;

    /**
     * 预计月用电量：（kwh）
     */
    private Long monthElectricity;

    /**
     * 计划完成日期
     */
    private LocalDate planCompleteDate;

    /**
     * 规划容量：（kVA）
     */
    private Long planCapacity;

    /**
     * 付费模式
     */
    private String paymentMode;

    /**
     * 增减容量：（kVA）
     */
    private Long capacity;

    /**
     * 原容量：（kVA）
     */
    private Long oldCapacity;

    /**
     * 备注
     */
    private String remark;

    /**
     * 办理情况
     */
    private String handleSchedule;

    /**
     * 传入人员
     */
    private String inputPerson;

    /**
     * 传入时间
     */
    private LocalDateTime createTime;

    /**
     * 办理完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 结算户id
     */
    private String custSettleAccount;
}