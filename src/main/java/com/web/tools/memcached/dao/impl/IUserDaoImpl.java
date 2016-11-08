package com.web.tools.memcached.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;
import com.web.tools.memcached.dao.IUserDao;
import com.web.tools.memcached.entity.IUser;

/**
 * 缓存操作通常是对DAO的方法进行拦截，加入必要的通知以达到增删改查的效果
 * 切入点的声明主要通过memcached注解标签来实现
 * 注：查询方式的注解适合返回值为map、list、object，而void不允许加入缓存注解
 * 
 * @author jiangyf
 */
@Repository("iuserDao")
public class IUserDaoImpl implements IUserDao {
	private static final String NAMESPACE = "com.web.tools.memcached.dao.impl.IUserDaoImpl.";
	private Map<String, IUser> users = new HashMap<String, IUser>();

	@Override
	public IUser saveUser(IUser user) {
		return users.put(user.getUserId(), user);
	}

	/**
	 * 当执行getById查询方法时，系统首先会从缓存中获取userId对应的实体 如果实体还没有被缓存，则执行查询方法并将查询结果放入缓存中
	 */
	@Override
	@ReadThroughSingleCache(namespace = NAMESPACE + "getById", expiration = 10)
	public IUser getById(@ParameterValueKeyProvider String userId) {
		return users.get(userId);
	}

	/**
	 * 当执行updateUser方法时，系统会更新缓存中userId对应的实体 将实体内容更新成@ParameterDataUpdateContent标签所描述的实体
	 */
	@UpdateSingleCache(namespace = NAMESPACE + "updateUser", expiration = 60)
	@Override
	public IUser updateUser(
			@ParameterValueKeyProvider @ParameterDataUpdateContent IUser user) {
		return users.put(user.getUserId(), user);
	}

	/**
	 * 当执行deleteUser方法时，系统会删除缓存中userId对应的实体
	 */
	@InvalidateSingleCache(namespace = NAMESPACE + "deleteUser")
	@Override
	public IUser deleteUser(@ParameterValueKeyProvider String userId) {
		return users.remove(userId);
	}
}
