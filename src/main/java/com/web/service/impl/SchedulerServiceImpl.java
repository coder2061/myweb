package com.web.service.impl;

import com.web.service.SchedulerService;

public class SchedulerServiceImpl implements SchedulerService {

	@Override
	public String sayHello(String name) {
		System.out.println("Hello, " + name);
		return "Hello, " + name;
	}

}
