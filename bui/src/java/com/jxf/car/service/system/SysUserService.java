package com.jxf.car.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.bui.BuiMenu;
import com.jxf.bui.ButtonMenu;
import com.jxf.bui.ConfigMenu;
import com.jxf.bui.MenuItem;
import com.jxf.car.dao.system.SysRoleMenuDAO;
import com.jxf.car.dao.system.SysUserDAO;
import com.jxf.car.model.SysUser;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
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
		List<ConfigMenu> configMenus = new ArrayList<>();
		List<Map<String, Object>> menus = roleMenuDAO.findMenusByRoleId(roleId);
		Map<String, ConfigMenu> level1Map = new HashMap<String, ConfigMenu>();
		Map<String, MenuItem> level2Map = new HashMap<String, MenuItem>();
		Map<String, List<ButtonMenu>> buttomMap = new HashMap<>();
		for (Map<String, Object> menuObj : menus) {
			String id = menuObj.get("id") + "";
			String level = menuObj.get("level") + "";
			String moduleName = menuObj.get("moduleName") + "";
			String moduleUrl = menuObj.get("moduleUrl") + "";

			String pid = menuObj.get("pid") + "";
			if ("0".equals(level)) {
				ConfigMenu configMenu = new ConfigMenu(id, null, moduleName,
						new ArrayList<MenuItem>());
				level1Map.put(id, configMenu);
				configMenus.add(configMenu);
			} else if ("1".equals(level)) {
				MenuItem menuItem = new MenuItem(id, moduleName, moduleUrl);
				level2Map.put(id, menuItem);
				level1Map.get(pid).getMenu().add(menuItem);
			} else if ("2".equals(level)) {
				MenuItem menuItem = new MenuItem(id, moduleName, contextPath
						+ moduleUrl);
				List<MenuItem> items = level2Map.get(pid).getItems();
				if (items == null) {
					items = new ArrayList<>();
					level2Map.get(pid).setItems(items);
				}
				items.add(menuItem);
			} else if ("3".equals(level)) {
				ButtonMenu buttonMenu = new ButtonMenu(moduleName, moduleUrl);
				List<ButtonMenu> list = buttomMap.get(pid);
				if (list == null) {
					list = new ArrayList<>();
					buttomMap.put(pid, list);
				}
				list.add(buttonMenu);
			}
		}
		return new BuiMenu(configMenus, buttomMap);
	}
}
