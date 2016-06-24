package com.jxf.car.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.controller.BaseController;
import com.jxf.car.service.customer.UserBillDetailService;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;
import com.jxf.common.tools.StringTools;

@Controller
@RequestMapping("/user/billDetail")
public class UserBillDetailController extends BaseController {

	@Autowired
	private UserBillDetailService billDetailService;

	@RequestMapping("list")
	public String list(Model model, HttpServletRequest request) {
		model.addAttribute("monthBillUuid",
				StringTools.decodeMethod(request.getParameter("monthBillUuid")));
		model.addAttribute("orderId",
				StringTools.decodeMethod(request.getParameter("orderId")));
		model.addAttribute("orderTable",
				StringTools.decodeMethod(request.getParameter("orderTable")));
		return "user/userBillDetailList";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = billDetailService.findUserBillDetailPage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		billDetailService.excelUserBillDetail(response, jsonObject);
		return null;
	}

}
