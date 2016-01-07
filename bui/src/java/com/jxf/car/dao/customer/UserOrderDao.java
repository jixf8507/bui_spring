package com.jxf.car.dao.customer;

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

	/**
	 * 分页查找用户订单信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(orderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserOrderMap(Integer id) {
		return this.get(GET_BY_ID_SQL, new Object[] { id });
	}

	private static final String GET_BY_ID_SQL = "select o.*,u.`name` as userName,u.mobilePhone,u.idCard,g.`name` as goodName,g.img,g.price,g.des1,g.des2,g.des3,m.`name` as merchantName,m.address,m.corporation"
			+ " from user_order o LEFT JOIN `user` u on o.userId=u.id LEFT JOIN merchant_goods g on o.goodsId=g.id LEFT JOIN merchant m on g.merchantId=m.id where o.id=? ";

}
