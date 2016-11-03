package com.web.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.entity.User;
import com.web.tools.redis.cache.UserCache;
import com.web.tools.redis.core.RedisManager;

@Controller
@RequestMapping("cache")
public class CacheController {
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Resource
	private UserCache userCache;
	@Resource
	private RedisManager<User> redisManager;
	
	@RequestMapping("save")
	public String save() {
		//key采取StringRedisSerializer,value采取JdkSerializationRedisSerializer  
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        User user = new User();
        user.setId(1);
        user.setName("jade");
        user.setPswd("1234");
        valueOper.set("user", user);  
        System.out.println(((User)valueOper.get("user")).getName());  
		return "index";
	}
	
	@RequestMapping("get")
	public String get() {
		User user = new User();
        user.setId(1);
        user.setName("jade");
        user.setPswd("1234");
        System.out.println(userCache.add(user));
		User user2 = userCache.get("1");
        System.out.println(user2.getName());  
		return "index";
	}
	
	@RequestMapping("getUser")
	public String getUser() {
		User user = new User();
        user.setId(1);
        user.setName("jade");
        user.setPswd("1234");
        
        redisManager.setCacheObject("user1", user);
        
        User user2 = (User) redisManager.getCacheObject("user1");
        System.out.println(user2.getName());  
		return "index";
	}
}
