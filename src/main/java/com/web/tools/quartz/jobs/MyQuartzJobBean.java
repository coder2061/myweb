package com.web.tools.quartz.jobs;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.web.service.SchedulerService;
import com.web.utils.DateUtils;

/**
 * spring定义quartz job需要继承类QuartzJobBean
 * 
 * @author jiangyf
 */
public class MyQuartzJobBean extends QuartzJobBean {
	private SchedulerService jobService;

	// 调度工厂实例化后，经过timeout时间开始执行调度
	private int timeout;
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getTimeout() {
		return timeout;
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		Scheduler scheduler = context.getScheduler();
		try {
			// 获取Scheduler上下文
			SchedulerContext schCtx = scheduler.getContext();
			// 获取Spring上下文
			ApplicationContext appCtx = (ApplicationContext) schCtx
					.get("applicationContextSchedulerContextKey");
			// 获取JobExecutionContext中的service对象
			jobService = (SchedulerService) appCtx.getBean("jobService");
			System.out.println(jobService.sayHello("jade"));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		System.out.println("定时任务：QuartzJobBean类继承，时间："
				+ DateUtils.getDateStrByPattern(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
	}
}