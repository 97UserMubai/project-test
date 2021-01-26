package com.boot.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2021/1/26
 **/
@Builder
@Data
public class IecTranMsg {
    private String sign;
    private Integer days;
}
