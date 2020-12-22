package com.boot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/10/14
 * <h>用电客户信息实体类</h>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iec_customer_elec_user")
@Builder
public class IecCustomerElecUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 用电编号
     */
    private String code;
    /**
     * 用电户名称
     */
    private String name;
    /**
     * 客户id
     */
    private String custId;
    /**
     * 客户编号
     */
    private String custCode;
    /**
     * 客户名称
     */
    private String custName;
    /**
     * 客户类型：01-充电站客户 02港口岸电客户 03自备电厂户 04变电站管理户 05线路联络户 06公线专变客户 07专线专变客户 08公变客户
     * 09趸售关口户 10地方电厂户 11线路考核表户 12台区考核表户 13省网关口表户 14变电站考核表户
     * 15站用变考核表户 16窃电黑户 17线路线损台区考核表虚户 18电能替代考核表户 19光伏发电客户
     */
    private String custType;
    /**
     * 用电地址
     */
    private String address;
    /**
     * 电压等级: 01 交流110V 02 交流220V 03 交流380V 04 交流660V 05 交流1000V 06 交流3kV 07 交流6kV 08 交流10kV
     * 09 交流20kV 10 交流35kV 11 交流66kV 12 交流110kV 13 交流220kV 14 交流330kV 15 交流500kV 16 交流750kV
     * 17 交流1000kV 20 交流5V 21 交流6V 22 交流12V 23 交流15V 24 交流24V 25 交流36V 26 交流42V
     * 27 交流48V 28 交流60V 29 交流100V 30 交流127V 31 交流115V 32 交流230V 33 交流400V
     * 34 交流690V 35 交流3150V 36 交流6300V 37 交流10.5kV 38 交流13.8kV 39 交流15.75kV
     * 40 交流18kV 42 交流22kV 43 交流24kV 44 交流26kV 45 交流132kV 46 交流400kV 49 其它交流电压
     * 51 直流24V 52 直流36V 53 直流48V 54 直流110V 55 直流220V 56 直流1.2V 57 直流1.5V
     * 58 直流2V 59 直流2.4V 60 直流3V 61 直流4.5V 62 直流5V 63 直流6V 64 直流9V 65 直流12V
     * 66 直流15V 67 直流30V 68 直流60V 69 直流72V 70 直流160V 71 直流400V 72 直流440V
     * 73 直流630V 74 直流800V 75 直流1000V 76 直流1250V 77 直流1500V 78 直流2000V
     * 79 直流3000V 80 直流160kV 81 直流500kV 82 直流700kV 83 直流800kV 91 直流115V
     * 92 直流230V 93 直流460V 94 直流600V 95 直流750V 99 其它直流电压 00 不区分电压等级
     */
    private String voltageLevel;
    /**
     * 计量方式:1-高供高计、2-高供低计、3-低供低计
     */
    private String meterage;
    /**
     * 用电类别:0-大工业用电,1-普通工业，2-商业，3-趸售，4-居民生活，5-非居民，6-农业生产，7-农业排灌，8-其他用电，9-非工业
     */
    private String elecType;
    /**
     * 原用户编号
     */
    private String originCode;
    /**
     * 原地址
     */
    private String originAddress;
    /**
     * 邮政编码
     */
    private String postCode;
    /**
     * 单位通邮地址
     */
    private String postAddress;
    /**
     * 所在楼层
     */
    private Integer floor;
    /**
     * 传真号码
     */
    private String fax;
    /**
     * 抄表区段编号
     */
    private String recordArea;
    /**
     * 抄表顺序号
     */
    private Integer recordNumber;
    /**
     * 自定义查询号
     */
    private String queryNumber;
    /**
     * 客户身份：0-业主、1-租户
     */
    private String identity;
    /**
     * 供电单位
     */
    private String elecSupplier;
    /**
     * 预付费类型：0-预售电、1-预付费、2-预收、3-非预付
     */
    private String perPayType;
    /**
     * 临时缴费关系号
     */
    private String tempPayNumber;
    /**
     * 是否套餐用户标志：0-否，1-是
     */
    private String packageFlag;
    /**
     * 城乡标志：0-城市、1-乡村
     */
    private String urbanFlag;
    /**
     * 立户日期
     */
    private LocalDate createDate;
    /**
     * 送电日期
     */
    private LocalDate supplyDate;
    /**
     * 销户日期
     */
    private LocalDate cancelDate;
    /**
     * 用户状态：0-正常用电客户，1-当前新装客户，2-当前变更客户，3-已销户客户。
     */
    private String state;
    /**
     * 抄表周期：0-不抄表,1-每月一次抄表,2-单月抄表,3-双月抄表,4-每月多次抄表,5-不定期抄表
     */
    private String recordPeriod;
    /**
     * 阶梯类型：0-阶梯计费、1-合表计费、2-分时阶梯计费、3-卡表计费
     */
    private String stepType;
    /**
     * 村代码：农村电费公布榜需要
     */
    private String villageCode;
    /**
     * 三重项目:0-否，1-是
     */
    private String tripleProject;
    /**
     * 档案袋号
     */
    private String documentCode;
    /**
     * 档案存放位置
     */
    private String documentPlace;
    /**
     * 所在城市
     */
    private String locationCity;
    /**
     * 市场化交易户：0-否，1-是
     */
    private String marketAccount;
    /**
     * 客户分群:0-重要客户、1-大客户、2-重点关注客户、3-居民客户、4-其它客户
     */
    private String custGroup;
    /**
     * 客户细分
     */
    private String firstDetailDivide;
    /**
     * 二级细分
     */
    private String secondDetailDivide;
    /**
     * 三级细分
     */
    private String thirdDetailDivide;
    /**
     * 信誉等级:通过信誉评估评估出来的客户的信誉等级：0-最高信誉度等级，1-较高信誉度等级，
     * 2-高信誉度等级，3-中最高信誉度等级，4-中较高信誉度等级等
     */
    private String creditLevel;
    /**
     * 信誉分值
     */
    private Integer creditScore;
    /**
     * 价值等级：1-1级，2-2级，3-3级
     */
    private String worthLevel;
    /**
     * 风险等级:风险等级:通过风险评估评估出的客户的风险等级：0-最高风险等级，
     * 1-较高风险等级，2-高风险等级，3-中最高风险等级，4-中较高风险等级等
     */
    private String riskLevel;
    /**
     * 客户经理
     */
    private String custManager;
    /**
     * 负荷性质：负荷的重要程度分类：一类，二类，三类
     */
    private String loadFlag;
    /**
     * 冲击负荷用户标志：0-否，1-是
     */
    private String impactLoadFlag;
    /**
     * 高可靠性标志：0-否，1-是
     */
    private String reliabilityFlag;
    /**
     * 行业分类：用电客户的行业分类代码,引用国标GB/T 4754-2002
     */
    private String industryType;
    /**
     * 新兴产业
     */
    private String newIndustry;
    /**
     * 高耗能行业类别：0-钢铁 1-电解铝 2-铁合金 3-水泥 4-电石 5-烧碱 6-黄磷 7-锌冶炼 8-造纸 9-铸造 10-化肥 11-碳化硅
     * 12-碳素 13-其他
     */
    private String highConsumeType;
    /**
     * 谐波用户标志:0-否，1-是
     */
    private String harmonicsFlag;
    /**
     * 行政区域
     */
    private String adminArea;
    /**
     * 生产班次：0-单班，1-二班，3-三班，4-连续生产
     */
    private String productClasses;
    /**
     * 厂休日：周休日通过数字连续表示周休哪几天，类似于飞机航班日期表示，如1.2.3,表示星期一星期二和星期三休息。
     */
    private String restDay;
    /**
     * 停电方式
     */
    private String elecCutWay;
    /**
     * 是否小微企业标志：0-否，1-是
     */
    private String microFlag;
    /**
     * 合同容量
     */
    private Integer contractCapacity;
    /**
     * 运行容量
     */
    private Integer runCapacity;
    /**
     * 自备电源容量
     */
    private Integer selfCapacity;
    /**
     * 电源类型：0-单电源单回路、1-单电源双回路、2-双电源同站双回路、3-双电源异站双回路
     */
    private String sourceType;
    /**
     * 电源联锁方式：01 电气联锁、02 机械联锁、03 机械和电气联锁
     */
    private String sourceLockWay;
    /**
     * 电源切换方式：01 手动、02 自动
     */
    private String sourceChangeWay;
    /**
     * 自备电源标志:0-否，1-是
     */
    private String sourceSelfFlag;
    /**
     * 自备电源闭锁方式
     */
    private Integer sourceSelfLockWay;
    /**
     * 是否自备电厂标志：0-否，1-是
     */
    private String selfFactoryFlag;
    /**
     * 自备电厂容量
     */
    private Integer selfFactoryCapacity;
    /**
     * 转供类型：0-无转供，1-转供户，2-被转供户
     */
    private String changeSupplyType;
    /**
     * 停电类型：0-计划停电、1-临时停电、2-故障停电、3-欠费停电、4-违约停电
     */
    private String elecCutType;
    /**
     * 保供电标识：0-否，1-是
     */
    private String guaranteeSupplyFlag;
    /**
     * 保供电开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime guaranteeSupplyStartTime;
    /**
     * 保供电结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime guaranteeSupplyEndTime;
    /**
     * 临时用电标志：01 装表临时用电，02 无表临时用电，03 非临时用电
     */
    private String tempUseFlag;
    /**
     * 临时用电到期日期
     */
    private LocalDate tempUseDeadline;
    /**
     * 用电检查周期
     */
    private String useCheckPeriod;
    /**
     * 上次检查日期
     */
    private LocalDate lastCheckTime;
    /**
     * 保障房标志：0-否，1-是
     */
    private String indemnificatoryHouseFlag;
    /**
     * 保障房总户数
     */
    private Integer indemnificatoryHouseNumber;
    /**
     * 保障房总面积
     */
    private String indemnificatoryHouseArea;
    /**
     * 保障房总容量
     */
    private String indemnificatoryHouseCapacity;
    /**
     * 地区特征
     */
    private String areaCharacter;
    /**
     * 数据来源:0-已归档，1-未归档
     */
    private Integer dataSource;
    /**
     * 归档时间:在业扩最后一个环节进行更新
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime archiveTime;
}