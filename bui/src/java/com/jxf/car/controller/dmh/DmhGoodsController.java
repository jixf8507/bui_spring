package com.jxf.car.controller.dmh;

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
import com.jxf.car.model.DmhGoods;
import com.jxf.car.service.dmh.DmhGoodsService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;

@Controller
@RequestMapping("/dmh/goods")
public class DmhGoodsController extends BaseController {

	@Autowired
	private DmhGoodsService dmhGoodsService;

	@RequestMapping("list")
	public String list() {
		return "dmh/dmhGoodsList";
	}

	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "dmh/dmhGoodsAdd";
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = dmhGoodsService.findGoodsPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		dmhGoodsService.excelGoods(response, jsonObject);
		return null;
	}

	@RequestMapping("edite")
	public String edite(Integer id, Model model) {
		model.addAttribute("dmhGoods", dmhGoodsService.getGoodsMap(id));
		return "dmh/dmhGoodsEdite";
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
	public MSG submit(DmhGoods dmhGoods) throws Exception {
		if (dmhGoods.getId() == null) {
			return dmhGoodsService.createGoods(dmhGoods);
		} else {
			return dmhGoodsService.updateGoods(dmhGoods);
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public MSG delete(String ids) {
		JSONArray jsonArray = JSONArray.fromObject(ids);
		return dmhGoodsService.deleteGoods(jsonArray);
	}
}
