package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h>节点5计量装置数据打印VO</h>
 * @Date 2020/12/22
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentPrintVO {
    /**
     * 工作单编号
     */
    private String orderCode;
    /**
     * 业务类别
     */
    private String type;
    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 供电单位
     */
    private String supplier;
    /**
     * 用户名称
     */
    private String custName;
    /**
     * 用电地址
     */
    private String address;
    /**
     * 经办人
     */
    private String manager;
    /**
     * 电话
     */
    private String phone;
    /**
     * 用电类别:0-大工业用电,1-普通工业，2-商业，3-趸售，4-居民生活，5-非居民，6-农业生产，7-农业排灌，8-其他用电，9-非工业
     */
    private String elecType;
    /**
     * 合同容量
     */
    private Integer contractCapacity;
    /**
     * 计量方式:1-高供高计、2-高供低计、3-低供低计
     */
    private String meterage;
    /**
     * 打印日期
     */
    private String printDate;
}
