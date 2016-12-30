package com.web.controller;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.entity.User;
import com.web.tools.mongodb.dao.MongoDao;

@Controller
@RequestMapping("mongo")
public class MongoController {
	@Resource
	private MongoDao<User> mongoDao;

	@RequestMapping("save")
	public String save() {
		User user = new User();
		user.setId("1");
		user.setName("jack");
		user.setPswd("123456");
		mongoDao.insert(user);
		return "index";
	}

	@RequestMapping("get")
	public ModelAndView get() {
		User user = mongoDao.findById("1", User.class);
		System.out.println(user.getName());
		ModelAndView model = new ModelAndView();
		model.setViewName("jsp/index");
		model.addObject("name", user.getName());
		return model;
	}

	@RequestMapping("update")
	public String update() {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is("1"));
		Update update = new Update();
		update.set("name", "jade");
		update.set("pswd", "654321");
		mongoDao.update(query, update, User.class);
		return "index";
	}

	@RequestMapping("del")
	public String del() {
		mongoDao.deleteById("1", User.class);
		return "index";
	}
}
