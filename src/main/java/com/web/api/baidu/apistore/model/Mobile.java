package com.web.api.baidu.apistore.model;

public class Mobile {
	private String error_code;
	private String reason;
	private MobileResult result;

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public MobileResult getResult() {
		return result;
	}

	public void setResult(MobileResult result) {
		this.result = result;
	}

}
