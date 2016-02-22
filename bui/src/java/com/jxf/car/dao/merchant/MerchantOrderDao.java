package com.jxf.car.dao.merchant;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

@Repository
public class MerchantOrderDao extends BaseDao {

	@Resource(name = "merchant_order_select_sql")
	private JSONSqlMapping orderSelectSQL;

	public PageResults findMerchantOrderPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(orderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findMerchantderList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(orderSelectSQL, jsonObject);
	}

	public Map<String, Object> get(Integer id) {
		return this.get(getSQL(), new Object[] { id });
	}

	private static final String getSQL() {
		return "select o.*,u.`name` as userName,u.mobilePhone,u.idCard,m.`name` as merchantName,m.code"
				+ " from merchant_order o LEFT JOIN `user` u on o.userId=u.id "
				+ "LEFT JOIN merchant m on o.merchantId=m.id where o.id=? ";
	}

}
