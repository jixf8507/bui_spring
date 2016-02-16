package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

import com.jxf.common.tools.MyDateUtil;

public class UserMonthBill {

	private Integer id;
	private Integer userId;
	private BigDecimal curBalance = new BigDecimal(0);
	private BigDecimal lastBalance = new BigDecimal(0);
	private BigDecimal paid = new BigDecimal(0);
	private BigDecimal lastLnterest = new BigDecimal(0);
	private BigDecimal curLnterest = new BigDecimal(0);
	private BigDecimal createTime;
	private Integer status;
	private Timestamp repaymentDate;

	public static UserMonthBill createUserBill(Map<String, Object> billMap) {
		UserMonthBill bill = new UserMonthBill();
		bill.setId(Integer.parseInt(billMap.get("id") + ""));
		bill.setUserId(Integer.parseInt(billMap.get("userId") + ""));
		bill.setCurBalance(new BigDecimal(billMap.get("curBalance") + ""));
		bill.setLastBalance(new BigDecimal(billMap.get("lastBalance") + ""));
		bill.setPaid(new BigDecimal(billMap.get("paid") + ""));
		bill.setLastLnterest(new BigDecimal(billMap.get("lastLnterest") + ""));
		bill.setCurLnterest(new BigDecimal(billMap.get("curLnterest") + ""));
		bill.setStatus(Integer.parseInt(billMap.get("status") + ""));
		bill.setRepaymentDate(MyDateUtil.strToTimestamp(billMap
				.get("repaymentDate") + ""));
		return bill;
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

	public BigDecimal getCurBalance() {
		return curBalance;
	}

	public void setCurBalance(BigDecimal curBalance) {
		this.curBalance = curBalance;
	}

	public BigDecimal getPaid() {
		return paid;
	}

	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	public BigDecimal getLastLnterest() {
		return lastLnterest;
	}

	public void setLastLnterest(BigDecimal lastLnterest) {
		this.lastLnterest = lastLnterest;
	}

	public BigDecimal getCurLnterest() {
		return curLnterest;
	}

	public void setCurLnterest(BigDecimal curLnterest) {
		this.curLnterest = curLnterest;
	}

	public BigDecimal getCreateTime() {
		return createTime;
	}

	public void setCreateTime(BigDecimal createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Timestamp repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public BigDecimal getLastBalance() {
		return lastBalance;
	}

	public void setLastBalance(BigDecimal lastBalance) {
		this.lastBalance = lastBalance;
	}

	public boolean isBeyondRepaymentDate() {
		return getCurBalance().compareTo(new BigDecimal(0)) > 0
				&& new Timestamp(System.currentTimeMillis()).getTime() > getRepaymentDate().getTime();
	}

}
