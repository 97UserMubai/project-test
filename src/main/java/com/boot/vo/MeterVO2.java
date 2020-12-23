package com.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangbaitao
 * @version 1.0.0
 * <h></h>
 * @Date 2020/12/23
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeterVO2 {
    /**
     * 设备类型代码（单相感应式长寿命电能表
     */
    private String type;
    private MeterVoOne meterVoOne;
    private MeterVoTwo meterVoTwo;
}
