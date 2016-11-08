package com.web.tools.memcached.dao;

import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.web.tools.memcached.entity.IUser;

public interface IUserDao {
	IUser saveUser(IUser user);
	
	IUser getById(@ParameterValueKeyProvider String userId);
	
	IUser updateUser(@ParameterValueKeyProvider @ParameterDataUpdateContent IUser user);
	
	IUser deleteUser(@ParameterValueKeyProvider String userId);

}
