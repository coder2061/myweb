package com.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.entity.User;
import com.web.model.Dept;

/**
 * 使用Spring 4 MVC中提供的@RestController，
 * 使用最少的代码来构建一个Restful Web Service，
 * 支持返回xml或json数据
 * 
 * @author jiangyf
 */
@RestController
@RequestMapping("/reset")
public class MyRestController {
	
    /**  
    * http://localhost:8080/myweb/reset/json/jade.json
    */
    @RequestMapping("json/{name}")
    public User userinfo(@PathVariable String name) {
    	User user = new User();
		user.setName(name);
		user.setPswd("123456");
        return user;
    }
    
    /**  
    * http://localhost:8080/myweb/reset/xml/jade.xml
    */
    @RequestMapping("xml/{name}")
    public Dept deptinfo(@PathVariable String name) {
    	Dept dept = new Dept();
    	dept.setId("1");
    	dept.setName(name);
        return dept;
    }

}
