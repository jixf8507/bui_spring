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
import com.jxf.common.tools.StringTools;

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

	/**
	 * 用户管理
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "system/userManager";
	}

	/**
	 * 添加系统员工
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
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

	/**
	 * 提交保存员工信息
	 * 
	 * @param sysUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("submit.htm")
	@ResponseBody
	public MSG submit(SysUser sysUser) throws Exception {
		if (sysUser.getId() == null) {
			return sysUserService.createUser(sysUser);
		} else {
			return sysUserService.updateUser(sysUser);
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
		return sysUserService.deledteUser(jsonArray);
	}

	/**
	 * 分页加载系统员工数据
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping("ajaxData")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = sysUserService.findUserPage(jsonObject,
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

}
