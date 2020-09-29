package com.boot.function;

import com.boot.bean.Student;

import java.util.function.Consumer;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/11 16:56
 * <h></h>
 */
public class ConsumerContext {

    public void consumetPrint(Student student, Consumer<Student> consumer){
        System.out.println(student.toString());
        consumer.accept(student);
    }

}
