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
public class UserMonthBillDao extends BaseDao {

	@Resource(name = "user_month_bill_select_sql")
	private JSONSqlMapping billSelectSQL;

	/**
	 * 分页查找系统用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserMonthBillPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(billSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}
	
	public List<Map<String, Object>> findUserMonthBillList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(billSelectSQL, jsonObject);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserMonthBillMap(Integer id) {
		return this.get(GET_BY_ID_SQL, new Object[] { id });
	}

	private static final String GET_BY_ID_SQL = "select b.*,u.`name` as userName,u.mobilePhone,u.idCard from user_month_bill b LEFT JOIN `user` u on b.userId=u.id where b.id=? ";

}
