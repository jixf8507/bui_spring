package com.jxf.car.dao.merchant;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.MerchantGoods;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class MerchantGoodsDao extends BaseDao {

	@Resource(name = "merchant_goods_select_sql")
	private JSONSqlMapping goodsSelectSQL;

	public PageResults findMerchantGoodsPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(goodsSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findMerchantGoodsList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(goodsSelectSQL, jsonObject);
	}

	public Map<String, Object> getMerchantGoodsMap(Integer id) {
		return MerchantGoods.get(id, this);
	}

	public Integer create(MerchantGoods merchantGoods) {
		return merchantGoods.create(this);
	}

	public boolean update(MerchantGoods merchantGoods) {
		return merchantGoods.update(this) > 0;
	}

}
