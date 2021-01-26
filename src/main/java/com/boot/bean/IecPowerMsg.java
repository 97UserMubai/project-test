package com.boot.bean;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2021/1/26
 **/
@Builder
@Data
public class IecPowerMsg {
    private String calcCode;
    private BigDecimal totalPower;
}
