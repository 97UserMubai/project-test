package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2021/2/25
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QywxExternalVO {
    private QywxExternalUserInfoVO external_contact;
    private QywxExternalFollowInfoVO follow_info;
}
