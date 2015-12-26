package com.jxf.car.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysRole extends BasePO {

	private Integer id;
	private String roleName;
	private String roleRemark;

	public SysRole() {

	}

	public SysRole(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.roleName = rs.getString("roleName");
		this.roleRemark = rs.getString("roleRemark");
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

}
