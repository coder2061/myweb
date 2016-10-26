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

@Controller
@RequestMapping("/qrtz")
public class SchedulerController {
	private static Logger log = LoggerFactory.getLogger(SchedulerController.class);
	@Resource
	private Scheduler scheduler;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test() {
		try {
			List<String> names = scheduler.getJobGroupNames();
			for (String name : names) {
				log.info("----" + name + "----");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("jsp/commonTest");
		return model;
	}

}
