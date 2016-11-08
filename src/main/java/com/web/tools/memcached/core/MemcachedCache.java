package com.web.tools.memcached.core;

import java.util.concurrent.Callable;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class MemcachedCache implements Cache {
	private final String name;
	private final MemCache memCache;

	public MemcachedCache(String name, int expire,
			MemcachedClient memcachedClient) {
		this.name = name;
		this.memCache = new MemCache(name, expire, memcachedClient);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public MemCache getNativeCache() {
		return this.memCache;
	}

	@Override
	public ValueWrapper get(Object key) {
		ValueWrapper wrapper = null;
		Object value = memCache.get(key.toString());
		if (value != null) {
			wrapper = new SimpleValueWrapper(value);
		}
		return wrapper;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Object key, Class<T> type) {
		Object cacheValue = this.memCache.get(key.toString());
		if (type != null && !type.isInstance(cacheValue)) {
			throw new IllegalStateException(
					"Cached value is not of required type [" + type.getName()
							+ "]: " + cacheValue);
		}
		return (T) cacheValue;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		return null;
	}

	@Override
	public void put(Object key, Object value) {
		memCache.put(key.toString(), value);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		return null;
	}

	@Override
	public void evict(Object key) {
		memCache.delete(key.toString());
	}

	@Override
	public void clear() {
		memCache.clear();
	}

}
