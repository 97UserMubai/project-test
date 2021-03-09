package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h>外部联系人的企业成员跟踪信息，需要跟ref表同步，并且没有tag_name和tag_group字段</h>
 * @Date 2021/2/25
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QywxExternalFollowInfoVO {
    private String userid;
    private String remark;
    private String description;
    private Long createtime;
    private String tag_id;
    private String remark_corp_name;
    private String remark_mobiles;
    private String oper_userid;
    private String add_way;
    private String state;
    private String tags;
}
