package com.web.service.impl;

import org.springframework.stereotype.Service;

import com.web.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public String sayHello(String name) {
		return "hello, " + name;
	}

}
