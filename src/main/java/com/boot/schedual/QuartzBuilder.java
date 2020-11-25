package com.boot.schedual;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/11/23 10:10
 * <h></h>
 */
public class QuartzBuilder {
    private static StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

    private static void addJobToScheduler(Scheduler scheduler, JobDetail jobDetail, Trigger trigger) {
        try {
            if (scheduler.checkExists(jobDetail.getKey())) {
                scheduler.deleteJob(jobDetail.getKey());
                System.out.println("我是重複的key，我在進行刪除測試");
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            System.out.println("我是重複的key，我在進行刪除測試");
        }
    }

    /**
     * 創建簡單的定時任務
     */
    public static void createSimpleJob(Class classes, JobDataMap dataMap, Date startTime, String name, String group) {
        try {
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(classes).withIdentity(name, group).usingJobData(dataMap).build();
            SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity(name, group).startAt(startTime).build();
            addJobToScheduler(scheduler, jobDetail, simpleTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            System.out.println("任務" + name + "開始");
        }
    }

    /**
     * 进行定时任务的删除
     *
     * @param name  name
     * @param group group
     */
    public static void deleteJob(String name, String group) {
        //获取唯一的任务器
        try {
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            JobKey jobKey = new JobKey(name, group);
            scheduler.deleteJob(jobKey);
            System.out.println("我被删除啦，尽管我不存在");
        } catch (SchedulerException e) {
            System.out.println("任務" + name + "開始");
        }
    }
}
