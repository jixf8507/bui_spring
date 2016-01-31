package com.jxf.car.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jxf.car.model.UserAccount;

public class UserAccountExtractor implements ResultSetExtractor<UserAccount> {

	@Override
	public UserAccount extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return UserAccount.create(rs);
		}
		return null;
	}

}
