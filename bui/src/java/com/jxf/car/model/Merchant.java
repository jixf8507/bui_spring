package com.jxf.car.model;

public class Merchant extends BasePO {

	private Integer id;
	private String name;
	private String address;
	private String img;
	private String des;
	private String corporation;
	private String mobilePhone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}
