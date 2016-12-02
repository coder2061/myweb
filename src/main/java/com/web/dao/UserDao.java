package com.web.dao;

import com.web.entity.User;

public interface UserDao {
	
	User selectOne(Integer id);
	
	User getUser(Integer id);

}
