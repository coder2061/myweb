package com.web.controller;

import java.util.List;

import javax.annotation.Resource;

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
	@Resource
	private Scheduler scheduler;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test() {
		try {
			for (int i = 0; i < 5; i++) {
				SchedulerJob job = new SchedulerJob();
				job.setJobId("10002" + i);
				job.setJobName("data_import" + i);
				job.setJobGroup("dataWork2");
				job.setJobStatus("1");
				job.setCronExpression("0/5 * * * * ?");
				job.setDescription("数据导入任务");
				SchedulerJobManager.addJob(scheduler, job);
			}
			
			scheduler.resumeAll();
			
			List<String> names = scheduler.getJobGroupNames();
			for (String name : names) {
				System.out.println("----" + name + "----");
			}
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("jsp/commonTest");
		return model;
	}

}
