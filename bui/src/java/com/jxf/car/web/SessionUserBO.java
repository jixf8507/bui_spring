package com.jxf.car.web;

import com.jxf.car.model.Merchant;
import com.jxf.car.model.SysUser;

/**
 * 登录用户对象
 * 
 * @author Administrator
 * 
 */
public class SessionUserBO {

	private SysUser sysUser;
	private Merchant merchant;

	public SessionUserBO(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public SessionUserBO(Merchant merchant) {
		this.merchant = merchant;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getName() {
		if (sysUser != null) {
			return sysUser.getName();
		}
		if (merchant != null) {
			return merchant.getName();
		}
		return "";
	}
}
