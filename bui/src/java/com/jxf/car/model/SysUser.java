package com.jxf.car.model;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	public SysUser(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.code = rs.getString("code");
		this.name = rs.getString("name");
		this.password = rs.getString("password");
		this.sex = rs.getString("sex");
		this.phone = rs.getString("phone");
		this.status = rs.getInt("status");
		this.roleId = rs.getInt("roleId");
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

}
