package com.jxf.car.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jxf.car.model.SysRole;

public class SysRoleExtractor implements ResultSetExtractor<SysRole> {

	@Override
	public SysRole extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return SysRole.create(rs);
		}
		return null;
	}

}
