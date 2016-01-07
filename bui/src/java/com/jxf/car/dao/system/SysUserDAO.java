package com.jxf.car.dao.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.creator.SysUserCreator;
import com.jxf.car.db.extractor.SysUserExtractor;
import com.jxf.car.model.SysUser;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author jixf
 * @date 2015年11月21日
 */
@Repository
public class SysUserDAO extends BaseDao {

	/**
	 * 根据登录号查找员工
	 * 
	 * @param code
	 * @return
	 */
	public SysUser findByCode(String code) {
		return this.getJdbcTemplate().query(GET_BY_CODE_SQL,
				new Object[] { code }, new SysUserExtractor());
	}

	public SysUser get(Integer id) {
		return this.getJdbcTemplate().query(GET_BY_ID_SQL, new Object[] { id },
				new SysUserExtractor());
	}

	@Resource(name = "system_user_select_sql")
	private JSONSqlMapping userSelectSQL;

	/**
	 * 分页查找系统员工信息
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
	 * 新增商家工作人员
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public Integer create(SysUser sysUser) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new SysUserCreator(INSERT_SQL, sysUser),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改商家工作人员信息
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(SysUser sysUser) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				sysUser.getCode(), sysUser.getPhone(), sysUser.getName(),
				sysUser.getRoleId(), sysUser.getSex(), sysUser.getId());
		return count > 0;
	}

	/**
	 * 删除商家工作人员
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Object id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	// 根据登录号查找系统员工的SQL
	private static final String GET_BY_CODE_SQL = "SELECT * from sys_user where code=? and `status`=1";
	// 根据登录号查找系统员工的SQL
	private static final String GET_BY_ID_SQL = "SELECT * from sys_user where id=? ";
	// 新增系统员工的SQL
	private static final String INSERT_SQL = "insert into sys_user (code,phone,name,roleId,password,sex) values (?,?,?,?,?,?)";
	// 新增系统员工的SQL
	private static final String UPDATE_SQL = "update sys_user set code=?,phone=?,name=?,roleId=?,sex=? where id=?";
	// 删除系统员工的SQL
	private static final String DELETE_SQL = "update  sys_user set `status`=0 where id=?";

}
