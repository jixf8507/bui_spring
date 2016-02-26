package com.jxf.car.dao.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.UserAccountRecord;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

@Repository
public class UserAccountRecordDao extends BaseDao {

	@Resource(name = "user_account_record_select_sql")
	private JSONSqlMapping accountRecordSelectSQL;

	public PageResults findUserAccountRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(accountRecordSelectSQL,
				jsonObject, pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserAccountRecordList(
			JSONObject jsonObject) {
		return this
				.findListByJSONSqlMapping(accountRecordSelectSQL, jsonObject);
	}

	public Integer create(UserAccountRecord accountRecord) {
		return accountRecord.create(this);
	}

	public boolean batchCreate(List<UserAccountRecord> repaidRecords) {
		return UserAccountRecord.batchCreate(repaidRecords, this);
	}

}
