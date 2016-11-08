package com.web.weixin.model;

/** 
 * 微信接口分析数据  
 */
public class WeixinInterface {
	// 所属公众编号
	private String weixinId;
	// 数据的日期，需在beginDate和endDate之间
	private String refDate;
	// 数据的小时
	private int refHour;
	// 通过服务器配置地址获得消息后，被动回复用户消息的次数
	private long callbackCount;
	// 上述动作的失败次数
	private int failCount;
	// 总耗时，除以callbackCount即为平均耗时
	private long totalTimeCost;
	// 最大耗时
	private long maxTimeCost;

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

	public long getCallbackCount() {
		return callbackCount;
	}

	public void setCallbackCount(long callbackCount) {
		this.callbackCount = callbackCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public long getTotalTimeCost() {
		return totalTimeCost;
	}

	public void setTotalTimeCost(long totalTimeCost) {
		this.totalTimeCost = totalTimeCost;
	}

	public long getMaxTimeCost() {
		return maxTimeCost;
	}

	public void setMaxTimeCost(long maxTimeCost) {
		this.maxTimeCost = maxTimeCost;
	}

}
