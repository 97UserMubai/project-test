package com.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2021/1/26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IecCalcMsg {
    private String calcCode;
    private String type;
    private String lastNum;
    private String currentNum;
}
