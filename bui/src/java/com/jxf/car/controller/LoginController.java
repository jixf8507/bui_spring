package com.jxf.car.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.model.Merchant;
import com.jxf.car.model.SysUser;
import com.jxf.car.service.merchant.MerchantService;
import com.jxf.car.service.system.SysUserService;
import com.jxf.car.web.MSG;
import com.jxf.car.web.SessionUserBO;
import com.jxf.common.SysConfig;

/**
 * 
 * @author jixf
 * @date 2015年11月21日
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private MerchantService merchantService;

	@RequestMapping("login")
	public String login(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "login";
	}

	/**
	 * 提交登录
	 * 
	 * @param request
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping("submit")
	@ResponseBody
	public MSG submit(HttpSession session, String username, String password,
			Model model) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return MSG.createErrorMSG(1, "用户名或密码不能为空！");
		}
		SysUser sysUser = sysUserService.findUserByCode(username);
		if (sysUser == null || !sysUser.checkPassword(password)) {
			return MSG.createErrorMSG(1, "用户名或密码不正确！");
		}
		session.setAttribute(SysConfig.SESSION_USER, new SessionUserBO(sysUser));
		return MSG.createSuccessMSG();
	}

	/**
	 * 提交登录
	 * 
	 * @param request
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping("merchant/submit")
	@ResponseBody
	public MSG merchantSubmit(HttpSession session, String username,
			String password, Model model) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return MSG.createErrorMSG(1, "用户名或密码不能为空！");
		}
		Merchant merchant = merchantService.getMerchantMap(username);
		if (merchant == null || !merchant.checkPassword(password)) {
			return MSG.createErrorMSG(1, "用户名或密码不正确！");
		}
		session.setAttribute(SysConfig.SESSION_USER,
				new SessionUserBO(merchant));
		return MSG.createSuccessMSG();
	}

	@RequestMapping("out")
	public String out(HttpSession session) {
		session.removeAttribute(SysConfig.SESSION_USER);
		return "login";
	}

}
