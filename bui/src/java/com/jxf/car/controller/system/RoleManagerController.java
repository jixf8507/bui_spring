package com.jxf.car.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.controller.BaseController;
import com.jxf.car.model.SysRole;
import com.jxf.car.service.system.SysRoleService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageHelp;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.JSONTools;

/**
 * 系统角色管理
 * 
 * @author jixf
 * @date 2015年11月24日
 */
@Controller
@RequestMapping("/system/role")
public class RoleManagerController extends BaseController {

	@Resource
	private SysRoleService roleService;

	@RequestMapping("manager")
	public String manager() {
		return "system/roleManager";
	}

	@RequestMapping("add")
	public String add() {
		return "system/roleAdd";
	}

	@RequestMapping("edite")
	public String edite(Integer id, Model model) {
		model.addAttribute("sysRole", roleService.getRole(id));
		return "system/roleEdite";
	}

	@RequestMapping("submit.htm")
	@ResponseBody
	public MSG submit(SysRole role) throws Exception {
		if (role.getId() == null) {
			return roleService.createRole(role);
		} else {
			return roleService.updateRole(role);
		}
	}

	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(String ids) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(ids);
		return roleService.deledteRole(jsonArray);
	}

	@RequestMapping("ajaxList")
	@ResponseBody
	public List<Map<String, Object>> ajaxList(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		return roleService.findRoles(jsonObject);
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		PageHelp pageHelp = JSONTools.toPageHelp(aoData);
		PageResults pageResults = roleService.findRolePage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	@RequestMapping("exportToExcel.htm")
	public String exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONTools.getJsonPara(paraData);
		roleService.excelSysRole(response, jsonObject);
		return null;
	}

}
