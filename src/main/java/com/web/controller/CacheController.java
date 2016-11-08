package com.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.tools.memcached.dao.IUserDao;
import com.web.tools.memcached.entity.IUser;

@Controller
@RequestMapping("cache")
public class CacheController {
	@Resource
	private IUserDao iuserDao;
	
	@RequestMapping("save")
	public String save() {
		IUser user = new IUser();
		user.setUserId("1");
		user.setUsername("jack");
		user.setPassword("123456");
		this.iuserDao.saveUser(user);
		return "index";
	}
	
	@RequestMapping("get")
	public String get() {
        System.out.println(this.iuserDao.getById("1").getUsername());  
		return "index";
	}
	
	@RequestMapping("update")
	public String update() {
		IUser user = this.iuserDao.getById("1");
		user.setUsername("jade");
		System.out.println(this.iuserDao.updateUser(user).getUsername());  
		return "index";
	}
	
	@RequestMapping("del")
	public String del() {
		System.out.println(this.iuserDao.deleteUser("1").getUsername());  
		return "index";
	}
}
