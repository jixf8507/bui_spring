package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.UserBorrowExtractor;

public class UserBorrow implements UserAgingOrder {

	private Integer id;
	private Integer userId;
	private BigDecimal cost;
	private Integer aging;
	private Integer status;
	private String checkDisc;
	private String checkMen;
	private Integer bankCardId;

	public static UserBorrow create(ResultSet rs) throws SQLException {
		UserBorrow ub = new UserBorrow();
		ub.id = rs.getInt("id");
		ub.userId = rs.getInt("userId");
		ub.cost = rs.getBigDecimal("cost");
		ub.aging = rs.getInt("aging");
		ub.status = rs.getInt("status");
		ub.checkDisc = rs.getString("checkDisc");
		ub.checkMen = rs.getString("checkMen");
		ub.bankCardId = rs.getInt("bankCardId");
		return ub;
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(getSQL(), new Object[] { id });
	}

	public static UserBorrow getUserBorrow(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new UserBorrowExtractor());
	}

	public int updateStatus(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(STATUS_UPDATE_SQL, this.status,
				this.checkDisc, this.checkMen, this.id);
	}

	private static final String getSQL() {
		return "select b.*,u.`name`,u.mobilePhone,u.idCard"
				+ " from user_borrow b,`user` u"
				+ " where b.userId=u.id and b.id=?";
	}

	private static final String GET_SQL = "select * from user_borrow where id=?";
	private static final String STATUS_UPDATE_SQL = "update user_borrow set `status`=?,checkDisc=?,checkMen=? where id=?";

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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Integer getAging() {
		return aging;
	}

	public void setAging(Integer aging) {
		this.aging = aging;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCheckDisc() {
		return checkDisc;
	}

	public void setCheckDisc(String checkDisc) {
		this.checkDisc = checkDisc;
	}

	public String getCheckMen() {
		return checkMen;
	}

	public void setCheckMen(String checkMen) {
		this.checkMen = checkMen;
	}

	public Integer getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(Integer bankCardId) {
		this.bankCardId = bankCardId;
	}

	@Override
	public BigDecimal getPrice() {
		return this.getCost();
	}

	@Override
	public String getOrderTable() {
		return "user_borrow";
	}

}
