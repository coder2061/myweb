package com.web.api.baidu.map.model;

/**
 * 编码地址
 * 
 * @author jiangyf
 */
public class GeocoderLocation {
	private int status;
	private GeocoderLocationResult result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public GeocoderLocationResult getGeocoderLocationResult() {
		return result;
	}

	public void setGeocoderLocationResult(GeocoderLocationResult result) {
		this.result = result;
	}

}
