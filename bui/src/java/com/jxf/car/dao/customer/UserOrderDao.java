package com.jxf.car.dao.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.UserOrder;
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
		return UserOrder.get(id, this);
	}

	public UserOrder getUserOrder(Integer id) {
		return UserOrder.getUserOrder(id, this);
	}

	public boolean submitCheckOrder(UserOrder userOrder) {
		return userOrder.updateStatus(this) > 0;
	}

}
