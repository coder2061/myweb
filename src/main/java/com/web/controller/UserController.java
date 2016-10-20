package com.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.service.UserService;

/** 
 * Function:  
 * @author jiangyf   
 * @since 2016年8月24日 下午3:32:07 
 * @version V1.0   
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public ModelAndView sayHello(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("sayHello", userService.sayHello(request.getParameter("name")));
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
	public ModelAndView test() {
		ModelAndView model = new ModelAndView();
		model.setViewName("jsp/commonTest");
		return model;
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView check() {
		ModelAndView model = new ModelAndView();
		model.setViewName("jsp/checkCard");
		return model;
	}

}
