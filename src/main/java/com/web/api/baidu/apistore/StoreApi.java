package com.web.api.baidu.apistore;

public class StoreApi {
	/** http://www.haoservice.com，手机归属地查询 1000次  **/
	public static final String HS_API_KEY = "0e940bc8b0da4e029948f9bec7315fc1";
	
	public static final String HS_MOBILE = "http://apis.haoservice.com/mobile?phone=%s&key=%s";
	
	public static final String API_KEY = "90ffe6b645be6917e6ca9be3f7f91323";

	// 查询手机号的归属地信息。不收费，暂不限制峰值，大规模应用调用只需邮件apistore报备应用和联系人信息，否则视为恶意攻击禁止访问
	public static final String MOBILENUMBER = "http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=%s";

	// 精准解读身份证信息，提供身份证归属地信息查询。不收费，如有大规模高并发调用，请邮件apistore@baidu.com报备联系人和app信息，否则视为恶意攻击禁止访问
	public static final String IDENTITY = "http://apis.baidu.com/apistore/idservice/id?id=%s";
}
