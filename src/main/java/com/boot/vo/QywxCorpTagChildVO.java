package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h>企业标签信息子对象VO</h>
 * @Date 2021/3/1
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QywxCorpTagChildVO {
    private String id;
    private String name;
    private Long create_time;
    private Integer order;
}
