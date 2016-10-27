package com.web.tools.quartz.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.web.utils.DateUtils;

/**
 * quartz作业调度接口实现
 * 
 * @author jiangyf
 */
public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("定时任务：Job接口实现，时间："
				+ DateUtils.getDateStrByPattern(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
	}

}
