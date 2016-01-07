package com.jxf.car.controller.customer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.controller.BaseController;
import com.jxf.car.service.customer.UserMonthBillService;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;
import com.jxf.common.tools.StringTools;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/user/monthBill")
public class UserMonthBillController extends BaseController {

	@Autowired
	private UserMonthBillService userMonthBillService;

	@RequestMapping("list")
	public String list() {
		return "user/userMonthBillList";
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

		PageResults pageResults = userMonthBillService.findUserMonthBillPage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	private JSONObject getJsonPara(String paraData, HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		return jsonObject;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		userMonthBillService.excelUserMonthBill(response, jsonObject);
		return null;
	}

	@RequestMapping("detail")
	public String detail(Integer id, Model model) {
		model.addAttribute("userMonthBill",
				userMonthBillService.getUserMonthBillMap(id));
		return "user/userMonthBillDetail";
	}

}
