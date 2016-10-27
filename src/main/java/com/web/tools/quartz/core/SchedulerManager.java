package com.web.tools.quartz.core;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * Scheduler管理器
 * 
 * @author jiangyf
 */
public class SchedulerManager {
	private static Scheduler scheduler;
	private static JobDetail jobDetail;
	private static Trigger trigger;

	/**
	 * 创建一个调度对象
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	private static Scheduler getScheduler() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			// scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return scheduler;
	}

	public static Scheduler getInstanceScheduler() {
		if (scheduler == null) {
			return getScheduler();
		}
		return scheduler;
	}

	/**
	 * 启动一个调度对象
	 * 
	 * @throws SchedulerException
	 */
	public static void start() throws SchedulerException {
		scheduler.start();
	}

	/**
	 * 检查调度是否启动
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean isStarted() throws SchedulerException {
		return scheduler.isStarted();
	}

	/**
	 * 关闭调度信息
	 * 
	 * @throws SchedulerException
	 */
	public static void shutdown() throws SchedulerException {
		scheduler.shutdown();
	}

	/**
	 * 添加调度的job信息
	 * 
	 * @param jobdetail
	 * @param trigger
	 * @return
	 * @throws SchedulerException
	 */
	public static Date scheduleJob(JobDetail jobdetail, Trigger trigger)
			throws SchedulerException {
		return scheduler.scheduleJob(jobdetail, trigger);
	}

	/**
	 * 添加相关的触发器
	 * 
	 * @param trigger
	 * @return
	 * @throws SchedulerException
	 */
	public static Date scheduleJob(Trigger trigger) throws SchedulerException {
		return scheduler.scheduleJob(trigger);
	}

	/**
	 * 添加多个job任务
	 * 
	 * @param triggersAndJobs
	 * @param replace
	 * @throws SchedulerException
	 */
	public static void scheduleJobs(
			Map<JobDetail, Set<? extends Trigger>> triggersAndJobs,
			boolean replace) throws SchedulerException {
		scheduler.scheduleJobs(triggersAndJobs, replace);
	}

	/**
	 * 停止调度Job任务
	 * 
	 * @param triggerkey
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean unscheduleJob(TriggerKey triggerkey)
			throws SchedulerException {
		return scheduler.unscheduleJob(triggerkey);
	}

	/**
	 * 停止调度多个触发器相关的job
	 * 
	 * @param list
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean unscheduleJobs(List<TriggerKey> triggerKeylist)
			throws SchedulerException {
		return scheduler.unscheduleJobs(triggerKeylist);
	}

	/**
	 * 重新恢复触发器相关的job任务
	 * 
	 * @param triggerkey
	 * @param trigger
	 * @return
	 * @throws SchedulerException
	 */
	public static Date rescheduleJob(TriggerKey triggerkey, Trigger trigger)
			throws SchedulerException {
		return scheduler.rescheduleJob(triggerkey, trigger);
	}

	/**
	 * 添加相关的job任务
	 * 
	 * @param jobdetail
	 * @param flag
	 * @throws SchedulerException
	 */
	public static void addJob(JobDetail jobdetail, boolean flag)
			throws SchedulerException {
		scheduler.addJob(jobdetail, flag);
	}

	/**
	 * 获取JobKey
	 * 
	 * @param jobName
	 * @return JobKey
	 */
	public static JobKey getJobKey(String jobName) {
		return JobKey.jobKey(jobName);
	}

	/**
	 * 获取JobKey
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return JobKey
	 */
	public static JobKey getJobKey(String jobName, String jobGroup) {
		return JobKey.jobKey(jobName, jobGroup);
	}

	/**
	 * 删除相关的job任务
	 * 
	 * @param jobkey
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean deleteJob(JobKey jobkey) throws SchedulerException {
		return scheduler.deleteJob(jobkey);
	}

	/**
	 * 删除相关的多个job任务
	 * 
	 * @param jobKeys
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean deleteJobs(List<JobKey> jobKeys)
			throws SchedulerException {
		return scheduler.deleteJobs(jobKeys);
	}

	/**
	 * 为job任务设置触发器，立即运行任务
	 * 
	 * @param jobkey
	 * @throws SchedulerException
	 */
	public static void triggerJob(JobKey jobkey) throws SchedulerException {
		scheduler.triggerJob(jobkey);
	}

	/**
	 * 为job任务设置触发器
	 * 
	 * @param jobkey
	 * @param jobdatamap
	 * @throws SchedulerException
	 */
	public static void triggerJob(JobKey jobkey, JobDataMap jobdatamap)
			throws SchedulerException {
		scheduler.triggerJob(jobkey, jobdatamap);
	}

	/**
	 * 停止一个job任务
	 * 
	 * @param jobkey
	 * @throws SchedulerException
	 */
	public static void pauseJob(JobKey jobkey) throws SchedulerException {
		scheduler.pauseJob(jobkey);
	}

	/**
	 * 停止多个job任务
	 * 
	 * @param groupmatcher
	 * @throws SchedulerException
	 */
	public static void pauseJobs(GroupMatcher<JobKey> groupmatcher)
			throws SchedulerException {
		scheduler.pauseJobs(groupmatcher);
	}

	/**
	 * 停止使用相关的触发器
	 * 
	 * @param triggerkey
	 * @throws SchedulerException
	 */
	public static void pauseTrigger(TriggerKey triggerkey)
			throws SchedulerException {
		scheduler.pauseTrigger(triggerkey);
	}

	/**
	 * 停止使用多个相关的触发器
	 * 
	 * @param groupmatcher
	 * @throws SchedulerException
	 */
	public static void pauseTriggers(GroupMatcher<TriggerKey> groupmatcher)
			throws SchedulerException {
		scheduler.pauseTriggers(groupmatcher);
	}

	/**
	 * 恢复相关的job任务
	 * 
	 * @param jobkey
	 * @throws SchedulerException
	 */
	public static void resumeJob(JobKey jobkey) throws SchedulerException {
		scheduler.pauseJob(jobkey);
	}

	/**
	 * 恢复多个相关的job任务
	 * 
	 * @param matcher
	 * @throws SchedulerException
	 */
	public static void resumeJobs(GroupMatcher<JobKey> matcher)
			throws SchedulerException {
		scheduler.resumeJobs(matcher);
	}

	/**
	 * 恢复相关的job触发器
	 * 
	 * @param triggerkey
	 * @throws SchedulerException
	 */
	public static void resumeTrigger(TriggerKey triggerkey)
			throws SchedulerException {
		scheduler.resumeTrigger(triggerkey);
	}

	/**
	 * 恢复多个相关的job触发器
	 * 
	 * @param groupmatcher
	 * @throws SchedulerException
	 */
	public static void resumeTriggers(GroupMatcher<TriggerKey> groupmatcher)
			throws SchedulerException {
		scheduler.resumeTriggers(groupmatcher);
	}

	/**
	 * 暂停调度中所有的job任务
	 * 
	 * @throws SchedulerException
	 */
	public static void pauseAll() throws SchedulerException {
		scheduler.pauseAll();
	}

	/**
	 * 恢复调度中所有的job的任务
	 * 
	 * @throws SchedulerException
	 */
	public static void resumeAll() throws SchedulerException {
		scheduler.resumeAll();
	}

	public static JobDetail createJobDetail(String jobName, String jobGroup,
			Class<? extends Job> jobClass) {
		return JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup)
				.build();
	}

	@SuppressWarnings("deprecation")
	public static JobDetail createJobDetail(String jobName, String jobGroup,
			Class<? extends Job> jobClass, boolean durability,
			boolean shouldRecover, String description) {
		JobDetailImpl jobDetail = new JobDetailImpl(jobName, jobGroup, jobClass);
		jobDetail.setDurability(durability);
		jobDetail.setRequestsRecovery(shouldRecover);
		jobDetail.setDescription(description);
		// jobDetail.setKey(jobKey);
		// jobDetail.setJobDataMap(jobDataMap);
		return jobDetail;
	}

	public static CronScheduleBuilder createScheduleBuilder(String cron) {
		CronScheduleBuilder schedBuilder = CronScheduleBuilder
				.cronSchedule(cron);
		return schedBuilder;
	}

	public static SimpleScheduleBuilder createScheduleBuilder(int interval) {
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder
				.simpleSchedule().withIntervalInSeconds(interval)
				.repeatForever();
		return schedBuilder;
	}

	public static Trigger createTrigger(String triggerName,
			String triggerGroup, CronScheduleBuilder schedBuilder) {
		return TriggerBuilder.newTrigger()
				.withIdentity(triggerName, triggerGroup).startNow()
				.withSchedule(schedBuilder).build();
	}

	public static Trigger createTrigger(String triggerName,
			String triggerGroup, SimpleScheduleBuilder schedBuilder) {
		return TriggerBuilder.newTrigger()
				.withIdentity(triggerName, triggerGroup).startNow()
				.withSchedule(schedBuilder).build();
	}

	public static Scheduler createScheduler(String jobName, String jobGroup,
			String cron, String triggerName, String triggerGroup,
			Class<? extends Job> jobClass) throws SchedulerException {
		// 启动Scheduler实例
		start();
		// 具体任务
		jobDetail = createJobDetail(jobName, jobGroup, jobClass);
		// 触发时间点
		CronScheduleBuilder schedBuilder = createScheduleBuilder(cron);
		trigger = createTrigger(triggerName, triggerGroup, schedBuilder);
		// 交由Scheduler安排触发
		scheduler.scheduleJob(jobDetail, trigger);
		return scheduler;
	}

	public static Scheduler createScheduler(String jobName, String jobGroup,
			int interval, String triggerName, String triggerGroup,
			Class<? extends Job> jobClass) throws SchedulerException {
		// 启动Scheduler实例
		start();
		// 具体任务
		jobDetail = createJobDetail(jobName, jobGroup, jobClass);
		// 触发时间点
		SimpleScheduleBuilder schedBuilder = createScheduleBuilder(interval);
		trigger = createTrigger(triggerName, triggerGroup, schedBuilder);
		// 交由Scheduler安排触发
		scheduler.scheduleJob(jobDetail, trigger);
		return scheduler;
	}

}