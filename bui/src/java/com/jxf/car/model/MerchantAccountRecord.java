package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

public class MerchantAccountRecord {

	private Integer id;
	private Integer merchantId;
	private BigDecimal price;
	private String type;
	
	public static MerchantAccountRecord createForDrawMoney(Integer merchantId,BigDecimal price){
		MerchantAccountRecord accountRecord = new MerchantAccountRecord();
		accountRecord.merchantId=merchantId;
		accountRecord.price=new BigDecimal(0).subtract(price);
		accountRecord.type="提现";
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
				ps.setInt(i++, merchantId);
				ps.setBigDecimal(i++, price);
				ps.setString(i++, type);			
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	private static final String INSERT_SQL = "INSERT INTO merchant_account_record (merchantId,price,type,createTime) values (?,?,?,NOW())";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
