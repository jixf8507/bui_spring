package com.jxf.car.model;

import java.math.BigDecimal;

public class MerchantOrder implements UserAgingOrder {

	private Integer id;
	private Integer userId;
	private Integer merchantId;
	private BigDecimal cost;
	private Integer aging;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public Integer getUserId() {
		return this.userId;
	}

	@Override
	public BigDecimal getCost() {
		return this.cost;
	}

	@Override
	public Integer getAging() {
		return this.aging;
	}

	@Override
	public String getOrderTable() {
		return "merchant_order";
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public void setAging(Integer aging) {
		this.aging = aging;
	}

}
