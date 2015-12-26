package com.jxf.car.dao.system;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.omg.CORBA.INTERNAL;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;

/**
 * 
 * @author jixf
 * @date 2015年11月23日
 */
@Repository
public class SysRoleMenuDAO extends BaseDao {

	/**
	 * 根据角色查找系统菜单列表
	 * 
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	public List<Map<String, Object>> findMenusByRoleId(Integer roleId) {
		return this.findListBySQL(SELECT_BY_ROLE_SQL, new Object[] { roleId });
	}

	/**
	 * 查询所有系统菜单
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findMenus() {
		return this.findListBySQL(SELECT_LIST_SQL, null);
	}

	/**
	 * 查询角色菜单列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> findRoleMenus(Integer roleId) {
		return this.findListBySQL(SELECT_ROLES_MENUS_SQL,
				new Object[] { roleId });
	}

	/**
	 * 删除系统角色菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public boolean delteByRoleId(Integer roleId) {
		int count = this.getJdbcTemplate().update(DELETE_BY_ROLEID_SQL, roleId);
		return count > 0;
	}

	/**
	 * 批量创建系统角色菜单
	 * 
	 * @param roleId
	 *            角色ID
	 * @param jsonArray
	 *            菜单列表
	 * @return
	 */
	public boolean batchCreate(final Integer roleId, final JSONArray jsonArray) {
		int[] count = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, roleId);
						ps.setObject(2, jsonArray.get(i));
					}

					@Override
					public int getBatchSize() {
						return jsonArray.size();
					}
				});
		return count.length == jsonArray.size();
	}

	// 根据ID查找资产的SQL
	private static final String SELECT_BY_ROLE_SQL = "select m.* from sys_menu m,sys_role_menu sr where  sr.menuId=m.id and sr.roleId=? order by m.`level`,m.pid,m.sort";

	// 根据ID查找资产的SQL
	private static final String SELECT_LIST_SQL = "select m.* from sys_menu m order by m.`level`,m.pid,m.sort";

	// 根据ID查找资产的SQL
	private static final String SELECT_ROLES_MENUS_SQL = "select id,roleId,menuId from sys_role_menu where roleId=?";

	// 根据角色删除系统菜单的SQL
	private static final String DELETE_BY_ROLEID_SQL = "delete from sys_role_menu where roleId=?";

	// 新增系统角色菜单的SQL
	private static final String INSERT_SQL = "insert into sys_role_menu (roleId,menuId) values (?,?)";
}
