package com.jxf.car.dao.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
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
		return SysRole.get(id, this);
	}

	/**
	 * 新增系统角色
	 * 
	 * @param role
	 * @return
	 */
	public Integer create(SysRole role) {
		return role.create(this);
	}

	/**
	 * 修改系统角色
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(SysRole role) {
		return role.update(this) > 0;
	}

	/**
	 * 删除系统角色
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Object id) {
		return SysRole.delete(id, this) > 0;
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

}
