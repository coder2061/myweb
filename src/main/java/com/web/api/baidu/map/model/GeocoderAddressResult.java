package com.web.api.baidu.map.model;

import java.util.List;

public class GeocoderAddressResult {
	private Location location;
	private String formatted_address; // 结构化地址信息
	private String business; // 所在商圈信息，如 "人民大学,中关村,苏州街"
	private AddressComponent addressComponent;
	private String sematic_description; // 当前位置结合POI的语义化结果描述。
	private List<Poi> pois;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public AddressComponent getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(AddressComponent addressComponent) {
		this.addressComponent = addressComponent;
	}

	public String getSematic_description() {
		return sematic_description;
	}

	public void setSematic_description(String sematic_description) {
		this.sematic_description = sematic_description;
	}

	public List<Poi> getPois() {
		return pois;
	}

	public void setPois(List<Poi> pois) {
		this.pois = pois;
	}

}
