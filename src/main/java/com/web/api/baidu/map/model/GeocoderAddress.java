package com.web.api.baidu.map.model;

public class GeocoderAddress {
	private int status;
	private GeocoderAddressResult result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public GeocoderAddressResult getResult() {
		return result;
	}

	public void setResult(GeocoderAddressResult result) {
		this.result = result;
	}

}
