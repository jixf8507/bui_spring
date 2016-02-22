package com.jxf.car.dao.merchant;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.MerchantAccountRecord;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

@Repository
public class MerchantAccountRecordDao extends BaseDao {

	@Resource(name = "merchant_account_record_select_sql")
	private JSONSqlMapping accountRecordSelectSQL;

	public PageResults findMerchantAccountRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(accountRecordSelectSQL,
				jsonObject, pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findMerchantAccountRecordList(
			JSONObject jsonObject) {
		return this
				.findListByJSONSqlMapping(accountRecordSelectSQL, jsonObject);
	}

	public Integer create(MerchantAccountRecord accountRecord) {
		return accountRecord.create(this);
	}
}
