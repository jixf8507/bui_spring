package com.jxf.car.service.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.system.SysRoleDAO;
import com.jxf.car.model.SysRole;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author jixf
 * @date 2015年11月24日
 */
@Service
public class SysRoleService extends BaseService {

	@Resource
	private SysRoleDAO roleDAO;

	/**
	 * 分页查找系统角色信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRolePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return roleDAO.findRolePage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件查询系统角色列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findRoles(JSONObject jsonObject) {
		return roleDAO.findRoles(jsonObject);
	}

	public SysRole getRole(Integer id) {
		return roleDAO.get(id);
	}

	public MSG createRole(SysRole role) {
		MSG msg = new MSG();
		Integer id = roleDAO.create(role);
		if (id == null) {
			msg.setSuccess(false);
			msg.setInfo("保存员工信息失败！");
		}
		return msg;
	}

	public MSG updateRole(SysRole role) {
		MSG msg = new MSG();
		if (!roleDAO.update(role)) {
			msg.setSuccess(false);
			msg.setInfo("修改员工信息失败！");
		}
		return msg;
	}

	@Transactional
	public MSG deledteRole(JSONArray jsonArray) {
		MSG msg = new MSG();
		try {
			for (Object id : jsonArray) {
				roleDAO.delete(id);
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setInfo("删除员工信息失败！");
		}
		return msg;
	}

}
