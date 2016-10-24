package com.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.dao.UserDao;
import com.web.entity.User;
import com.web.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public String sayHello(String name) {
		return "hello, " + name;
	}

	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}

}
