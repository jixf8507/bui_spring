package com.jxf.car.dao.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class UserOrderDao extends BaseDao {

	@Resource(name = "user_order_select_sql")
	private JSONSqlMapping orderSelectSQL;

	public PageResults findUserOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(orderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserOrderList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(orderSelectSQL, jsonObject);
	}

	public Map<String, Object> getUserOrderMap(Integer id) {
		return this.get(GET_BY_ID_SQL, new Object[] { id });
	}

	private static final String GET_BY_ID_SQL = "select o.*,u.`name` as userName,u.mobilePhone,u.idCard,g.`name` as goodName,g.img,g.price,g.des1,g.des2,g.des3,m.`name` as merchantName,m.address,m.corporation"
			+ " from user_order o LEFT JOIN `user` u on o.userId=u.id LEFT JOIN merchant_goods g on o.goodsId=g.id LEFT JOIN merchant m on g.merchantId=m.id where o.id=? ";

}
