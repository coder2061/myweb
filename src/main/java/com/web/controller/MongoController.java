package com.web.controller;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.entity.User;


@Controller
@RequestMapping("mongo")
public class MongoController {
	@Resource
	private MongoTemplate mongoTemplate;
	
	@RequestMapping("save")
	public String save() {
		User user = new User();
		user.setId(1);
		user.setName("jack");
		user.setPswd("123456");
		mongoTemplate.insert(user);
		return "index";
	}
	
	@RequestMapping("get")
	public String get() {
		return "index";
	}
	
	@RequestMapping("update")
	public String update() {
		return "index";
	}
	
	@RequestMapping("del")
	public String del() {
		return "index";
	}
}
