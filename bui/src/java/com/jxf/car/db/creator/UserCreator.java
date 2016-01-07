package com.jxf.car.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jxf.car.model.User;
import com.jxf.common.SysConfig;

public class UserCreator implements PreparedStatementCreator {

	private String sql;
	private User user;

	public UserCreator(String sql, User user) {
		this.sql = sql;
		this.user = user;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getName());
		ps.setString(2, user.getMobilePhone());
		ps.setString(3, user.getIdCard());
		ps.setString(4, SysConfig.PASSWORD);
		ps.setString(5, SysConfig.PASSWORD);
		return ps;
	}

}
