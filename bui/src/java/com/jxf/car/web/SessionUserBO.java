package com.jxf.car.web;

import com.jxf.car.model.SysUser;

/**
 * 登录用户对象
 * 
 * @author Administrator
 * 
 */
public class SessionUserBO {

	private SysUser sysUser;

	public SessionUserBO(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}
