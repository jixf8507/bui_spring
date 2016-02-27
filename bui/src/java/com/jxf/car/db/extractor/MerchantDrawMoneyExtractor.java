package com.jxf.car.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jxf.car.model.MerchantDrawMoney;

public class MerchantDrawMoneyExtractor implements ResultSetExtractor<MerchantDrawMoney> {

	@Override
	public MerchantDrawMoney extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return MerchantDrawMoney.create(rs);
		}
		return null;
	}
}