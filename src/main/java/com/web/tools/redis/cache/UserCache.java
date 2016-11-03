package com.web.tools.redis.cache;

import java.util.List;

import com.web.entity.User;

public interface UserCache {
	/**  
	* 新增
	* 
	* @param user
	*/
	boolean add(User user);

	/**
	 * 批量新增 使用pipeline方式
	 * 
	 * @param list
	 */
	boolean add(List<User> list);

	/**
	 * 删除
	 * 
	 * @param key
	 */
	void delete(String key);

	/**
	 * 批量删除
	 * 
	 * @param keys
	 */
	void delete(List<String> keys);

	/**
	 * 修改
	 * 
	 * @param user
	 */
	boolean update(User user);

	/**
	 * 通过key获取
	 * 
	 * @param keyId
	 */
	User get(String key);

}
