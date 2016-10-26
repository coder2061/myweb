package com.web.demo.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取注入Spring容器的Properies文件属性
 * 
 * @author jiangyf
 */
@Component
public class Common {
	private static Common instance;
	
    public Common() {
        instance = this;
    }
    
    public static Common getInstance() {
    	return instance;
    }
    
    @Value("#{configProperties['common_username']}")
    private String username;
    @Value("#{configProperties['common_password']}")
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
