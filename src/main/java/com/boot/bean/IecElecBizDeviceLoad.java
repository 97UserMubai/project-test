package com.boot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/10/14
 * <h></h>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iec_elec_biz_device_load")
@Builder
public class IecElecBizDeviceLoad implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 工作单编号
     */
    private String orderCode;

    /**
     * 运行终端标识
     */
    private String terminalSign;

    /**
     * 变更标志
     */
    private String changeSign;

    /**
     * 计量点编号
     */
    private String calculateNumber;

    /**
     * 拆装标志
     */
    private Boolean assembleSign;

    /**
     * 安装地址
     */
    private String installationAddress;

    /**
     * 产权归属（01 局属、02 用户）
     */
    private String propertyOwnership;

    /**
     * 执行班组
     */
    private String executeTeam;

    /**
     * 资产编号
     */
    private String propertyNumber;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 额定电压
     */
    private String nominalVoltage;

    /**
     * 标定电流
     */
    private String ratedCurrent;

    /**
     * 通讯方式（红外通信
     * RS485通信
     * 载波通信
     * 无线电通信
     * GPRS通信
     * CDMA通信
     * 光纤通信
     * WIFI通信
     * 蓝牙通信
     * RJ-11
     * RJ-45
     * RS232）
     */
    private String communicationMode;

    /**
     * 通讯规约（DL/T645
     * IEC1107
     * GB/T 19897.1-2005（IEC62056-21）
     * DL/T698）
     */
    private String communicationProtocol;

    /**
     * 用电户编号
     */
    private String elecUserCode;

    /**
     * 线路图元件id
     */
    private String diagramId;

    /**
     * 电能表标识（关联电能表）
     */
    private String meterSign;

    /**
     * 电能表资产编号
     */
    private String meterCode;

    /**
     * 负荷终端通讯地址
     */
    private String communicationAddress;

    /**
     * 采集对象通讯地址
     */
    private String collectionAddress;

    /**
     * 电能表设备类型
     */
    private String meterType;

    /**
     * 采集类型
     */
    private String collectionType;

    /**
     * 采集对象通讯方式（红外通信
     * RS485通信
     * 载波通信
     * 无线电通信
     * GPRS通信
     * CDMA通信
     * 光纤通信
     * WIFI通信
     * 蓝牙通信
     * RJ-11
     * RJ-45
     * RS232）
     */
    private String collectionWay;

    /**
     * 采集对象通讯规约
     */
    private String collectionProtocol;

    /**
     * 测量点号
     */
    private String testPoint;

    /**
     * 变更日期
     */
    private LocalDate changeDate;

}