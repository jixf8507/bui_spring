package com.jxf.car.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jxf.car.model.SysFileUrlsPO;

public class FileUrlsExtractor implements ResultSetExtractor<SysFileUrlsPO> {

	@Override
	public SysFileUrlsPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return SysFileUrlsPO.create(rs);
		}
		return null;
	}

}
