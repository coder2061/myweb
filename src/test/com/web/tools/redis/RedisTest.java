package com.web.tools.redis;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisTest {

	/**
	 * 结合spring的redis单机版测试
	 */
	@Test
	public void testSpringSingle() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:config/spring-redis.xml");
		JedisPool jedisPool = (JedisPool) context.getBean("redisClient");
		Jedis jedis = jedisPool.getResource();
		jedis.set("myname", "jack");
		String str = jedis.get("myname");
		System.out.println("--:" + str);
		jedis.close();
		jedisPool.close();
	}

	@Test
	public void testJedisCluster() throws IOException {
		HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.198.130", 7001));
		nodes.add(new HostAndPort("192.168.198.130", 7002));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("myname", "tom");
		String str = cluster.get("myname");
		System.out.println("---:" + str);
		// 关闭连接
		cluster.close();
	}

	/**
	 * 结合spring的jedis集群版
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSpringJedisCluster() throws IOException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:config/spring-redis.xml");
		JedisCluster jedisCluster = (JedisCluster) context
				.getBean(JedisCluster.class);
		jedisCluster.set("myname", "mary");
		String str = jedisCluster.get("myname");
		System.out.println("--:" + str);
		jedisCluster.close();
	}

}
