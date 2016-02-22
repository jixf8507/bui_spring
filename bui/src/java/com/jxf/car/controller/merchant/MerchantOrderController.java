package com.jxf.car.controller.merchant;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.controller.BaseController;
import com.jxf.car.service.merchant.MerchantOrderService;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;

@Controller
@RequestMapping("/merchant/order")
public class MerchantOrderController extends BaseController {

	@Autowired
	private MerchantOrderService merchantOrderService;

	@RequestMapping("list")
	public String list() {
		return "merchant/merchantOrderList";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData,
				this.getSesseionUser(session));
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = merchantOrderService.findMerchantOrderPage(
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
		merchantOrderService.excelMerchantOrder(response, jsonObject);
		return null;
	}

	@RequestMapping("detail")
	public String detail(Integer id, Model model) {
		model.addAttribute("merchantOrder",
				merchantOrderService.getMerchantOrderMap(id));
		return "merchant/merchantOrderDetail";
	}

}
