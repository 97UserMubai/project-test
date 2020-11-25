package com.boot.schedual;

import org.quartz.JobDataMap;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/11/23 10:24
 * <h></h>
 */
public class MyJobTest2 {
    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        date.setTime(date.getTime() + 10000);// 10s后
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("MyJobTest", "MyJobTest");
        System.out.println("start:" + LocalDateTime.now().toString());
        QuartzBuilder.createSimpleJob(PrintWordsJob.class, dataMap, date, "hahah", "taskgroup");
        QuartzBuilder.deleteJob("ceshi","taskgroup");
    }
}
