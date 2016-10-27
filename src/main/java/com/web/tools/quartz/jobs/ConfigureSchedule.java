package com.web.tools.quartz.jobs;

import java.util.Date;

import com.web.utils.DateUtils;

/**
 * quartz作业调度配置实现
 * 
 * @author jiangyf
 */
public class ConfigureSchedule {
	public void test() {
		System.out.println("定时任务：jobDetail配置实现，时间："
				+ DateUtils.getDateStrByPattern(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
	}

	public void testCron() {
		System.out.println("定时任务：jobDetail配置实现（CronTrigger），时间："
				+ DateUtils.getDateStrByPattern(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
	}

	public void testSimple() {
		System.out.println("定时任务：jobDetail配置实现（SimpleTrigger），时间："
				+ DateUtils.getDateStrByPattern(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
	}

}
