package com.jxf.car.controller.merchant;

import javax.servlet.http.HttpServletRequest;
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
import com.jxf.car.model.MerchantGoods;
import com.jxf.car.service.merchant.MerchantGoodsService;
import com.jxf.car.web.MSG;
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
@RequestMapping("/merchant/goods")
public class MerchantGoodsController extends BaseController {

	@Autowired
	private MerchantGoodsService merchantGoodsService;

	@RequestMapping("list")
	public String list() {
		return "merchant/merchantGoodsList";
	}

	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "merchant/merchantGoodsAdd";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = merchantGoodsService.findMerchantGoodsPage(
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
		merchantGoodsService.excelMerchantGoods(response, jsonObject);
		return null;
	}
	@RequestMapping("edite")
	public String edite(Integer id, Model model) {
		model.addAttribute("merchantGoods",
				merchantGoodsService.getMerchantGoodsMap(id));
		return "merchant/merchantGoodsEdite";
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
	public MSG submit(MerchantGoods merchantGoods) throws Exception {
		if (merchantGoods.getId() == null) {
			return merchantGoodsService.createMerchantGoods(merchantGoods);
		} else {
			return merchantGoodsService.updateMerchantGoods(merchantGoods);
		}
	}

}
