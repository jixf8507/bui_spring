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
import com.jxf.car.model.UserOrder;
import com.jxf.car.service.customer.UserOrderService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/user/order")
public class UserOrderController extends BaseController {

	@Autowired
	private UserOrderService userOrderService;

	@RequestMapping("list")
	public String list() {
		return "user/userOrderList";
	}

	@RequestMapping("checkList")
	public String checkList() {
		return "user/userOrderCheckList";
	}

	@RequestMapping("buyList")
	public String buyList() {
		return "user/userOrderBuyList";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData,
				this.getSesseionUser(session));
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = userOrderService.findUserOrderPage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData,
				this.getSesseionUser(session));
		userOrderService.excelUserOrder(response, jsonObject);
		return null;
	}

	@RequestMapping("detail")
	public String detail(Integer id, Model model) {
		model.addAttribute("userOrder", userOrderService.getUserOrderMap(id));
		return "user/userOrderDetail";
	}

	@RequestMapping("checkDetail")
	public String checkDetail(Integer id, Model model) {
		model.addAttribute("userOrder", userOrderService.getUserOrderMap(id));
		return "user/userOrderCheckDetail";
	}

	@RequestMapping("submitCheck.htm")
	@ResponseBody
	public MSG submitCheck(UserOrder userOrder, HttpSession session)
			throws Exception {
		userOrder.setCheckMen(this.getSesseionUser(session).getName());
		return userOrderService.submitCheckOrder(userOrder);
	}

	@RequestMapping("updateBuyStatus.htm")
	@ResponseBody
	public MSG updateBuyStatus(Integer status, String ids) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(ids);
		return userOrderService.batchUpdateStatus(status, jsonArray);
	}

}
