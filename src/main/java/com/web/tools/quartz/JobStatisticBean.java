package com.web.tools.quartz;

import java.io.Serializable;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;

public class JobStatisticBean implements Serializable {
	private static final long serialVersionUID = 7931411116577295290L;

	private String jobName;
	private String className;
	private Date startTime;
	private Date endTime;
	private Long takesTime;
	private JobDetail jobDetail;
	private Trigger trigger;
	private JobExecutionContext jobExecutionContext;
	private Job jobIntance;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getTakesTime() {
		return takesTime;
	}

	public void setTakesTime(Long takesTime) {
		this.takesTime = takesTime;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public JobExecutionContext getJobExecutionContext() {
		return jobExecutionContext;
	}

	public void setJobExecutionContext(JobExecutionContext jobExecutionContext) {
		this.jobExecutionContext = jobExecutionContext;
	}

	public Job getJobIntance() {
		return jobIntance;
	}

	public void setJobIntance(Job jobIntance) {
		this.jobIntance = jobIntance;
	}
}
