package com.jxf.car.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.bui.BuiMenu;
import com.jxf.car.model.SysUser;
import com.jxf.car.service.system.SysUserService;
import com.jxf.car.web.MSG;
import com.jxf.car.web.SessionUserBO;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

	@Resource
	private SysUserService sysUserService;

	@RequestMapping("index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "index";
	}

	/**
	 * 获取用户菜单
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("loadUserMenus")
	@ResponseBody
	public BuiMenu loadUserMenus(HttpSession session, HttpServletRequest request) {
		String contextPath = request.getContextPath();
		SessionUserBO sesseionUser = this.getSesseionUser(session);
		return sysUserService.getConfigMunesByRoleId(sesseionUser.getSysUser()
				.getRoleId(), contextPath);
	}

	@RequestMapping("updatePwd.htm")
	@ResponseBody
	public MSG updatePwd(String ypassword, String password, HttpSession session)
			throws Exception {
		SessionUserBO sessionUserBO = this.getSesseionUser(session);
		SysUser sysUser = sessionUserBO.getSysUser();
		if (sysUser.checkPassword(ypassword)) {
			sysUser.setPassword(password);
			return sysUserService.updateUserPassword(sysUser);
		}
		return MSG.createErrorMSG(1, "原密码输入错误");
	}

}
