package com.jxf.car.controller.merchant;

import java.util.List;
import java.util.Map;

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
import com.jxf.car.model.Merchant;
import com.jxf.car.service.merchant.MerchantService;
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
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

	@Autowired
	private MerchantService merchantService;

	@RequestMapping("list")
	public String list() {
		return "merchant/merchantList";
	}

	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "merchant/merchantAdd";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData,
				this.getSesseionUser(session));
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = merchantService.findMerchantPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData,
				this.getSesseionUser(session));
		merchantService.excelMerchant(response, jsonObject);
		return null;
	}

	@RequestMapping("ajaxList")
	@ResponseBody
	public List<Map<String, Object>> ajaxList(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData,
				this.getSesseionUser(session));
		return merchantService.findMerchantList(jsonObject);
	}

	@RequestMapping("edite")
	public String edite(Integer id, Model model) {
		model.addAttribute("merchant", merchantService.getMerchantMap(id));
		return "merchant/merchantEdite";
	}

	@RequestMapping("submit.htm")
	@ResponseBody
	public MSG submit(Merchant merchant) throws Exception {
		if (merchant.getId() == null) {
			return merchantService.createMerchant(merchant);
		} else {
			return merchantService.updateMerchant(merchant);
		}
	}

}
