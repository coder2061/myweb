package com.web.service;

import com.web.entity.User;

public interface UserService {
	String sayHello(String name);
	
	User getUser(int id);

}
