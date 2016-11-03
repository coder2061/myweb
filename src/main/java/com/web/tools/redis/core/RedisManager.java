package com.web.tools.redis.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service("redisManager")
public class RedisManager<T> {
	@Resource
	private RedisTemplate<Serializable, Object> redisTemplate;
	
	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的key
	 * 
	 * @param key
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate
				.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param expireTime
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * 
	 * @param key
	 *            缓存的键值
	 * @param value
	 *            缓存的值
	 * @return 缓存的对象
	 */
	public ValueOperations<Serializable, Object> setCacheObject(String key,
			T value) {
		ValueOperations<Serializable, Object> operation = redisTemplate
				.opsForValue();
		operation.set(key, value);
		return operation;
	}

	/**
	 * 获得缓存的基本对象。
	 * 
	 * @param key
	 *            缓存键值
	 * @return 缓存键值对应的数据
	 */
	public Object getCacheObject(String key) {
		ValueOperations<Serializable, Object> operation = redisTemplate
				.opsForValue();
		return operation.get(key);
	}

	/**
	 * 缓存List数据
	 * 
	 * @param key
	 *            缓存的键值
	 * @param dataList
	 *            待缓存的List数据
	 * @return 缓存的对象
	 */
	public ListOperations<Serializable, Object> setCacheList(String key,
			List<T> dataList) {
		ListOperations<Serializable, Object> listOperation = redisTemplate
				.opsForList();
		if (null != dataList) {
			int size = dataList.size();
			for (int i = 0; i < size; i++) {
				listOperation.rightPush(key, dataList.get(i));
			}
		}
		return listOperation;
	}

	/**
	 * 获得缓存的list对象
	 * 
	 * @param key
	 *            缓存的键值
	 * @return 缓存键值对应的数据
	 */
	@SuppressWarnings("unchecked")
	public List<T> getCacheList(String key) {
		List<T> dataList = new ArrayList<T>();
		ListOperations<Serializable, Object> listOperation = redisTemplate
				.opsForList();
		Long size = listOperation.size(key);
		for (int i = 0; i < size; i++) {
			dataList.add((T) listOperation.leftPop(key));
		}
		return dataList;
	}

	/**
	 * 缓存Set
	 * 
	 * @param key
	 *            缓存键值
	 * @param dataSet
	 *            缓存的数据
	 * @return 缓存数据的对象
	 */
	public BoundSetOperations<Serializable, Object> setCacheSet(String key,
			Set<T> dataSet) {
		BoundSetOperations<Serializable, Object> setOperation = redisTemplate
				.boundSetOps(key);
//		T[] t = (T[]) dataSet.toArray();
//		setOperation.add(t);
		Iterator<T> it = dataSet.iterator();
		while (it.hasNext()) {
			setOperation.add(it.next());
		}
		return setOperation;
	}

	/**
	 * 获得缓存的set
	 * 
	 * @param key
	 * @param operation
	 */
	@SuppressWarnings("unchecked")
	public Set<T> getCacheSet(String key) {
		Set<T> dataSet = new HashSet<T>();
		BoundSetOperations<Serializable, Object> operation = redisTemplate
				.boundSetOps(key);
		Long size = operation.size();
		for (int i = 0; i < size; i++) {
			dataSet.add((T) operation.pop());
		}
		return dataSet;
	}

	/**
	 * 缓存Map
	 * 
	 * @param key
	 * @param dataMap
	 */
	public HashOperations<Serializable, Object, Object> setCacheMap(String key,
			Map<String, T> dataMap) {
		HashOperations<Serializable, Object, Object> hashOperations = redisTemplate
				.opsForHash();
		if (null != dataMap) {
			for (Map.Entry<String, T> entry : dataMap.entrySet()) {
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}
		}
		return hashOperations;
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 */
	public Map<Object, Object> getCacheMap(String key) {
		Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
		return map;
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 * @param hashOperation
	 */
	public Map<String, T> getCacheMap(String key,
			HashOperations<String, String, T> hashOperation) {
		return hashOperation.entries(key);
	}

	/**
	 * 缓存Map
	 * 
	 * @param key
	 * @param dataMap
	 */
	public HashOperations<Serializable, Object, Object> setCacheIntegerMap(
			String key, Map<Integer, T> dataMap) {
		HashOperations<Serializable, Object, Object> hashOperations = redisTemplate
				.opsForHash();
		if (null != dataMap) {
			for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}
		}
		return hashOperations;
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 */
	public Map<Object, Object> getCacheIntegerMap(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 * @param hashOperation
	 */
	public Map<String, T> getCacheIntegerMap(String key,
			HashOperations<String, String, T> hashOperation) {
		return hashOperation.entries(key);
	}

}
