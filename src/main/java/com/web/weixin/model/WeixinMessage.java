package com.web.weixin.model;

/** 
 * 消息分析数据 
 */
public class WeixinMessage {
	// 所属公众编号
	private String weixinId;
	// 数据的日期
	private String refDate;
	// 数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
	private int refHour;
	// 消息类型，代表含义如下：1代表文字 2代表图片 3代表语音 4代表视频 6代表第三方应用消息（链接消息）
	private int msgType;
	// 上行发送了（向公众号发送了）消息的用户数
	private int msgUser;
	// 上行发送了消息的消息总数
	private int msgCount;
	// 当日发送消息量分布的区间，0代表 “0”，1代表“1-5”，2代表“6-10”，3代表“10次以上”
	private int countInterval;
	
	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getRefDate() {
		return refDate;
	}

	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}

	public int getRefHour() {
		return refHour;
	}

	public void setRefHour(int refHour) {
		this.refHour = refHour;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public int getMsgUser() {
		return msgUser;
	}

	public void setMsgUser(int msgUser) {
		this.msgUser = msgUser;
	}

	public int getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	public int getCountInterval() {
		return countInterval;
	}

	public void setCountInterval(int countInterval) {
		this.countInterval = countInterval;
	}

}
