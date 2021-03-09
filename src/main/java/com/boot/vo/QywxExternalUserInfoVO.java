package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h>企业微信外部联系人接口返回体</h>
 * @Date 2021/2/25
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QywxExternalUserInfoVO {
    private String external_userid;
    private String name;
    private String position;
    private String avatar;
    private String corp_name;
    private String corp_full_name;
    private String type;
    private String gender;
    private String unionid;
    private String external_profile;
    private String follow_info;
    private String follow_user;
}
