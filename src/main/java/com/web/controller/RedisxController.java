package com.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.skynethome.redisx.spring.RedisXMasterSlave;

import com.web.entity.User;

@Controller
@RequestMapping("redisx")
public class RedisxController {
//	@Resource
	private RedisXMasterSlave redisXMsterSlave;
	
	@RequestMapping("save")
	public String save() {
        User user = new User();
        user.setId("1");
        user.setName("jade");
        user.setPswd("1234");
        redisXMsterSlave.setObject("1", user);
		return "index";
	}
	
	@RequestMapping("get")
	public String get() {
		User user = redisXMsterSlave.getObject("1", User.class);
        System.out.println(user.getName());  
		return "index";
	}
	
	@RequestMapping("update")
	public String update() {
		User user = redisXMsterSlave.getObject("1", User.class);
		user.setName("jack");
		redisXMsterSlave.setObject("1", user);
		return "index";
	}
	
	@RequestMapping("del")
	public String del() {
		long count = redisXMsterSlave.del("1");
		System.out.println(count);
		return "index";
	}
}
