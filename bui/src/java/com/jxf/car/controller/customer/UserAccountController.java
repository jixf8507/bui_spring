package com.jxf.car.controller.customer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.controller.BaseController;
import com.jxf.car.model.UserAccount;
import com.jxf.car.service.customer.UserAccountService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;

/**
 * 用户信息管理
 * 
 * @author jixf
 * @date 2015年11月24日
 */
@Controller
@RequestMapping("/user/account")
public class UserAccountController extends BaseController {

	@Autowired
	private UserAccountService accountService;

	@RequestMapping("page")
	public String page() {
		return "user/userAccount";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = accountService.findUserAccountPage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		accountService.excelUserAccount(response, jsonObject);
		return null;
	}

	@RequestMapping("edite")
	public String edite(Integer id, Model model) {
		model.addAttribute("userAccount", accountService.getAccountMap(id));
		return "user/accountEdite";
	}

	@RequestMapping("detail")
	public String detail(Integer id, Model model) {
		model.addAttribute("userAccount", accountService.getAccountMap(id));
		return "user/accountDetail";
	}

	@RequestMapping("submit.htm")
	@ResponseBody
	public MSG submit(UserAccount userAccount) throws Exception {
		if (userAccount.getId() == null) {
			return accountService.createUserAccount(userAccount);
		} else {
			return accountService.updateUserAccount(userAccount);
		}
	}

}
