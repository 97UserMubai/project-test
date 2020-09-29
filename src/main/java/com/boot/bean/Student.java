package com.boot.bean;

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
public class Student {
    private int id;
    private String name;
    private LocalDate localDate;
    public Student(int id, String name, LocalDate localDate) {
        this.id = id;
        this.name = name;
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", localDate=" + localDate +
                '}';
    }
}
