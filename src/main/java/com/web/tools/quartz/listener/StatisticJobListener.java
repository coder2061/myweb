package com.web.tools.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**   
 * JOB监听（监听JOB执行前和执行后）
 */
public class StatisticJobListener implements JobListener {

	@Override
	public String getName() {
		
		return null;
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext arg0) {
		
		
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext arg0) {
		
		
	}

	@Override
	public void jobWasExecuted(JobExecutionContext arg0,
			JobExecutionException arg1) {
		
		
	}

}
