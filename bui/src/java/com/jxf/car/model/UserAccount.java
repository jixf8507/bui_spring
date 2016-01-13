package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

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

	public static String getStatus(String accountStatus) {
		switch (accountStatus) {
		case "1":
			return "正常";
		case "2":
			return "不可取现可分期";
		case "3":
			return "不可取现不可分期";
		default:
			return "其它";
		}
	}

	/**
	 * 修改客户账户
	 * 
	 * @param baseDao
	 * @return
	 */
	public int update(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.usableLimit,
				this.whiteBarLimit, this.id);
	}

	/**
	 * 新增客户账户
	 * 
	 * @param baseDao
	 * @return
	 */
	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.setBigDecimal(2, usableLimit);
				ps.setBigDecimal(3, usableLimit);
				ps.setBigDecimal(4, whiteBarLimit);
				ps.setBigDecimal(5, whiteBarLimit);
				return ps;
			}
		}, keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	// 根据登录号查找系统用户的SQL
	public static final String GET_BY_ID_SQL = "select a.*,u.`name`,u.mobilePhone,u.idCard from user_account a INNER JOIN user u on a.userId=u.id where a.id=? ";
	private static final String INSERT_SQL = "insert into user_account (userId,usableLimit,curUsableLimit,whiteBarLimit,curWhiteBarLimit,statementDate,repaymentDate,`status`) values (?,?,?,?,?,now(),now(),1)";
	private static final String UPDATE_SQL = "update user_account set usableLimit=?,whiteBarLimit=? where id=?";

}
