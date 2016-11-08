package com.web.tools.memcached.whalin.dao;

import com.web.tools.memcached.whalin.model.CacheItem;

/**   
 * Memcached 常用功能接口定义，用于业务层直接使用，屏蔽各种客户端实现的API差异，实现解耦客户端与业务系统的目的
 * 无过期时间和flags支持，无append,prepend,replace,incr,decr等操作
 * 
 * @author jiangyf   
 */
public interface WhalinDao {
	String get(String key);

	CacheItem gets(String key);

	boolean add(String key, String value);

	boolean set(String key, String value);

	boolean cas(String key, String value, long unique);
	
	boolean delete(String key);

	boolean flushAll();

}
