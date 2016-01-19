package com.jxf.bui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuiMenu {

	private List<ConfigMenu> configMenus;
	private Map<String, List<ButtonMenu>> buttomMap;
	private Map<String, ConfigMenu> configMuneMap;
	private Map<String, MenuItem> menuItemMap;

	public BuiMenu() {

	}

	public static BuiMenu createBuiMenu(List<Map<String, Object>> menus,
			String contextPath) {
		BuiMenu bm = new BuiMenu();
		bm.configMenus = new ArrayList<>();
		bm.buttomMap = new HashMap<>();
		bm.configMuneMap = new HashMap<String, ConfigMenu>();
		bm.menuItemMap = new HashMap<String, MenuItem>();
		bm.init(menus, contextPath);
		return bm;
	}

	/**
	 * 根据数据库菜单列表设置系统菜单项
	 * 
	 * @param menus
	 * @param contextPath
	 *            系统URL请求相对路径
	 */
	private void init(List<Map<String, Object>> menus, String contextPath) {
		for (Map<String, Object> menuObj : menus) {
			String id = menuObj.get("id") + "";
			String level = menuObj.get("level") + "";
			String moduleName = menuObj.get("moduleName") + "";
			String moduleUrl = menuObj.get("moduleUrl") + "";
			String pid = menuObj.get("pid") + "";
			// 添加系统菜单
			addMenu(contextPath, id, level, moduleName, moduleUrl, pid);
		}
	}

	/**
	 * 添加系统菜单
	 * 
	 * @param contextPath
	 *            URL相对路径
	 * @param id
	 *            菜单ID
	 * @param level
	 *            级别
	 * @param moduleName
	 *            菜单名称
	 * @param moduleUrl
	 *            请求URL
	 * @param pid
	 *            父级菜单ID
	 */
	private void addMenu(String contextPath, String id, String level,
			String moduleName, String moduleUrl, String pid) {
		switch (level) {
		case "0":// 添加系统模块菜单
			addConfigMenu(id, moduleName);
			break;
		case "1":// 添加模块下分类菜单
			addMenuItem1(id, moduleName, moduleUrl, pid);
			break;
		case "2":// 添加分类的二级菜单（可点击的菜单）
			addMenuItem2(contextPath, id, moduleName, moduleUrl, pid);
			break;
		case "3":// 添加菜单下的操作按钮
			addButtonMenu(moduleName, moduleUrl, pid);
			break;
		default:
			break;
		}
	}

	/**
	 * 添加按钮菜单
	 * 
	 * @param moduleName
	 * @param moduleUrl
	 * @param pid
	 */
	private void addButtonMenu(String moduleName, String moduleUrl, String pid) {
		ButtonMenu buttonMenu = ButtonMenu.createButtonMenu(moduleName,
				moduleUrl);
		List<ButtonMenu> list = buttomMap.get(pid);
		if (list == null) {
			list = new ArrayList<>();
			buttomMap.put(pid, list);
		}
		list.add(buttonMenu);
	}

	/**
	 * 添加模块下二级菜单（可点击访问的菜单项）
	 * 
	 * @param contextPath
	 * @param id
	 * @param moduleName
	 * @param moduleUrl
	 * @param pid
	 */
	private void addMenuItem2(String contextPath, String id, String moduleName,
			String moduleUrl, String pid) {
		MenuItem menuItem = MenuItem.createMenuItem(id, moduleName, contextPath
				+ moduleUrl);
		List<MenuItem> items = menuItemMap.get(pid).getItems();
		if (items == null) {
			items = new ArrayList<>();
			menuItemMap.get(pid).setItems(items);
		}
		items.add(menuItem);
	}

	/**
	 * 添加模块下一级菜单
	 * 
	 * @param id
	 * @param moduleName
	 * @param moduleUrl
	 * @param pid
	 */
	private void addMenuItem1(String id, String moduleName, String moduleUrl,
			String pid) {
		MenuItem menuItem = MenuItem.createMenuItem(id, moduleName, moduleUrl);
		menuItemMap.put(id, menuItem);
		configMuneMap.get(pid).getMenu().add(menuItem);
	}

	/**
	 * 添加模块菜单
	 * 
	 * @param id
	 * @param moduleName
	 */
	private void addConfigMenu(String id, String moduleName) {
		ConfigMenu configMenu = ConfigMenu.createConfigMenu(id, null,
				moduleName, new ArrayList<MenuItem>());
		configMenus.add(configMenu);
		configMuneMap.put(id, configMenu);
	}

	public List<ConfigMenu> getConfigMenus() {
		return configMenus;
	}

	public void setConfigMenus(List<ConfigMenu> configMenus) {
		this.configMenus = configMenus;
	}

	public Map<String, List<ButtonMenu>> getButtomMap() {
		return buttomMap;
	}

	public void setButtomMap(Map<String, List<ButtonMenu>> buttomMap) {
		this.buttomMap = buttomMap;
	}

}
