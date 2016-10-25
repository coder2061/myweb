package com.web.tools.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

/**   
 * 监听触发规则triggers，一般是在spring启动和刷新时触发
 */
public class StatisticTriggerListener implements TriggerListener {

	@Override
	public String getName() {
		
		return null;
	}

	@Override
	public void triggerComplete(Trigger arg0, JobExecutionContext arg1,
			CompletedExecutionInstruction arg2) {
		
		
	}

	@Override
	public void triggerFired(Trigger arg0, JobExecutionContext arg1) {
		
		
	}

	@Override
	public void triggerMisfired(Trigger arg0) {
		
		
	}

	@Override
	public boolean vetoJobExecution(Trigger arg0, JobExecutionContext arg1) {
		
		return false;
	}

}
