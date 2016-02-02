package com.jxf.car.dao.merchant;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.Merchant;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class MerchantDao extends BaseDao {

	@Resource(name = "merchant_merchant_select_sql")
	private JSONSqlMapping merchantSelectSQL;

	public PageResults findMerchantPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(merchantSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findMerchantList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(merchantSelectSQL, jsonObject);
	}

	public Map<String, Object> getMerchantMap(Integer id) {
		return Merchant.get(id, this);
	}

	public Integer create(Merchant merchant) {
		return merchant.create(this);
	}

	public boolean update(Merchant merchant) {
		return merchant.update(this) > 0;
	}

}
