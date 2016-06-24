package com.jxf.car.dao.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.UserBorrow;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

@Repository
public class UserBorrowDao extends BaseDao {

	@Resource(name = "user_borrow_select_sql")
	private JSONSqlMapping borrowSelectSQL;

	public PageResults findUserBorrowPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(borrowSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserBorrowList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(borrowSelectSQL, jsonObject);
	}

	public Map<String, Object> getUserBorrowMap(Integer id) {
		return UserBorrow.get(id, this);
	}

	public UserBorrow getUserBorrow(Integer id) {
		return UserBorrow.getUserBorrow(id, this);
	}

	public boolean submitCheckOrder(UserBorrow userBorrow) {
		return userBorrow.updateStatus(this) > 0;
	}

}
