package com.jxf.car.dao.merchant;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import net.sf.json.JSONObject;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.MerchantDrawMoney;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

@Repository
public class MerchantDrawMoneyDao extends BaseDao {

	@Resource(name = "merchant_draw_money_select_sql")
	private JSONSqlMapping merchantDrawMoneySelectSQL;

	public PageResults findMerchantDrawMoneyPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(merchantDrawMoneySelectSQL,
				jsonObject, pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findMerchantDrawMoneyList(
			JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(merchantDrawMoneySelectSQL,
				jsonObject);
	}

	public Map<String, Object> getMerchantDrawMoneyMap(Integer id) {
		return MerchantDrawMoney.get(id, this);
	}

	public Integer create(MerchantDrawMoney drawMoney) {
		return drawMoney.create(this);
	}

}
