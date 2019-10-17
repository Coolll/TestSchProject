package com.wql.utils.quartz;

import org.quartz.Job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class SQLJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job执行方法，时间：" + new Date()+"====这里可以做需要定时做的任务====");
    }

}
