package com.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.web.entity.User;
import com.web.model.Area;
import com.web.service.UserService;

@Controller
@RequestMapping("/data")
public class DataController {
	@Resource
	private UserService userService;
	
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
	
	/**
	* 跨域问题：只要协议、域名、端口有任何一个不同，都被当作是不同的域，只要是在不同域中是无法进行通信的。
	* 使用JSONP解决跨域问题，缺点：只支持GET方式的HTTP请求。
	* request：http://localhost:8080/myweb/data/jsonpInfo?callback=getUser&userId=1
	* response：getUser({"id":1,"name":"jade","pswd":"123"})
	*/
	@RequestMapping(value = "/jsonpInfo", method = { RequestMethod.GET })
	@ResponseBody
	public Object jsonpInfo(String callback,Integer userId) {
	    User user = userService.getUser(userId);
	    JSONPObject jsonpObject = new JSONPObject(callback,user) ;
	    return jsonpObject ;
	}

}
