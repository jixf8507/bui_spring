package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * @author Administrator
 * 
 */
public class UserAccount extends BasePO {

	private Integer id;
	private Integer userId;
	private BigDecimal usableLimit;
	private BigDecimal curUsableLimit;
	private BigDecimal whiteBarLimit;
	private BigDecimal curWhiteBarLimit;
	private BigDecimal balance;
	private Timestamp statementDate;
	private Timestamp repaymentDate;
	private int status;

	private User user;

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

	public BigDecimal getUsableLimit() {
		return usableLimit;
	}

	public void setUsableLimit(BigDecimal usableLimit) {
		this.usableLimit = usableLimit;
	}

	public BigDecimal getCurUsableLimit() {
		return curUsableLimit;
	}

	public void setCurUsableLimit(BigDecimal curUsableLimit) {
		this.curUsableLimit = curUsableLimit;
	}

	public BigDecimal getWhiteBarLimit() {
		return whiteBarLimit;
	}

	public void setWhiteBarLimit(BigDecimal whiteBarLimit) {
		this.whiteBarLimit = whiteBarLimit;
	}

	public BigDecimal getCurWhiteBarLimit() {
		return curWhiteBarLimit;
	}

	public void setCurWhiteBarLimit(BigDecimal curWhiteBarLimit) {
		this.curWhiteBarLimit = curWhiteBarLimit;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Timestamp getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(Timestamp statementDate) {
		this.statementDate = statementDate;
	}

	public Timestamp getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Timestamp repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
