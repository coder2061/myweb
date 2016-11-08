package com.web.tools.memcached.whalin.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.schooner.MemCached.MemcachedItem;
import com.web.tools.memcached.whalin.dao.WhalinDao;
import com.web.tools.memcached.whalin.model.CacheItem;
import com.whalin.MemCached.MemCachedClient;

@Repository("whalinDao")
public class WhalinDaoImpl implements WhalinDao {
	@Resource
	private MemCachedClient memCachedClient;

	public boolean add(String key, String value) {
		return memCachedClient.add(key, value);
	}

	public boolean cas(String key, String value, long unique) {
		return memCachedClient.cas(key, value, unique);
	}

	public String get(String key) {
		return (String) memCachedClient.get(key);
	}

	public CacheItem gets(String key) {
		MemcachedItem item = memCachedClient.gets(key);
		return new CacheItem(key, (String) item.getValue(), item.getCasUnique());
	}

	public boolean set(String key, String value) {
		return memCachedClient.set(key, value);
	}

	public boolean delete(String key) {
		return memCachedClient.delete(key);
	}

	public boolean flushAll() {
		return memCachedClient.flushAll();
	}

}
