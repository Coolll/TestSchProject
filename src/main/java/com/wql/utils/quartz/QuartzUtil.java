package com.wql.utils.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzUtil {
    private final static String JOB_GROUP_NAME = "QUARTZ_JOBGROUP_NAME";//任务组
    private final static String TRIGGER_GROUP_NAME = "QUARTZ_TRIGGERGROUP_NAME";//触发器组



    public static void addJob(String jobName, String triggerName, Class<? extends Job> jobClass){
        try {
            //创建一个SchedulerFactory工厂实例
            SchedulerFactory factory = new StdSchedulerFactory();
            //通过scheduleFactory构建Scheduler对象
            Scheduler scheduler = factory.getScheduler();
            System.out.println("====init quartz====");

            //用于描叙job实现类以及其他的一些静态信息，构建一个作业实例
            JobDetail detail = JobBuilder.newJob(jobClass).withIdentity(jobName,JOB_GROUP_NAME).build();
            //构建一个触发器，并规定触发的规则
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerName,TRIGGER_GROUP_NAME)
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            //向schedule中添加job任务和trigger触发器
            scheduler.scheduleJob(detail,trigger);
            //启动schedule
            scheduler.start();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
