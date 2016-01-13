package com.jxf.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

/**
 * 
 * @author jixf
 * @date 2015年11月21日
 */
public class SysUser extends BasePO {

	private Integer id;
	private String code;
	private String name;
	private String password;
	private String sex;
	private String phone;
	private Integer status;
	private Integer roleId;

	public SysUser() {

	}

	public static SysUser create(ResultSet rs) throws SQLException {
		SysUser sysUser = new SysUser();
		sysUser.id = rs.getInt("id");
		sysUser.code = rs.getString("code");
		sysUser.name = rs.getString("name");
		sysUser.password = rs.getString("password");
		sysUser.sex = rs.getString("sex");
		sysUser.phone = rs.getString("phone");
		sysUser.status = rs.getInt("status");
		sysUser.roleId = rs.getInt("roleId");
		return sysUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, code);
				ps.setString(2, phone);
				ps.setString(3, name);
				ps.setInt(4, roleId);
				ps.setString(5, "123456");
				ps.setString(6, sex);
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
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.code,
				this.phone, this.name, this.roleId, this.sex, this.id);
	}

	// 根据登录号查找系统员工的SQL
	public static final String GET_BY_CODE_SQL = "SELECT * from sys_user where code=? and `status`=1";
	// 根据登录号查找系统员工的SQL
	public static final String GET_BY_ID_SQL = "SELECT * from sys_user where id=? ";
	// 新增系统员工的SQL
	private static final String INSERT_SQL = "insert into sys_user (code,phone,name,roleId,password,sex) values (?,?,?,?,?,?)";
	// 新增系统员工的SQL
	private static final String UPDATE_SQL = "update sys_user set code=?,phone=?,name=?,roleId=?,sex=? where id=?";
	// 删除系统员工的SQL
	public static final String DELETE_SQL = "update  sys_user set `status`=0 where id=?";

}
