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
public class IecMsg {
    private String elecUserCode;
    private String elecUserName;
}
