package com.jxf.car.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jxf.car.model.SysUser;

public class SysUserExtractor implements ResultSetExtractor<SysUser> {

	@Override
	public SysUser extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new SysUser(rs);
		}
		return null;
	}
}
