package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2021/2/26
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebVO {
    private String url;
    private String title;
}
