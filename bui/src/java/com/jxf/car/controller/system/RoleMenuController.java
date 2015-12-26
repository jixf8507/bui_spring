package com.jxf.car.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.car.controller.BaseController;
import com.jxf.car.service.system.SysRoleMenuService;
import com.jxf.car.web.MSG;

/**
 * 
 * @author jixf
 * @date 2015年11月27日
 */
@Controller
@RequestMapping("/system/roleMenu")
public class RoleMenuController extends BaseController {

	@Resource
	private SysRoleMenuService roleMenuService;

	@RequestMapping("select")
	public String select(Integer rid, Model model) {
		model.addAttribute("rid", rid);
		return "system/roleMenusSelect";
	}

	/**
	 * 动态返回所有系统菜单列表
	 * 
	 * @return
	 */
	@RequestMapping("ajaxMenus")
	@ResponseBody
	public List<Map<String, Object>> ajaxMenus() {
		return roleMenuService.findMenus();
	}

	/**
	 * 动态返回角色菜单列表
	 * 
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	@RequestMapping("ajaxRoleMenus")
	@ResponseBody
	public List<Map<String, Object>> ajaxRoleMenus(Integer roleId) {
		return roleMenuService.findRoleMenus(roleId);
	}

	/**
	 * 更改角色菜单
	 * 
	 * @param roleId
	 *            角色ID
	 * @param mIds
	 *            菜单id
	 * @return
	 */
	@RequestMapping("saveRoleMenus")
	@ResponseBody
	public MSG saveRoleMenus(Integer roleId, String mIds) {
		JSONArray jsonArray = JSONArray.fromObject(mIds);
		return roleMenuService.saveRoleMenus(roleId, jsonArray);
	}

}
