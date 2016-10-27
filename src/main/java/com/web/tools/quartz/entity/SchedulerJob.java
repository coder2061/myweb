package com.web.tools.quartz.entity;

import java.io.Serializable;
import java.util.Date;

public class SchedulerJob implements Serializable {
	private static final long serialVersionUID = -5984887793170303453L;

	private String jobId;
	
	private String jobName;

    private String jobGroup;

    private String triggerName;

    private String triggerGroup;
    
    private String jobClassName;

    private String cronExpression;
    
    private Integer repeatCount;
    
    private Long repeatInterval;
    
    private Integer priority;

    private Long nextFireTime;

    private Long prevFireTime;

    private String triggerState;

    private String triggerType;

    private Long startTime;

    private Long endTime;
    
    private String description;
    
    private String jobStatus;

    private Date createTime;

    private Date updateTime;

    public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Long getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(Long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Long nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public Long getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(Long prevFireTime) {
        this.prevFireTime = prevFireTime;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}