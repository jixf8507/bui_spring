package com.jxf.car.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jxf.car.model.SysUser;

public class SysUserCreator implements PreparedStatementCreator {

	private String sql;
	private SysUser sysUser;

	public SysUserCreator(String sql, SysUser sysUser) {
		this.sql = sql;
		this.sysUser = sysUser;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, sysUser.getCode());
		ps.setString(2, sysUser.getPhone());
		ps.setString(3, sysUser.getName());
		ps.setInt(4, sysUser.getRoleId());
		ps.setString(5, "123456");
		ps.setString(6, sysUser.getSex());
		return ps;
	}
}
