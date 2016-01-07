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
import com.jxf.car.service.customer.UserService;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;
import com.jxf.common.tools.StringTools;

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

		PageResults pageResults = userService.findUserPage(jsonObject,
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
