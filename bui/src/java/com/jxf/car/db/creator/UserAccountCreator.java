package com.jxf.car.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jxf.car.model.UserAccount;

public class UserAccountCreator implements PreparedStatementCreator {

	private String sql;
	private UserAccount userAccount;

	public UserAccountCreator(String sql, UserAccount userAccount) {
		this.sql = sql;
		this.userAccount = userAccount;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, userAccount.getUserId());
		ps.setBigDecimal(2, userAccount.getUsableLimit());
		ps.setBigDecimal(3, userAccount.getUsableLimit());
		ps.setBigDecimal(4, userAccount.getWhiteBarLimit());
		ps.setBigDecimal(5, userAccount.getWhiteBarLimit());	
		return ps;
	}

}
