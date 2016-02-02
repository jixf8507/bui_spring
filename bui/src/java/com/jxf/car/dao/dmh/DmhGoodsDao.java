package com.jxf.car.dao.dmh;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import net.sf.json.JSONObject;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.DmhGoods;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

@Repository
public class DmhGoodsDao extends BaseDao {

	@Resource(name = "dmh_goods_select_sql")
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
		return DmhGoods.get(id, this);
	}

	public Integer create(DmhGoods dmhGoods) {
		return dmhGoods.create(this);
	}

	public boolean update(DmhGoods dmhGoods) {
		return dmhGoods.update(this) > 0;
	}

	public boolean delete(Object id) {
		return DmhGoods.delete(id, this) > 0;
	}
}
