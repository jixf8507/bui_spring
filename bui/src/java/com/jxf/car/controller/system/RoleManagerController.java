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
import com.jxf.common.tools.StringTools;

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

	/**
	 * 提交保存员工信息
	 * 
	 * @param sysUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("submit.htm")
	@ResponseBody
	public MSG submit(SysRole role) throws Exception {
		if (role.getId() == null) {
			return roleService.createRole(role);
		} else {
			return roleService.updateRole(role);
		}
	}

	/**
	 * 删除系统用户
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
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
		JSONObject jsonObject = getJsonPara(paraData, session);
		return roleService.findRoles(jsonObject);
	}

	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = roleService.findRolePage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
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
		roleService.excelSysRole(response, jsonObject);
		return null;
	}

}
