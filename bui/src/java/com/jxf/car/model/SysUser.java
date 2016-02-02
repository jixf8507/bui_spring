package com.jxf.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.SysUserExtractor;

/**
 * 系统用户
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
				int i = 1;
				ps.setString(i++, code);
				ps.setString(i++, phone);
				ps.setString(i++, name);
				ps.setInt(i++, roleId);
				ps.setString(i++, "123456");
				ps.setString(i++, sex);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public int update(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.code,
				this.phone, this.name, this.roleId, this.sex, this.id);
	}

	public int updatePassword(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_PASSWORD_SQL,
				this.password, this.id);
	}

	public static SysUser findByCode(String code, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_BY_CODE_SQL,
				new Object[] { code }, new SysUserExtractor());
	}

	public static SysUser get(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_BY_ID_SQL,
				new Object[] { id }, new SysUserExtractor());
	}

	public static int delete(Object id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(DELETE_SQL, id);
	}

	public boolean checkPassword(String password) {
		if (this.password.equals(password)) {
			return true;
		}
		return false;
	}

	private static final String GET_BY_CODE_SQL = "SELECT * from sys_user where code=? and `status`=1";
	private static final String GET_BY_ID_SQL = "SELECT * from sys_user where id=? ";
	private static final String INSERT_SQL = "insert into sys_user (code,phone,name,roleId,password,sex) values (?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "update sys_user set code=?,phone=?,name=?,roleId=?,sex=? where id=?";
	private static final String UPDATE_PASSWORD_SQL = "update sys_user set password=? where id=?";
	private static final String DELETE_SQL = "update  sys_user set `status`=0 where id=?";

}
