package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

public class UserAccountRecord {

	private Integer id;
	private Integer userId;
	private BigDecimal money;
	private String type;

	public static UserAccountRecord createByUserOrder(UserOrder order) {
		UserAccountRecord accountRecord = new UserAccountRecord();
		accountRecord.userId = order.getUserId();
		accountRecord.money = new BigDecimal(0).subtract(order.getCost());
		accountRecord.type = "大马花";
		return accountRecord;
	}

	public static UserAccountRecord createByUserBorrow(UserBorrow borrow) {
		UserAccountRecord accountRecord = new UserAccountRecord();
		accountRecord.userId = borrow.getUserId();
		accountRecord.money = new BigDecimal(0).subtract(borrow.getCost());
		accountRecord.type = "取现";
		return accountRecord;
	}

	public static UserAccountRecord createByUserMonthBill(UserMonthBill bill) {
		UserAccountRecord accountRecord = new UserAccountRecord();
		accountRecord.userId = bill.getUserId();
		accountRecord.money = bill.getPaidCapital();
		accountRecord.type = "还款";
		return accountRecord;
	}

	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setInt(i++, userId);
				ps.setBigDecimal(i++, money);
				ps.setString(i++, type);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static boolean batchCreate(
			final List<UserAccountRecord> repaidRecords, BaseDao baseDao) {
		int[] count = baseDao.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						UserAccountRecord ar = repaidRecords.get(i);
						int num = 1;
						ps.setInt(num++, ar.getUserId());
						ps.setBigDecimal(num++, ar.getMoney());
						ps.setString(num++, ar.getType());
					}

					@Override
					public int getBatchSize() {
						return repaidRecords.size();
					}
				});
		return count.length == repaidRecords.size();
	}

	private static final String INSERT_SQL = "INSERT INTO user_account_record(userId,money,type,createTime) VALUES (?,?,?,NOW())";

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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
