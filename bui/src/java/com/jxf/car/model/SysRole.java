package com.jxf.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.SysRoleExtractor;

public class SysRole extends BasePO {

	private Integer id;
	private String roleName;
	private String roleRemark;

	public SysRole() {

	}

	public static SysRole create(ResultSet rs) throws SQLException {
		SysRole role = new SysRole();
		role.id = rs.getInt("id");
		role.roleName = rs.getString("roleName");
		role.roleRemark = rs.getString("roleRemark");
		return role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	/**
	 * 新增系统角色
	 * 
	 * @param role
	 * @return
	 */
	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, roleName);
				ps.setString(i++, roleRemark);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改系统角色
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public int update(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.roleName,
				this.roleRemark, this.id);
	}

	public static SysRole get(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_BY_ID_SQL,
				new Object[] { id }, new SysRoleExtractor());
	}

	public static int delete(Object id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(DELETE_SQL, id);
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
