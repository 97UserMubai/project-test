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
public class ExternalAttrVO {
    private String type;
    private String name;
    private TextVO text;
    private WebVO web;
    private MiniprogramVO miniprogram;
}
