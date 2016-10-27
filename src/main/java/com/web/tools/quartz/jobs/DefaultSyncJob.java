package com.web.tools.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.web.tools.quartz.entity.SchedulerJob;

/**   
 * 定时任务运行工厂类，任务运行入口，即Job实现类(无状态的Job)
 * 给Job实现类加上注解@DisallowConcurrentExecution即可实现有状态
 * 
 * @author jiangyf   
 */
@DisallowConcurrentExecution
public class DefaultSyncJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务成功运行");
        SchedulerJob scheduleJob = (SchedulerJob)context.getScheduler();
        System.out.println("任务名称 :[" + scheduleJob.getJobName() + "]");
    }
}


