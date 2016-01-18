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

	/**
	 * 分页查找系统用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findMerchantGoodsPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(goodsSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findMerchantGoodsList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(goodsSelectSQL, jsonObject);
	}

	public Map<String, Object> getMerchantGoodsMap(Integer id) {
		return this.get(DmhGoods.GET_BY_ID_SQL, new Object[] { id });
	}

	/**
	 * 新增用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	public Integer create(DmhGoods dmhGoods) {
		return dmhGoods.create(this);
	}

	/**
	 * 修改用户账户
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(DmhGoods dmhGoods) {
		return dmhGoods.update(this) > 0;
	}

	public boolean delete(Object id) {
		return getJdbcTemplate().update(DmhGoods.DELETE_ID_SQL, id) > 0;
	}
}
