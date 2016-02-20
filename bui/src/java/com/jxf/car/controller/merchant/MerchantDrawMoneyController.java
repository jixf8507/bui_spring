package com.jxf.car.controller.merchant;

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
import com.jxf.car.model.MerchantDrawMoney;
import com.jxf.car.service.merchant.MerchantDrawMoneyService;
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
@RequestMapping("/merchant/drawMoney")
public class MerchantDrawMoneyController extends BaseController {

	@Autowired
	private MerchantDrawMoneyService drawMoneyService;

	@RequestMapping("list")
	public String list() {
		return "merchant/merchantDrawMoneyList";
	}

	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "merchant/merchantDrawMoneyAdd";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData,
				this.getSesseionUser(session));
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = drawMoneyService.findMerchantDrawMoneyPage(
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
		drawMoneyService.excelMerchantDrawMoney(response, jsonObject);
		return null;
	}

	@RequestMapping("detail")
	public String detail(Integer id, Model model, HttpSession session) {
		model.addAttribute("drawMoney",
				drawMoneyService.getMerchantDrawMoneyMap(id));
		return "merchant/merchantDrawMoneyDetail";
	}

	@RequestMapping("submit.htm")
	@ResponseBody
	public MSG submit(MerchantDrawMoney drawMoney, HttpSession session)
			throws Exception {
		if (this.getSesseionUser(session).getMerchant() == null) {
			return MSG.createErrorMSG(1, "商家信息获取失败");
		}
		drawMoney.setMerchantId(this.getSesseionUser(session).getMerchant()
				.getId());
		return drawMoneyService.createMerchantDrawMoney(drawMoney);
	}

}
