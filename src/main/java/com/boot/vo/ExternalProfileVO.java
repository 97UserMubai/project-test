package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class ExternalProfileVO {
    private String external_corp_name;
    private List<ExternalAttrVO> external_attr;
}
