package com.jxf.car.controller.sj;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.bui.BuiMenu;
import com.jxf.car.controller.BaseController;
import com.jxf.car.model.Merchant;
import com.jxf.car.service.merchant.MerchantService;
import com.jxf.car.web.MSG;
import com.jxf.car.web.SessionUserBO;
import com.jxf.common.SysConfig;

@Controller
@RequestMapping("/sj/home")
public class HomeController extends BaseController {

	@Resource
	private MerchantService merchantService;

	@RequestMapping("index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "sj/index";
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
		return merchantService.getConfigMunes(contextPath);
	}

	@RequestMapping("out")
	public String out(HttpSession session) {
		session.removeAttribute(SysConfig.SESSION_USER);
		return "sj/login";
	}

	@RequestMapping("updatePwd.htm")
	@ResponseBody
	public MSG updatePwd(String ypassword, String password, HttpSession session)
			throws Exception {
		SessionUserBO sessionUserBO = this.getSesseionUser(session);
		Merchant merchant = sessionUserBO.getMerchant();
		if (merchant.checkPassword(ypassword)) {
			merchant.setPassword(password);
			return merchantService.updateMerchantPassword(merchant);
		}
		return MSG.createErrorMSG(1, "原密码输入错误");
	}

}
