package com.web.tools.quartz.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

import com.web.tools.quartz.entity.SchedulerJob;
import com.web.tools.quartz.jobs.DefaultJob;
import com.web.tools.quartz.jobs.DefaultSyncJob;

/**
 * Scheduler任务管理器
 * 
 * @author jiangyf
 */
public class SchedulerJobManager {
	private static final String JOBCLASSNAME = "com.web.tools.quartz.jobs.DefaultJob";
	private static final String JOBDATAMAPKEY = "schedulerJob";

	/**
	 * 创建定时任务
	 *
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 * @param cronExpression
	 * @param isSync
	 * @param param
	 */
	public static void createScheduleJob(Scheduler scheduler, String jobName,
			String jobGroup, String cronExpression, boolean isSync, String key,
			Object param) {
		// 同步或异步
		Class<? extends Job> jobClass = (Class<? extends Job>) (isSync ? DefaultSyncJob.class
				: DefaultJob.class);
		// 构建job信息
		JobDetail jobDetail = JobBuilder.newJob(jobClass)
				.withIdentity(jobName, jobGroup).build();
		// 放入参数，运行时的方法可以获取
		jobDetail.getJobDataMap().put(key, param);
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(cronExpression);
		// 构建trigger
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder)
				.build();
		try {
			// 交由Scheduler安排触发
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			System.out.println("创建定时任务失败" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 创建定时任务
	 * 
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 * @param interval
	 * @param count
	 * @param isSync
	 * @param key
	 * @param param
	 */
	public static void createScheduleJob(Scheduler scheduler, String jobName,
			String jobGroup, int interval, int count, boolean isSync,
			String key, Object param) {
		// 同步或异步
		Class<? extends Job> jobClass = (Class<? extends Job>) (isSync ? DefaultSyncJob.class
				: DefaultJob.class);
		// 构建job信息
		JobDetail jobDetail = JobBuilder.newJob(jobClass)
				.withIdentity(jobName, jobGroup).build();
		// 放入参数，运行时的方法可以获取
		jobDetail.getJobDataMap().put(key, param);
		// 设置运行时间点
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
				.simpleSchedule().withIntervalInSeconds(interval)
				.withRepeatCount(count).repeatForever();
		// 构建trigger
		SimpleTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder)
				.build();
		try {
			// 交由Scheduler安排触发
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			System.out.println("创建定时任务失败" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 添加调度任务
	 * 
	 * @param scheduler
	 * @param schedulerJob
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 */
	public static Date addJob(Scheduler scheduler, SchedulerJob schedulerJob)
			throws SchedulerException, ClassNotFoundException {
		return addJob(scheduler, schedulerJob, JOBCLASSNAME);
	}

	/**
	 * 添加调度任务
	 * 
	 * @param scheduler
	 * @param schedulerJob
	 * @param JobClassName
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static Date addJob(Scheduler scheduler, SchedulerJob schedulerJob,
			String JobClassName) throws SchedulerException,
			ClassNotFoundException {
		TriggerKey triggerKey = TriggerKey.triggerKey(
				schedulerJob.getJobName(), schedulerJob.getJobGroup());
		// 获取trigger
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// trigger不存在，创建一个
		if (trigger == null) {
			Class<?> JobClass = Class.forName(JobClassName);
			JobDetail jobDetail = JobBuilder
					.newJob((Class<? extends Job>) JobClass)
					.withIdentity(schedulerJob.getJobName(),
							schedulerJob.getJobGroup()).build();
			jobDetail.getJobDataMap().put(JOBDATAMAPKEY, schedulerJob);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(schedulerJob.getCronExpression());
			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(schedulerJob.getJobName(),
							schedulerJob.getJobGroup())
					.withSchedule(scheduleBuilder).build();
			// 添加job至调度工厂
			return scheduler.scheduleJob(jobDetail, trigger);
		} else { // trigger已存在，更新相应的定时设置后恢复执行
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(schedulerJob.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job恢复执行
			return scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	/**
	 * 更新任务的时间表达式，更新之后，任务将立即按新的时间表达式执行
	 * 
	 * @param scheduler
	 * @param schedulerJob
	 * @throws SchedulerException
	 * @return Date
	 */
	public static Date modifyCronExpression(Scheduler scheduler,
			SchedulerJob schedulerJob) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(
				schedulerJob.getJobName(), schedulerJob.getJobGroup());
		// 获取trigger
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(schedulerJob.getCronExpression());
		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(scheduleBuilder).build();
		// 按新的trigger重新设置job执行
		return scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 更新任务的运行时间使劲设置，更新之后，任务将立即按新的运行时间执行
	 * 
	 * @param scheduler
	 * @param schedulerJob
	 * @throws SchedulerException
	 * @return Date
	 */
	public static Date modifyRepeatInfo(Scheduler scheduler,
			SchedulerJob schedulerJob) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(
				schedulerJob.getJobName(), schedulerJob.getJobGroup());
		// 获取trigger
		SimpleTrigger trigger = (SimpleTrigger) scheduler
				.getTrigger(triggerKey);
		// 设置运行时间点
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInSeconds(
						schedulerJob.getRepeatInterval().intValue())
				.withRepeatCount(schedulerJob.getRepeatCount()).repeatForever();
		// 重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(scheduleBuilder).build();

		// 按新的trigger重新设置job执行
		return scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 更新参数，实际测试中发现无法更新，所以可采用先删除任务再进行创建的方式来迂回实现参数的更新
	 * 
	 * @param jobDetail
	 * @param schedulerJob
	 * @return JobDetail
	 * @throws SchedulerException
	 */
	@SuppressWarnings("unused")
	private static JobDetail modifyJobDataMap(Scheduler scheduler,
			SchedulerJob schedulerJob) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(schedulerJob.getJobName(),
				schedulerJob.getJobGroup());
		JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		jobDataMap.put(JOBDATAMAPKEY, schedulerJob);
		jobDetail.getJobBuilder().usingJobData(jobDataMap);
		return jobDetail;
	}

	/**
	 * 计划中的任务：指那些已经添加到quartz调度器的任务，
	 * 因为quartz并没有直接提供这样的查询接口，所以我们需要结合JobKey和Trigger来实现
	 * 
	 * @param scheduler
	 * @return List<SchedulerJob>
	 * @throws SchedulerException
	 */
	public static List<SchedulerJob> getPlannedSchedulerJobs(Scheduler scheduler)
			throws SchedulerException {
		List<SchedulerJob> jobList = new ArrayList<SchedulerJob>();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler
					.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
				SchedulerJob job = setSchedulerJob(scheduler, jobKey,
						jobDetail, trigger);
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 运行中的任务
	 * 
	 * @param scheduler
	 * @return List<SchedulerJob>
	 * @throws SchedulerException
	 */
	public static List<SchedulerJob> getRunningSchedulerJobs(Scheduler scheduler)
			throws SchedulerException {
		List<JobExecutionContext> executingJobs = scheduler
				.getCurrentlyExecutingJobs();
		List<SchedulerJob> jobList = new ArrayList<SchedulerJob>(
				executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			SchedulerJob job = setSchedulerJob(scheduler, jobKey, jobDetail,
					trigger);
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 获取SchedulerJob对象
	 * 
	 * @param jobKey
	 * @param jobDetail
	 * @param trigger
	 * @return SchedulerJob
	 * @throws SchedulerException
	 */
	private static SchedulerJob setSchedulerJob(Scheduler scheduler,
			JobKey jobKey, JobDetail jobDetail, Trigger trigger)
			throws SchedulerException {
		SchedulerJob job = new SchedulerJob();

		job.setJobName(jobKey.getName());
		job.setJobGroup(jobKey.getGroup());

		TriggerKey triggerKey = trigger.getKey();
		job.setTriggerName(triggerKey.getName());
		job.setTriggerGroup(triggerKey.getGroup());

		job.setJobClassName(jobDetail.getJobClass().getName());
		job.setDescription(jobDetail.getDescription());

		if (trigger instanceof CronTrigger) {
			CronTrigger cronTrigger = (CronTrigger) trigger;
			job.setCronExpression(cronTrigger.getCronExpression());
		} else if (trigger instanceof SimpleTrigger) {
			SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;
			job.setRepeatCount(simpleTrigger.getRepeatCount());
			job.setRepeatInterval(simpleTrigger.getRepeatInterval());
		}
		job.setPriority(trigger.getPriority());
		job.setNextFireTime(trigger.getNextFireTime().getTime());
		job.setPrevFireTime(trigger.getPreviousFireTime().getTime());

		Trigger.TriggerState triggerState = scheduler
				.getTriggerState(triggerKey);
		job.setJobStatus(triggerState.name());

		job.setStartTime(trigger.getStartTime() != null ? trigger
				.getStartTime().getTime() : null);
		job.setEndTime(trigger.getEndTime() != null ? trigger.getEndTime()
				.getTime() : null);

		return job;
	}

}
