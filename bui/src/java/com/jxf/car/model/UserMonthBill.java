package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

import com.jxf.common.tools.MyDateUtil;

public class UserMonthBill {

	private Integer id;
	private Integer userId;
	private BigDecimal capital = new BigDecimal(0);
	private BigDecimal curBalance = new BigDecimal(0);
	private BigDecimal lastBalance = new BigDecimal(0);
	private BigDecimal paid = new BigDecimal(0);
	private BigDecimal lastLnterest = new BigDecimal(0);
	private BigDecimal curLnterest = new BigDecimal(0);
	private BigDecimal createTime;
	private Integer status;
	private Timestamp repaymentDate;
	private String uuid;

	public static UserMonthBill createUserBill(Map<String, Object> billMap) {
		UserMonthBill bill = new UserMonthBill();
		bill.setId(Integer.parseInt(billMap.get("id") + ""));
		bill.setUserId(Integer.parseInt(billMap.get("userId") + ""));
		bill.setCapital(new BigDecimal(billMap.get("capital") + ""));
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getAllRepaymentCost() {
		BigDecimal cost = this.curBalance.add(this.lastBalance)
				.add(this.lastLnterest).add(this.curLnterest);
		return cost;
	}

	/**
	 * 
	 * @return 本期已还本金
	 */
	public BigDecimal getPaidCapital() {
		BigDecimal paidCapital;
		if (getPaid().compareTo(getCapital()) < 0) {
			paidCapital = getPaid();
			setCapital(getCapital().subtract(getPaid()));
		} else if (getPaid().compareTo(getAllRepaymentCost()) < 0) {
			paidCapital = getCapital();
			setCapital(new BigDecimal(0));
		} else {
			paidCapital = getPaid().add(getCapital()).subtract(
					getAllRepaymentCost());
			setCapital(new BigDecimal(0));
		}
		return paidCapital;
	}

	/**
	 * 还款和计算利息
	 * 
	 * @param dayInterest
	 *            每天利息
	 */
	public void repaymentAndInterest(BigDecimal dayInterest) {
		if (this.paid.compareTo(this.lastBalance) <= 0) {
			this.lastBalance = this.lastBalance.subtract(this.paid);
			this.lastLnterest = this.lastLnterest.add(this.lastBalance
					.multiply(dayInterest));
		} else if (this.paid.compareTo(this.lastBalance.add(this.curBalance)) <= 0) {
			this.curBalance = this.curBalance.add(this.lastBalance).subtract(
					this.paid);
			this.lastBalance = new BigDecimal(0);
		} else if (this.paid.compareTo(this.lastBalance.add(this.curBalance)
				.add(this.lastLnterest)) <= 0) {
			this.lastLnterest = this.lastBalance.add(this.curBalance)
					.add(this.lastLnterest).subtract(this.paid);
			this.lastBalance = new BigDecimal(0);
			this.curBalance = new BigDecimal(0);
		} else if (this.paid.compareTo(this.lastBalance.add(this.curBalance)
				.add(this.lastLnterest).add(this.curLnterest)) < 0) {
			this.curLnterest = this.lastBalance.add(this.curBalance)
					.add(this.lastLnterest).add(this.curLnterest)
					.subtract(this.paid);
			this.lastBalance = new BigDecimal(0);
			this.curBalance = new BigDecimal(0);
			this.lastLnterest = new BigDecimal(0);
		} else {
			this.lastBalance = new BigDecimal(0);
			this.curBalance = new BigDecimal(0);
			this.lastLnterest = new BigDecimal(0);
			this.curLnterest = new BigDecimal(0);
			this.status = 1;
		}

		// 未按期还款
		if (isBeyondRepaymentDate()) {
			this.curLnterest = this.curLnterest.add(this.curBalance
					.multiply(dayInterest));
		}
		this.paid = new BigDecimal(0);
	}

	/**
	 * 超出还款日期未还清本期还款
	 * 
	 * @return
	 */
	public boolean isBeyondRepaymentDate() {
		return this.curBalance.compareTo(new BigDecimal(0)) > 0
				&& new Timestamp(System.currentTimeMillis()).getTime() > getRepaymentDate()
						.getTime();
	}

}
