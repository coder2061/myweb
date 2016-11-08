package com.web.core.config;

public class GithubInfo {
	public static final String CLIENT_ID = "3c04323acc3bcf8afd55";
	public static final String CLIENT_SECRET = "7967ba296a20e79cd304b2dc6ca78f088c55f6f0";
	
	// 授权github用户登录
	public static final String LOGINBYGITHUB = "http://127.0.0.1/myweb/loginByGithub";
	
	// 用户点击github登录本地应用引导用户跳转到第三方授权页
	public static final String POST_OAUTH_AUTHORIZE = "https://github.com/login/oauth/authorize?client_id=ClientId&state=State&redirect_uri=RedirectUri";
	// 授权成功后会回调第三方平台
	public static final String GET_OAUTH_ACCESSTOKEN = "https://github.com/login/oauth/access_token?client_id=ClientId&client_secret=ClientSecret&code=Code&redirect_uri=RedirectUri";
	// 获取用户信息
	public static final String GET_API_USER = "https://api.github.com/user?access_token=AccessToken";

}
