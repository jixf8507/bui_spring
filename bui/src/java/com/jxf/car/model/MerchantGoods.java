package com.jxf.car.model;

import java.math.BigDecimal;

public class MerchantGoods extends BasePO {

	private Integer id;
	private Integer merchantId;
	private String name;
	private BigDecimal price;
	private String img;
	private String des1;
	private String des2;
	private String des3;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDes1() {
		return des1;
	}

	public void setDes1(String des1) {
		this.des1 = des1;
	}

	public String getDes2() {
		return des2;
	}

	public void setDes2(String des2) {
		this.des2 = des2;
	}

	public String getDes3() {
		return des3;
	}

	public void setDes3(String des3) {
		this.des3 = des3;
	}

}
