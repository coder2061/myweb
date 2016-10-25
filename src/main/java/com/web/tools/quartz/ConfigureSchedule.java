package com.web.tools.quartz;

import java.util.Date;

import com.web.utils.DateUtils;

/** 
 * quartz作业调度配置实现
 * @author jiangyf   
 * @date 2016年8月10日 下午6:28:50 
 * @version V1.0   
 */
public class ConfigureSchedule {
	
	public void testCron(){
		System.out.println("定时任务：quartz配置实现（CronTrigger），时间：" + DateUtils.getDateStrByPattern(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
	
	public void testSimple(){
		System.out.println("定时任务：quartz配置实现（SimpleTrigger），时间：" + DateUtils.getDateStrByPattern(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}

}
