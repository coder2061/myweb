package com.web.tools.redis.listener;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.web.entity.User;
import com.web.service.UserService;
import com.web.tools.redis.core.RedisManager;

/**   
 * 监听器，用于项目启动的时候初始化信息
 * 
 * @author jiangyf   
 */
public class RedisListener implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger log= Logger.getLogger(RedisListener.class);
    
    @Resource
	private RedisManager<User> redisManager;
    
    @Autowired
    private UserService userService;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	log.info("-----------缓存数据-----------");
    	User user = userService.getUser(1);
    	redisManager.setCacheObject("2", user);
    }
    
}
