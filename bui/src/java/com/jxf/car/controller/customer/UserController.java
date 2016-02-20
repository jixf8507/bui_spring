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
import com.jxf.car.service.customer.UserService;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;

/**
 * 用户信息审核
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("checkList")
	public String checkList() {
		return "user/userCheckList";
	}
	
	@RequestMapping("list")
	public String list() {
		return "user/userList";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = userService.findUserPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		userService.excelUser(response, jsonObject);
		return null;
	}

	@RequestMapping("check")
	public String check(Integer id, Model model) {
		model.addAttribute("user", userService.getUserMap(id));
		return "user/userCheck";
	}

	@RequestMapping("detail")
	public String detail(Integer id, Model model) {
		model.addAttribute("user", userService.getUserMap(id));
		return "user/userDetail";
	}

}
