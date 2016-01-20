package com.jxf.car.service.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.system.SysRoleMenuDAO;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;

/**
 * 
 * @author jixf
 * @date 2015年12月4日
 */
@Service
public class SysRoleMenuService extends BaseService {

	@Resource
	private SysRoleMenuDAO sysRoleMenuDAO;

	/**
	 * 查询系统菜单列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findMenus() {
		return sysRoleMenuDAO.findMenus();
	}

	/**
	 * 查询角色菜单列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> findRoleMenus(Integer roleId) {
		return sysRoleMenuDAO.findRoleMenus(roleId);
	}

	/**
	 * 保存系统角色菜单
	 * 
	 * @param roleId
	 *            角色ID
	 * @param jsonArray
	 *            菜单列表
	 * @return
	 */
	@Transactional
	public MSG saveRoleMenus(Integer roleId, JSONArray jsonArray) {
		try {
			sysRoleMenuDAO.delteByRoleId(roleId);
			sysRoleMenuDAO.batchCreate(roleId, jsonArray);
			return MSG.createSuccessMSG();
		} catch (Exception e) {

		}
		return MSG.createErrorMSG(1, "保存成功");
	}
}
