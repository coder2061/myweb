package com.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.demo.file.Common;
import com.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public ModelAndView sayHello(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/index");
		String name = Common.getInstance().getUsername();
//		String name = userService.getUser(1).getName();
//		String name = userService.sayHello(request.getParameter("name"));
		log.info("----" + name + "----");
		model.addObject("sayHello", name);
		return model;
	}
	
	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("name", name);
		return model;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "jsp/commonTest";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String check() {
		return "jsp/checkCard";
	}
	
	@RequestMapping(value = "/socket/{example}", method = RequestMethod.GET)
	public String socket(@PathVariable String example) {
		return "websocket/" + example;
	}

}
