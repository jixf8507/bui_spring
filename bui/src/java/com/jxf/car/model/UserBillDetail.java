package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserBillDetail {

	private Integer id;
	private Integer userId;
	private Integer orderId;
	private BigDecimal capital;
	private BigDecimal interest;
	private BigDecimal totleCost;
	private Timestamp repaymentTime;
	private String orderTable;

	public static List<UserBillDetail> createUserBillDetailByOrder(
			UserAgingOrder uo, BigDecimal interest) {
		Calendar current = Calendar.getInstance();
		List<UserBillDetail> list = new ArrayList<>();
		BigDecimal billCapital = uo.getCost().divide(
				new BigDecimal(uo.getAging()), 2, BigDecimal.ROUND_UP);
		BigDecimal totleInterest = uo.getCost().multiply(interest);
		BigDecimal billInterest = totleInterest.divide(
				new BigDecimal(uo.getAging()), 2, BigDecimal.ROUND_UP);

		BigDecimal tempCapital = new BigDecimal(0);
		BigDecimal tempInterest = new BigDecimal(0);
		for (int i = 0; i < uo.getAging(); i++) {
			if (i == uo.getAging() - 1) {
				billCapital = uo.getCost().subtract(tempCapital);
				billInterest = totleInterest.subtract(tempInterest);
			}
			UserBillDetail billDetail = createUserBillDetail(uo.getUserId(),
					uo.getId(), uo.getOrderTable(), current, billCapital,
					billInterest);
			current.add(Calendar.MONTH, 1);
			tempCapital = tempCapital.add(billCapital);
			tempInterest = tempInterest.add(billInterest);
			list.add(billDetail);
		}
		return list;
	}

	public static UserBillDetail createUserBillDetail(Integer userId,
			Integer orderId, String orderTable, Calendar current,
			BigDecimal billCapital, BigDecimal billInterest) {
		UserBillDetail billDetail = new UserBillDetail();
		billDetail.userId = userId;
		billDetail.orderId = orderId;
		billDetail.capital = billCapital;
		billDetail.interest = billInterest;
		billDetail.totleCost = billDetail.capital.add(billDetail.interest);
		billDetail.repaymentTime = new Timestamp(current.getTime().getTime());
		billDetail.orderTable = orderTable;
		return billDetail;
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

	public String getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(String orderTable) {
		this.orderTable = orderTable;
	}

}
