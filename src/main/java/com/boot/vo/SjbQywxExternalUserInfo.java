package com.boot.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据宝企微外部联系人信息,在同步关系中
 *
 * @author wangbaitao
 * @Date 2021-02-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SjbQywxExternalUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 外部联系人的userid
     */
    private String externalUserid;
    /**
     * 外部联系人的名称
     */
    private String name;
    /**
     * 外部联系人的类型，1表示该外部联系人是微信用户，2表示该外部联系人是企业微信用户
     */
    private String type;
    /**
     * 外部联系人性别 0-未知 1-男性 2-女性
     */
    private String gender;
    /**
     * 外部联系人在微信开放平台的唯一身份标识（微信unionid），
     * 通过此字段企业可将外部联系人与公众号/小程序用户关联起来。
     * 仅当联系人类型是微信用户，且企业或第三方服务商绑定了微信开发者ID有此字段
     */
    private String unionid;
    /**
     * 外部联系人所在企业的简称，仅当联系人类型是企业微信用户时有此字段
     */
    private String corpName;
    /**
     * 外部联系人所在企业的主体名称，仅当联系人类型是企业微信用户时有此字段
     */
    private String corpFullName;
    /**
     * 后端根据follow_user进行筛选后得到的最早的添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;
    /**
     * 后端根据follow_user进行筛选后得到的最早的添加人
     */
    private String addUser;
    /**
     * 同步时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime syncTime;
    /**
     * 外部联系人头像，第三方不可获取
     */
    private String avatar;
    /**
     * 外部联系人的自定义展示信息，样例：{"external_attr":[{"type":0,"name":"文本名称", "text":{"value":"文本"}}]}
     */
    private String externalProfile;
    /**
     * 后端需要根据该字段进行vo对象的转换
     */
    private String followUser;
    /**
     * 企业成员Id
     */
    private String userid;
    /**
     * 流失状态：0-正常，1-流失。每天定时任务会进行数据同步，如果客户不在此次同步的数据中则表示客户流失
     */
    private Boolean status;
    /**
     * 批量接口临时字段
     */
    @TableField(exist = false)
    private String follow_info;

    public static final String ID = "id";
    public static final String EXTERNAL_USER_ID = "external_userid";
    public static final String USER_ID = "userid";
    public static final String NAME = "name";
    public static final String AVATAR = "avatar";
    public static final String TYPE = "type";
    public static final String GENDER = "gender";
    public static final String UNION_ID = "unionid";
    public static final String CORP_NAME = "corp_name";
    public static final String CORP_FULL_NAME = "corp_full_name";
    public static final String EXTERNAL_PROFILE = "external_profile";
    public static final String FOLLOW_USER = "follow_user";
    public static final String ADD_TIME = "add_time";
    public static final String ADD_USER = "add_user";
    public static final String SYNC_TIME = "sync_time";
    public static final String STATUS = "status";
}