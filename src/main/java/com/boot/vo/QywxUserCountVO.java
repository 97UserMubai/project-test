package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h>企业微信用户统计数据VO对象</h>
 * @Date 2021/3/4
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QywxUserCountVO {
    private Long stat_time;
    private Integer chat_cnt;
    private Integer message_cnt;
    private Double reply_percentage;
    private Integer avg_reply_time;
    private Integer negative_feedback_cnt;
    private Integer new_apply_cnt;
    private Integer new_contact_cnt;
}
