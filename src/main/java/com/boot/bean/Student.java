package com.boot.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.boot.converter.LocalDateConverter;
import com.boot.converter.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @ExcelProperty(value = "日期", converter = LocalDateConverter.class)
    private LocalDate localDate;
    @ExcelProperty(value = "时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime localDateTime;

    public Student(int id, String name, LocalDate localDate) {
        this.id = id;
        this.name = name;
        this.localDate = localDate;
    }
}
