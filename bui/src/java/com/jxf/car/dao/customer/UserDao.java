package com.jxf.car.dao.customer;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.creator.UserCreator;
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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserMap(Integer id) {
		return this.get(GET_BY_ID_SQL, new Object[] { id });
	}

	/**
	 * 新增用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	public Integer create(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new UserCreator(INSERT_SQL, user),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改用户账户
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(User user) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL, user.getName(),
				user.getMobilePhone(), user.getIdCard(), user.getId());
		return count > 0;
	}

	public boolean updateStatus(Integer status, String statusDesc, Integer id) {
		int count = this.getJdbcTemplate().update(STATUS_UPDATE_SQL, status,
				statusDesc, id);
		return count > 0;
	}

	// 根据登录号查找系统用户的SQL
	private static final String GET_BY_ID_SQL = "select * from user u  where u.id=? ";

	private static final String INSERT_SQL = "insert into user (name,mobilePhone,idCard,loginPassword,payPassword,lastVisitorTime,`status`,statusDesc) values (?,?,?,?,?,now(),1,'')";

	private static final String UPDATE_SQL = "update user set name=?,mobilePhone=?,idCard=? where id=?";

	private static final String STATUS_UPDATE_SQL = "update user set status=?,statusDesc=? where id=?";

}
