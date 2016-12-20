package com.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.dao.MemberDao;
import com.web.entity.Member;
import com.web.service.MemberService;
import com.web.tools.redis.core.RedisManager;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Resource
	private MemberDao memberDao;
	@Resource
	private RedisManager<Member> redisManager;

	@Override
	public Map<String, String> singleAccount(String username, String password) {
		Map<String, String> result = new HashMap<String, String>();
		List<Member> list = this.memberDao.find(username);
		if (list!=null && list.size()>0) {
			Member member = list.get(0);
			if (password.equals(member.getPasswd())) {
				// 将当前会员信息放入redis缓存
				redisManager.setCacheObject(member.getId(), member);
				// 设置缓存过期时间，30分钟
				redisManager.expire(member.getId(), 30L, 2);
				result.put("status_code", "0");
				result.put("status_msg", "success");
			} else {
				result.put("status_code", "1");
				result.put("status_msg", "fail");
			}
		} else {
			result.put("status_code", "2");
			result.put("status_msg", "no record about the username");
		}
		return result;
	}

}
