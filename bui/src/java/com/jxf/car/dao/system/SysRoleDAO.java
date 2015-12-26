package com.jxf.car.dao.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.creator.SysRoleCreator;
import com.jxf.car.db.extractor.SysRoleExtractor;
import com.jxf.car.model.SysRole;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author jixf
 * @date 2015年11月24日
 */
@Repository
public class SysRoleDAO extends BaseDao {

	/**
	 * 获取系统角色
	 * 
	 * @param id
	 * @return
	 */
	public SysRole get(Integer id) {
		return this.getJdbcTemplate().query(GET_BY_ID_SQL, new Object[] { id },
				new SysRoleExtractor());
	}

	/**
	 * 新增系统角色
	 * 
	 * @param role
	 * @return
	 */
	public Integer create(SysRole role) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new SysRoleCreator(INSERT_SQL, role),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改系统角色
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(SysRole role) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				role.getRoleName(), role.getRoleRemark(), role.getId());
		return count > 0;
	}

	/**
	 * 删除系统角色
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Object id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	@Resource(name = "system_role_select_sql")
	private JSONSqlMapping roleSelectSQL;

	/**
	 * 分页查找系统角色信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRolePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(roleSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查询系统角色列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findRoles(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(roleSelectSQL, jsonObject);
	}

	// 根据登录号查找系统员工的SQL
	private static final String GET_BY_ID_SQL = "SELECT * from sys_role where id=? ";
	// 新增系统员工的SQL
	private static final String INSERT_SQL = "insert into sys_role (roleName,roleRemark) values (?,?)";
	// 新增系统员工的SQL
	private static final String UPDATE_SQL = "update sys_role set roleName=?,roleRemark=? where id=?";
	// 删除系统员工的SQL
	private static final String DELETE_SQL = "delete from  sys_role where id=?";

}
