package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.UserOrderExtractor;

public class UserOrder {

	private Integer id;
	private Integer userId;
	private Integer goodsId;
	private BigDecimal sfMoney;
	private Integer aging;
	private Integer status;
	private Integer type;
	private BigDecimal price;
	private String checkDisc;
	private String checkMen;

	public static UserOrder create(ResultSet rs) throws SQLException {
		UserOrder userOrder = new UserOrder();
		userOrder.id = rs.getInt("id");
		userOrder.userId = rs.getInt("userId");
		userOrder.goodsId = rs.getInt("goodsId");
		userOrder.sfMoney = rs.getBigDecimal("sfMoney");
		userOrder.aging = rs.getInt("aging");
		userOrder.status = rs.getInt("status");
		userOrder.type = rs.getInt("type");
		userOrder.price = rs.getBigDecimal("price");
		userOrder.checkDisc = rs.getString("checkDisc");
		userOrder.checkMen = rs.getString("checkMen");
		return userOrder;
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

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getSfMoney() {
		return sfMoney;
	}

	public void setSfMoney(BigDecimal sfMoney) {
		this.sfMoney = sfMoney;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public int updateStatus(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(STATUS_UPDATE_SQL, this.status,
				this.checkDisc, this.checkMen, this.id);
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(getSQL(), new Object[] { id });
	}

	public static UserOrder getUserOrder(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new UserOrderExtractor());
	}

	private static final String getSQL() {
		return "select o.*,u.`name` as userName,u.mobilePhone,u.idCard,g.`name` as goodName,"
				+ "g.img,g.price,g.des1,g.des2,g.des3,m.`name` as merchantName,m.address,m.corporation"
				+ " from user_order o LEFT JOIN `user` u on o.userId=u.id "
				+ "LEFT JOIN merchant_goods g on o.goodsId=g.id "
				+ "LEFT JOIN merchant m on g.merchantId=m.id where o.id=? ";
	}

	private static final String STATUS_UPDATE_SQL = "update user_order set `status`=?,checkDisc=?,checkDisc=? where id=?";
	private static final String GET_SQL = "select * from user_order where id=?";

}
