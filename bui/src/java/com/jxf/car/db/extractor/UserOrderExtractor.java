package com.jxf.car.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jxf.car.model.UserOrder;

public class UserOrderExtractor implements ResultSetExtractor<UserOrder> {

	@Override
	public UserOrder extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return UserOrder.create(rs);
		}
		return null;
	}

}
