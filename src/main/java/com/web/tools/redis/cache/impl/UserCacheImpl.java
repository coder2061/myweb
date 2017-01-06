package com.web.tools.redis.cache.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.web.entity.User;
import com.web.tools.redis.cache.UserCache;
import com.web.tools.redis.core.RedisCache;

@Service("userCache")
public class UserCacheImpl extends RedisCache<String, User> implements
		UserCache {
	/**
	 * 新增
	 *
	 * @param user
	 */
	@Override
	public boolean add(final User user) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(user.getId().toString());
				byte[] name = serializer.serialize(user.getName());
				return connection.setNX(key, name);
			}
		});
		return result;
	}

	/**
	 * 批量新增 使用pipeline方式
	 *
	 * @param list
	 */
	@Override
	public boolean add(final List<User> list) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				for (User user : list) {
					byte[] key = serializer.serialize(user.getId().toString());
					byte[] name = serializer.serialize(user.getName());
					connection.setNX(key, name);
				}
				return true;
			}
		}, false, true);
		return result;
	}

	/**
	 * 删除
	 * 
	 * @param key
	 */
	@Override
	public void delete(String key) {
		List<String> list = new ArrayList<String>();
		list.add(key);
		delete(list);
	}

	/**
	 * 批量删除
	 * 
	 * @param keys
	 */
	@Override
	public void delete(List<String> keys) {
		redisTemplate.delete(keys);
	}

	/**
	 * 修改
	 * 
	 * @param user
	 */
	@Override
	public boolean update(final User user) {
		String key = user.getId().toString();
		if (get(key) == null) {
			throw new NullPointerException("数据行不存在, key = " + key);
		}
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(user.getId().toString());
				byte[] name = serializer.serialize(user.getName());
				connection.set(key, name);
				return true;
			}
		});
		return result;
	}

	/**
	 * 通过key获取
	 * 
	 * @param keyId
	 */
	@Override
	public User get(final String keyId) {
		User result = redisTemplate.execute(new RedisCallback<User>() {
			public User doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				String name = serializer.deserialize(value);

				return new User(keyId, name, null);
			}
		});
		return result;
	}

}
