package com.web.tools.memcached.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

/**
 * memcached与spring提供的cache接口整合 
 * 
 * @author jiangyf
 */
public class MemcachedManager extends AbstractTransactionSupportingCacheManager {
	private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
	private Map<String, Integer> expireMap = new HashMap<String, Integer>(); // 缓存的时间
	
	private MemcachedClient memcachedClient; // xmemcached的客户端

	public MemcachedManager() {
		
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		return cacheMap.values();
	}

	@Override
	public Cache getCache(String name) {
		Cache cache = cacheMap.get(name);
		if (cache == null) {
			Integer expire = expireMap.get(name);
			if (expire == null) {
				expire = 0;
				expireMap.put(name, expire);
			}
			cache = new MemcachedCache(name, expire.intValue(), memcachedClient);
			cacheMap.put(name, cache);
		}
		return cache;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setExpireMap(Map<String, Integer> expireMap) {
		this.expireMap = expireMap;
	}

}
