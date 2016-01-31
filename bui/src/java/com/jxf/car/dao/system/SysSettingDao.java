package com.jxf.car.dao.system;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;

@Repository
public class SysSettingDao extends BaseDao {

	public List<Map<String, Object>> findList(String key) {
		return this.findListBySQL(SELECT_SQL, new Object[] { key });
	}

	public Map<String, Object> findOne(String key) {
		List<Map<String, Object>> list = this.findList(key);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public BigDecimal findSysInterest() {
		Map<String, Object> map = this.findOne("INTEREST");
		try {
			return new BigDecimal(map.get("value") + "");
		} catch (Exception e) {

		}
		return null;
	}

	private static final String SELECT_SQL = "select * from sys_setting where `key` =?";

}
