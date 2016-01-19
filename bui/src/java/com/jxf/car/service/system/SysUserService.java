package com.jxf.car.service.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.bui.BuiMenu;
import com.jxf.car.dao.system.SysRoleMenuDAO;
import com.jxf.car.dao.system.SysUserDAO;
import com.jxf.car.export.system.SysUserExport;
import com.jxf.car.model.SysUser;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.BaseExport;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author jixf
 * @date 2015年11月21日
 */
@Service
public class SysUserService extends BaseService {

	@Resource
	private SysUserDAO sysUserDAO;

	@Resource
	private SysRoleMenuDAO roleMenuDAO;

	/**
	 * 根据登录号查找用户
	 * 
	 * @param code
	 * @return
	 */
	public SysUser findUserByCode(String code) {
		return sysUserDAO.findByCode(code);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public SysUser getUser(Integer id) {
		return sysUserDAO.get(id);
	}

	/**
	 * 分页查找系统员工信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return sysUserDAO.findUserPage(jsonObject, pageSize, iDisplayStart);
	}

	public void excelSysUser(HttpServletResponse response, JSONObject jsonObject) {
		BaseExport export = new SysUserExport();
		List<Map<String, Object>> list = sysUserDAO.findUserList(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param sysUser
	 * @return
	 */
	public MSG createUser(SysUser sysUser) {
		MSG msg = new MSG();
		Integer id = sysUserDAO.create(sysUser);
		if (id == null) {
			msg.setSuccess(false);
			msg.setInfo("保存员工信息失败！");
		}
		return msg;
	}

	public MSG updateUser(SysUser sysUser) {
		MSG msg = new MSG();
		if (!sysUserDAO.update(sysUser)) {
			msg.setSuccess(false);
			msg.setInfo("修改员工信息失败！");
		}
		return msg;
	}

	/**
	 * 批量删除员工
	 * 
	 * @param jsonArray
	 * @return
	 */
	@Transactional
	public MSG deledteUser(JSONArray jsonArray) {
		MSG msg = new MSG();
		try {
			for (Object id : jsonArray) {
				sysUserDAO.delete(id);
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setInfo("删除员工信息失败！");
		}
		return msg;
	}

	/**
	 * 根据用户角色得到系统菜单对象
	 * 
	 * @param roleId
	 * @return
	 */
	public BuiMenu getConfigMunesByRoleId(Integer roleId, String contextPath) {
		List<Map<String, Object>> menus = roleMenuDAO.findMenusByRoleId(roleId);
		return BuiMenu.createBuiMenu(menus, contextPath);
	}
}
