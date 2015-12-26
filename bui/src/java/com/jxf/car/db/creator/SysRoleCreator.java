package com.jxf.car.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jxf.car.model.SysRole;

public class SysRoleCreator implements PreparedStatementCreator {

	private String sql;
	private SysRole sysRole;

	public SysRoleCreator(String sql, SysRole sysRole) {
		this.sql = sql;
		this.sysRole = sysRole;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, sysRole.getRoleName());
		ps.setString(2, sysRole.getRoleRemark());
		return ps;
	}

}
