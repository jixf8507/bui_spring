package com.jxf.car.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jxf.car.model.MerchantGoods;

public class MerchantGoodsCreator implements PreparedStatementCreator {

	private String sql;
	private MerchantGoods merchantGoods;

	public MerchantGoodsCreator(String sql, MerchantGoods merchantGoods) {
		this.sql = sql;
		this.merchantGoods = merchantGoods;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, merchantGoods.getMerchantId());
		ps.setString(2, merchantGoods.getName());
		ps.setBigDecimal(3, merchantGoods.getPrice());
		ps.setString(4, merchantGoods.getImg());
		ps.setString(5, merchantGoods.getDes1());
		ps.setString(6, merchantGoods.getDes2());
		ps.setString(7, merchantGoods.getDes3());
		return ps;
	}

}
