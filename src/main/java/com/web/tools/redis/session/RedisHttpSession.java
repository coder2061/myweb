package com.web.tools.redis.session;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 使用redis解决session共享问题
 * spring-session将web的session存到redis服务器指定位置，
 * 集群的所有web服务器在操作session时就使用该指定位置的session
 * 
 * @author jiangyf
 */
@EnableRedisHttpSession
public class RedisHttpSession {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setHostName("127.0.0.1");
        connection.setPort(6379);
        return connection;
    }
}
