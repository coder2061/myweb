package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.entity.User;
import com.web.model.Area;

@Controller
@RequestMapping("/data")
public class DataController {
	
	/** 
	* spring mvc实现Restful返回json格式数据
	*/
	@RequestMapping(value="/userinfo", method = RequestMethod.GET)
	@ResponseBody
	public User userinfo() {
		User user = new User();
		user.setName("jade");
		user.setPswd("123456");
		return user;
	}
	
	/**  
	* spring mvc实现Restful返回xml格式数据
	*/
	@RequestMapping(value="/areainfo", method = RequestMethod.GET)
	@ResponseBody
	public Area areainfo() {
		Area area = new Area("china", "beijing", "chaoyang", "wangjing", "100000");
		return area;
	}

}
