package com.jxf.car.dao.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.User;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class UserDao extends BaseDao {

	@Resource(name = "user_user_select_sql")
	private JSONSqlMapping userSelectSQL;

	/**
	 * 分页查找系统用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(userSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(userSelectSQL, jsonObject);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserMap(Integer id) {
		return this.get(User.GET_BY_ID_SQL, new Object[] { id });
	}

	/**
	 * 新增用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	public Integer create(User user) {
		return user.create(this);
	}

	/**
	 * 修改用户账户
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(User user) {
		return user.update(this) > 0;
	}

	public boolean updateStatus(Integer status, String statusDesc, Integer id) {
		User user = User.createUser(status, statusDesc, id);
		return user.updateStatus(this) > 0;
	}

}
