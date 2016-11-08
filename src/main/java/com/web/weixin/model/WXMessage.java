package com.web.weixin.model;

/** 
 * 消息分析数据 
 */
public class WXMessage {
	// 数据的日期
	private String ref_date;
	// 数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
	private int ref_hour;
	// 消息类型，代表含义如下：1代表文字 2代表图片 3代表语音 4代表视频 6代表第三方应用消息（链接消息）
	private int msg_type;
	// 上行发送了（向公众号发送了）消息的用户数
	private int msg_user;
	// 上行发送了消息的消息总数
	private int msg_count;
	// 当日发送消息量分布的区间，0代表 “0”，1代表“1-5”，2代表“6-10”，3代表“10次以上”
	private int count_interval;
	
	public String getRef_date() {
		return ref_date;
	}
	public void setRef_date(String ref_date) {
		this.ref_date = ref_date;
	}
	public int getRef_hour() {
		return ref_hour;
	}
	public void setRef_hour(int ref_hour) {
		this.ref_hour = ref_hour;
	}
	public int getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}
	public int getMsg_user() {
		return msg_user;
	}
	public void setMsg_user(int msg_user) {
		this.msg_user = msg_user;
	}
	public int getMsg_count() {
		return msg_count;
	}
	public void setMsg_count(int msg_count) {
		this.msg_count = msg_count;
	}
	public int getCount_interval() {
		return count_interval;
	}
	public void setCount_interval(int count_interval) {
		this.count_interval = count_interval;
	}
	
}
