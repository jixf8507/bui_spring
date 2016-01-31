package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserBillDetail {

	private Integer id;
	private Integer userId;
	private Integer orderId;
	private BigDecimal capital;
	private BigDecimal interest;
	private BigDecimal totleCost;
	private Timestamp repaymentTime;

	public static List<UserBillDetail> createUserBill(BigDecimal price,
			BigDecimal interest, int aging) {
		// Date date = MyDateUti
		List<UserBillDetail> list = new ArrayList<>();
		BigDecimal billCapital = price.divide(new BigDecimal(aging));
		BigDecimal totleInterest = price.subtract(interest);
		BigDecimal billInterest = totleInterest.divide(new BigDecimal(aging));

		BigDecimal tempCapital = new BigDecimal(0);
		BigDecimal tempInterest = new BigDecimal(0);
		for (int i = 0; i <= aging; i++) {

		}
		return list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getTotleCost() {
		return totleCost;
	}

	public void setTotleCost(BigDecimal totleCost) {
		this.totleCost = totleCost;
	}

	public Timestamp getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Timestamp repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

}
