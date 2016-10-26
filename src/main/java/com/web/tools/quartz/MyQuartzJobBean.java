package com.web.tools.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.web.service.SchedulerService;

/**
 * spring定义quartz job需要继承类QuartzJobBean
 * 
 * @author jiangyf
 */
public class MyQuartzJobBean extends QuartzJobBean {
	private static Logger log = LoggerFactory.getLogger(MyQuartzJobBean.class);
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
			log.info(jobService.sayHello("jade"));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}