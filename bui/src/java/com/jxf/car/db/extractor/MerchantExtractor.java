package com.jxf.car.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jxf.car.model.Merchant;

public class MerchantExtractor implements ResultSetExtractor<Merchant> {

	@Override
	public Merchant extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return Merchant.create(rs);
		}
		return null;
	}
}