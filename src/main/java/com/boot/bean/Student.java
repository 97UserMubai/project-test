package com.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/4 10:40
 * <h></h>
 * <p>
 *
 * </p>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    private int id;
    private String name;
    private LocalDate localDate;
}
