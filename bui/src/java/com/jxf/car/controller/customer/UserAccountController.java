package com.jxf.car.controller.customer;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
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
import com.jxf.common.tools.StringTools;

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

	/**
	 * 分页加载系统用户信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = accountService.findUserAccountPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	private JSONObject getJsonPara(String paraData, HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		return jsonObject;
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

	/**
	 * 提交保存员工信息
	 * 
	 * @param sysUser
	 * @return
	 * @throws Exception
	 */
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
