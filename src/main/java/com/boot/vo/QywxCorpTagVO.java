package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h>企业标签信息请求体VO</h>
 * @Date 2021/3/1
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QywxCorpTagVO {
    private String group_id;
    private String group_name;
    private Long create_time;
    private Integer order;
    private List<QywxCorpTagChildVO> tag;
}
