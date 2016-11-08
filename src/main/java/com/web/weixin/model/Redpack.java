package com.web.weixin.model;
/**
 * 普通红包   
 * 
 * @author jiangyf   
 */
public class Redpack {
	private String nonce_str;
	private String sign;
	private String mch_billno;
	private String mch_id;
	private String sub_mch_id;
	private String wxappid;
	private String msgappid;
	private String send_name;
	private String re_openid;
	private int total_amount;
	private int total_num;
	private String amt_type;
	private String client_ip;
	private String consume_mch_id;
	private String wishing;
	private String act_name;
	private String remark;
	private String scene_id;
	private String risk_info;
	
	public String getSub_mch_id() {
		return sub_mch_id;
	}
	public void setSub_mch_id(String subMchId) {
		sub_mch_id = subMchId;
	}
	public String getMsgappid() {
		return msgappid;
	}
	public void setMsgappid(String msgappid) {
		this.msgappid = msgappid;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String clientIp) {
		client_ip = clientIp;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getMch_billno() {
		return mch_billno;
	}
	public void setMch_billno(String mchBillno) {
		mch_billno = mchBillno;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mchId) {
		mch_id = mchId;
	}
	public String getWxappid() {
		return wxappid;
	}
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String sendName) {
		send_name = sendName;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String reOpenid) {
		re_openid = reOpenid;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int totalAmount) {
		total_amount = totalAmount;
	}
	public int getTotal_num() {
		return total_num;
	}
	public void setTotal_num(int totalNum) {
		total_num = totalNum;
	}
	public String getAmt_type() {
		return amt_type;
	}
	public void setAmt_type(String amtType) {
		amt_type = amtType;
	}
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String actName) {
		act_name = actName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getScene_id() {
		return scene_id;
	}
	public void setScene_id(String sceneId) {
		scene_id = sceneId;
	}
	public String getRisk_info() {
		return risk_info;
	}
	public void setRisk_info(String riskInfo) {
		risk_info = riskInfo;
	}
	public String getConsume_mch_id() {
		return consume_mch_id;
	}
	public void setConsume_mch_id(String consumeMchId) {
		consume_mch_id = consumeMchId;
	}
}
