package com.boot.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/10/19 14:37
 * <h></h>
 */
@Data
@Builder
public class Student2 {
    private int id;
    private String name;
    private List<String> tests;

}
