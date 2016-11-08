package com.web.weixin.config;

public class CouponURI {
	
	/***************************** cash_coupon *****************************/
	
	// 普通红包：一次只发放给一个用户
	public static final String HTTPS_POST_SENDREDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	
	// 裂变红包：一次可以发放一组红包。首先领取的用户为种子用户，种子用户领取一组红包当中的一个，并可以通过社交分享将剩下的红包给其他用户。裂变红包充分利用了人际传播的优势。
	public static final String HTTPS_POST_SENDGROUPREDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack";
	
	// 用于商户对已发放的红包进行查询红包的具体信息，可支持普通红包和裂变红包。
	public static final String HTTPS_POST_GETHBINFO = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo";
	

}
