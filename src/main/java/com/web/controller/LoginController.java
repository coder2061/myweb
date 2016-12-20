package com.web.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.core.config.GithubInfo;
import com.web.service.MemberService;
import com.web.utils.HttpUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private MemberService memberService;

	/**
	 * 单个账号登录
	 */
	@RequestMapping(value = "/singleAccount", method = RequestMethod.POST)
	public void singleAccount(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, String> result = this.memberService.singleAccount(username, password);
		System.out.println(result.get("status_code") + "---" + result.get("status_msg"));
	}
	
	/**
	 * 单点登录
	 */
	public String singlePoint() {
		return null;
	}

	/**
	 * 授权github用户登录-callback url
	 */
	@RequestMapping(value = "loginByGithub")
	@ResponseBody
	public String loginByGithub(String state, String code) {
		String authorUrl = GithubInfo.POST_OAUTH_AUTHORIZE
				.replace("ClientId", GithubInfo.CLIENT_ID)
				.replace("ClientSecret", GithubInfo.CLIENT_SECRET)
				.replace("State", state)
				.replace("RedirectUri", GithubInfo.LOGINBYGITHUB);
		String tokenUrl = GithubInfo.GET_OAUTH_ACCESSTOKEN
				.replace("ClientId", GithubInfo.CLIENT_ID)
				.replace("ClientSecret", GithubInfo.CLIENT_SECRET)
				.replace("Code", code)
				.replace("RedirectUri", GithubInfo.LOGINBYGITHUB);
		String me = HttpUtil.sendGet(tokenUrl);
		String token = me.split("&")[0];
		return HttpUtil.sendGet(GithubInfo.GET_API_USER.replace("AccessToken",
				token));
	}

}
