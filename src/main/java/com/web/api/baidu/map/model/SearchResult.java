package com.web.api.baidu.map.model;

public class SearchResult {
	private String name;
	private Location location;
	private String address;
	private String telephone;
	private String uid;
	private String street_id;
	private Integer detail;
	private DetailInfo detail_info;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	public Integer getDetail() {
		return detail;
	}

	public void setDetail(Integer detail) {
		this.detail = detail;
	}

	public DetailInfo getDetail_info() {
		return detail_info;
	}

	public void setDetail_info(DetailInfo detail_info) {
		this.detail_info = detail_info;
	}

}
