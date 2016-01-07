package com.jxf.car.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jxf.car.model.Merchant;

public class MerchantCreator implements PreparedStatementCreator {

	private String sql;
	private Merchant merchant;

	public MerchantCreator(String sql, Merchant merchant) {
		this.sql = sql;
		this.merchant = merchant;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, merchant.getName());
		ps.setString(2, merchant.getAddress());
		ps.setString(3, merchant.getDes());
		ps.setString(4, merchant.getCorporation());
		ps.setString(5, merchant.getMobilePhone());
		return ps;
	}

}
