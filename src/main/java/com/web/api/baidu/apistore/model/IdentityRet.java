package com.web.api.baidu.apistore.model;

public class IdentityRet {
	private String sex; // M-男，F-女，N-未知
	private String birthday; // 出生日期
	private String address; // 身份证归属地 市/县

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
