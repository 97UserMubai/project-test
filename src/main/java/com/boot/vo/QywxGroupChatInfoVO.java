package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h>企业微信群聊详情VO对象</h>
 * @Date 2021/3/1
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QywxGroupChatInfoVO {
    private String chat_id;
    private String name;
    private String owner;
    private LocalDateTime create_time;
    private String notice;
    private String member_list;
}
