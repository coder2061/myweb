package com.web.tools.memcached.entity;

import java.io.Serializable;

import com.google.code.ssm.api.CacheKeyMethod;

/**
 * 实体的定义
 * memcached相当于一个功能强大的Map，通过Key/Value的形式来缓存POJO实体，
 * 在定义实体的时候，可通过@CacheKeyMethod标签来为实体指定Key值，
 * 同时实体及实体的每个成员变量必须是可序列化的，否则不能存入memcached，
 * 可通过实现Serializable接口或Externalizable接口来为实体序列化。
 * 
 * @author jiangyf
 */
public class IUser implements Serializable {
	private static final long serialVersionUID = 7517080513591583073L;

	private String userId;

	private String username;

	private String password;

	@CacheKeyMethod
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
