package com.jxf.car.dao.customer;

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

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getAccountMap(Integer id) {
		return this.get(GET_BY_ID_SQL, new Object[] { id });
	}

	/**
	 * 新增用户账户
	 * 
	 * @param userAccount
	 * @return
	 */
	public Integer create(UserAccount userAccount) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new UserAccountCreator(INSERT_SQL, userAccount), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改用户账户
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(UserAccount userAccount) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				userAccount.getUsableLimit(), userAccount.getWhiteBarLimit(),
				userAccount.getId());
		return count > 0;
	}

	// 根据登录号查找系统用户的SQL
	private static final String GET_BY_ID_SQL = "select a.*,u.`name`,u.mobilePhone,u.idCard from user_account a INNER JOIN user u on a.userId=u.id where a.id=? ";

	private static final String INSERT_SQL = "insert into user_account (userId,usableLimit,curUsableLimit,whiteBarLimit,curWhiteBarLimit,statementDate,repaymentDate,`status`) values (?,?,?,?,?,now(),now(),1)";

	private static final String UPDATE_SQL = "update user_account set usableLimit=?,whiteBarLimit=? where id=?";

}
