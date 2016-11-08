package com.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.tools.memcached.whalin.dao.WhalinDao;

@Controller
@RequestMapping("whalin")
public class WhalinController {
	@Resource
	private WhalinDao whalinDao;
	
	@RequestMapping("add")
	public String add() {
		whalinDao.add("name", "mike");
		System.out.println(whalinDao.get("name"));
		return "index";
	}
}
