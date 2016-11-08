package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.core.config.GithubInfo;
import com.web.utils.HttpUtil;

@Controller
public class LoginController {
	
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
		return HttpUtil.sendGet(GithubInfo.GET_API_USER.replace("AccessToken", token));
	}

}
