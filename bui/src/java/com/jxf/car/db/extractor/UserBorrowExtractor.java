package com.jxf.car.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jxf.car.model.UserBorrow;

public class UserBorrowExtractor implements ResultSetExtractor<UserBorrow> {

	@Override
	public UserBorrow extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return UserBorrow.create(rs);
		}
		return null;
	}

}
