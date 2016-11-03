package com.web.tools.redis.core;

import java.io.IOException;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisManager {

	/**
	 * 初始化JedisPool
	 */
	public static JedisPool getJedisPool() {
		JedisPool pool = null;
		try {
			Properties props = new Properties();
			props.load(JedisManager.class.getClassLoader().getResourceAsStream(
					"config/redis.properties"));
			// 创建jedis池配置实例
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置jedis池配置项值
			config.setMaxIdle(Integer.valueOf(props
					.getProperty("redis.maxIdle")));
			config.setMaxWaitMillis(Long.valueOf(props
					.getProperty("redis.maxWaitMillis")));
			config.setTestOnBorrow(Boolean.valueOf(props
					.getProperty("redis.testOnBorrow")));
			config.setTestOnReturn(Boolean.valueOf(props
					.getProperty("redis.testOnReturn")));
			// 根据配置实例化jedis池
			pool = new JedisPool(config, props.getProperty("redis.hostName"),
					Integer.valueOf(props.getProperty("redis.port")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pool;
	}

	/** 获得jedis对象 */

	public static Jedis getJedis(JedisPool pool) {
		return pool.getResource();
	}

	/** 归还jedis对象 */
	@SuppressWarnings("deprecation")
	public static void recycleJedis(JedisPool pool, Jedis jedis) {
		pool.returnResource(jedis);
	}

	public static void main(String[] args) {
		JedisPool pool = getJedisPool();
		Jedis jedis = getJedis(pool);
		jedis.set("myname", "zhuxun");
		System.out.println(jedis.get("myname"));
		jedis.del("myname");
		System.out.println(jedis.exists("myname"));
		recycleJedis(pool, jedis);
	}

}
