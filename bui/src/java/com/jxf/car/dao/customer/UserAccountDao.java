package com.jxf.car.dao.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.creator.UserAccountCreator;
import com.jxf.car.model.UserAccount;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class UserAccountDao extends BaseDao {

	@Resource(name = "user_account_select_sql")
	private JSONSqlMapping userSelectSQL;

	/**
	 * 分页查找系统用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserAccountPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(userSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserAccountList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(userSelectSQL, jsonObject);
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getAccountMap(Integer id) {
		return this.get(UserAccount.GET_BY_ID_SQL, new Object[] { id });
	}

	/**
	 * 新增用户账户
	 * 
	 * @param userAccount
	 * @return
	 */
	public Integer create(UserAccount userAccount) {
		return userAccount.create(this);
	}

	/**
	 * 修改用户账户
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(UserAccount userAccount) {
		return userAccount.update(this) > 0;
	}

}
