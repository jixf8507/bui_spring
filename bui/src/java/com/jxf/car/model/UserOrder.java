package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.UserOrderExtractor;

public class UserOrder implements UserAgingOrder {

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
		return baseDao.getJdbcTemplate().update(ORDER_CHECK_UPDATE_SQL,
				this.status, this.checkDisc, this.checkMen, this.id);
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(getSQL(), new Object[] { id });
	}

	public static UserOrder getUserOrder(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new UserOrderExtractor());
	}

	private static final String getSQL() {
		return "select o.*,u.`name` as userName,u.mobilePhone,u.idCard,g.`name` as goodName,g.img,g.price"
				+ " from user_order o LEFT JOIN `user` u on o.userId=u.id "
				+ "LEFT JOIN dmh_goods g on o.goodsId=g.id where o.id=? ";
	}

	public static boolean batchUpdateStatus(final int status,
			final JSONArray jsonArray, BaseDao baseDao) {
		int[] count = baseDao.getJdbcTemplate().batchUpdate(STATUS_UPDATE_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						int num = 1;
						ps.setInt(num++, status);
						ps.setObject(num++, jsonArray.get(i));
					}

					@Override
					public int getBatchSize() {
						return jsonArray.size();
					}
				});
		return count.length == jsonArray.size();
	}

	private static final String ORDER_CHECK_UPDATE_SQL = "update user_order set `status`=?,checkDisc=?,checkMen=? where id=?";
	private static final String STATUS_UPDATE_SQL = "update user_order set `status`=? where id=?";
	private static final String GET_SQL = "select * from user_order where id=?";

	@Override
	public String getOrderTable() {
		return "user_order";
	}

	@Override
	public BigDecimal getCost() {
		return this.price.subtract(this.sfMoney);
	}

	public static final String getStatus(String value) {
		switch (value) {
		case "1":
			return "审核中";
		case "2":
			return "已通过";
		case "3":
			return "未通过";
		case "4":
			return "购买中";
		case "5":
			return "已配送";
		case "6":
			return "已收货";
		default:
			return "其它";
		}
	}

}
