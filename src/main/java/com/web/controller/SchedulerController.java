package com.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.tools.quartz.core.SchedulerJobManager;
import com.web.tools.quartz.entity.SchedulerJob;

@Controller
@RequestMapping("/qrtz")
public class SchedulerController {
	private static Logger log = LoggerFactory.getLogger(SchedulerController.class);
	 /*
	  * spring注入schedulerFactoryBean,实际返回的是Scheduler对象，
	  * 可以直接声明Scheduler对象，不必再调用schedulerFactory的getScheduler()；
	  * 这是getBean("bean")和getBean("&bean")的区别
	  */
	@Resource
	private Scheduler scheduler;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add() {
		try {
			for (int i = 0; i < 5; i++) {
				SchedulerJob job = new SchedulerJob();
				job.setJobId("10002" + i);
				job.setJobName("data_import" + i);
				job.setJobGroup("dataWork2");
				job.setJobStatus("1");
				job.setCronExpression("0/10 * * * * ?");
				job.setDescription("数据导入任务");
				SchedulerJobManager.addJob(scheduler, job);
			}
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public ModelAndView jobs() {
		ModelAndView model = new ModelAndView();
		try {
			List<SchedulerJob> jobs = SchedulerJobManager.getPlannedSchedulerJobs(scheduler);
			List<SchedulerJob> jobs2 = SchedulerJobManager.getRunningSchedulerJobs(scheduler);
			if (jobs!=null) {
				System.out.println("----jobs----" + jobs.size());
			}
			if (jobs2!=null) {
				System.out.println("----jobs2----" + jobs2.size());
			}
			
			model.addObject("jobs", jobs);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		model.setViewName("jsp/scheduler");
		return model;
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void schedulerInfo() {
		try {
			System.out.println("----id----" + scheduler.getSchedulerInstanceId());
			System.out.println("----name----" + scheduler.getSchedulerName());
			System.out.println("----isStarted----" + scheduler.isStarted());
			System.out.println("----isShutdown----" + scheduler.isShutdown());
			System.out.println("----isInStandbyMode----" + scheduler.isInStandbyMode());
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			List<String> calendarNames = scheduler.getCalendarNames();
			for (String name : calendarNames) {
				System.out.println("----calendarNames----" + name);
			}
			
			List<String> jobGroupNames = scheduler.getJobGroupNames();
			for (String name : jobGroupNames) {
				System.out.println("----jobGroupNames----" + name);
			}
			
			List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
			for (String name : triggerGroupNames) {
				System.out.println("----triggerGroupNames----" + name);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/pause", method = RequestMethod.GET)
	public void pause(HttpServletRequest request) {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.start();
			String jobName = request.getParameter("jobName");
			String jobGroup = request.getParameter("jobGroup");
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/resume", method = RequestMethod.GET)
	public void resume(HttpServletRequest request) {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.start();
			String jobName = request.getParameter("jobName");
			String jobGroup = request.getParameter("jobGroup");
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request) {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.start();
			String jobName = request.getParameter("jobName");
			String jobGroup = request.getParameter("jobGroup");
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public void start() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/startDelayed", method = RequestMethod.GET)
	public void startDelayed() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.startDelayed(10);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/standby", method = RequestMethod.GET)
	public void standby() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.standby();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/pauseAll", method = RequestMethod.GET)
	public void pauseAll() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.pauseAll();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/resumeAll", method = RequestMethod.GET)
	public void resumeAll() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.resumeAll();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public void clear() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			scheduler.clear();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/shutdown", method = RequestMethod.GET)
	public void shutdown() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
