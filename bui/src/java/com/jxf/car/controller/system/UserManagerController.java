package com.jxf.car.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.controller.BaseController;
import com.jxf.car.model.SysUser;
import com.jxf.car.service.system.SysUserService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;

/**
 * 系统用户管理控制
 * 
 * @author jixf
 * @date 2015年11月23日
 */
@Controller
@RequestMapping("/system/user")
public class UserManagerController extends BaseController {

	@Resource
	private SysUserService sysUserService;

	@RequestMapping("manager")
	public String manager(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "system/userManager";
	}

	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "system/userAdd";
	}

	@RequestMapping("edite")
	public String edite(Integer id, Model model) {
		model.addAttribute("sysUser", sysUserService.getUser(id));
		return "system/userEdite";
	}

	@RequestMapping("submit.htm")
	@ResponseBody
	public MSG submit(SysUser sysUser) throws Exception {
		if (sysUser.getId() == null) {
			return sysUserService.createUser(sysUser);
		} else {
			return sysUserService.updateUser(sysUser);
		}
	}

	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(String ids) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(ids);
		return sysUserService.deledteUser(jsonArray);
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = sysUserService.findUserPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		sysUserService.excelSysUser(response, jsonObject);
		return null;
	}

}
