package com.web.tools.memcached.core;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * memcached缓存的模型
 *
 * @author jiangyf
 */
public class MemCache {
	private static Logger log = LoggerFactory.getLogger(MemCache.class);

	private Set<String> keySet = new HashSet<String>();
	private final String name;
	private final int expire;
	private final MemcachedClient memcachedClient;

	public MemCache(String name, int expire, MemcachedClient memcachedClient) {
		this.name = name;
		this.expire = expire;
		this.memcachedClient = memcachedClient;
	}

	public Object get(String key) {
		Object value = null;
		key = this.getKey(key);
		try {
			value = memcachedClient.get(key);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return value;
	}

	public void put(String key, Object value) {
		if (value == null)
			return;
		try {
			key = this.getKey(key);
			memcachedClient.setWithNoReply(key, expire, value);
			keySet.add(key);
		} catch (InterruptedException e) {
			log.warn("更新 Memcached 缓存被中断", e);
		} catch (MemcachedException e) {
			log.warn("更新 Memcached 缓存错误", e);
		}
	}

	public void delete(String key) {
		try {
			key = this.getKey(key);
			memcachedClient.deleteWithNoReply(key);
		} catch (InterruptedException e) {
			log.warn("删除 Memcached 缓存被中断", e);
		} catch (MemcachedException e) {
			log.warn("删除 Memcached 缓存错误", e);
		}
	}
	
	public void clear() {
		for (String key : keySet) {
			try {
				memcachedClient.deleteWithNoReply(this.getKey(key));
			} catch (InterruptedException e) {
				log.warn("删除 Memcached 缓存被中断", e);
			} catch (MemcachedException e) {
				log.warn("删除 Memcached 缓存错误", e);
			}
		}
	}

	private String getKey(String key) {
		return name + "_" + key;
	}
}
