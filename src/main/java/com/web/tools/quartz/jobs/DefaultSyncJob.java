package com.web.tools.quartz.jobs;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.web.utils.DateUtils;

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
        JobDetail jobDetail = context.getJobDetail();
    	JobKey jobKey = jobDetail.getKey();
    	System.out.println("有状态同步定时任务，时间："
    			+ DateUtils.getDateStrByPattern(new Date(), "yyyy-MM-dd HH:mm:ss") 
    			+ "任务名称 :[" + jobKey.getName() + "]，任务分组:[" + jobKey.getGroup() + "]");
    }
}


