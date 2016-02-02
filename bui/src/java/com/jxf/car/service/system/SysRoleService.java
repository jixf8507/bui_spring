package com.jxf.car.service.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.system.SysRoleDAO;
import com.jxf.car.export.system.SysRoleExport;
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

	public PageResults findRolePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return roleDAO.findRolePage(jsonObject, pageSize, iDisplayStart);
	}

	public void excelSysRole(HttpServletResponse response, JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = this.findRoles(jsonObject);
			SysRoleExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> findRoles(JSONObject jsonObject) {
		return roleDAO.findRoles(jsonObject);
	}

	public SysRole getRole(Integer id) {
		return roleDAO.get(id);
	}

	public MSG createRole(SysRole role) {
		if (roleDAO.create(role) == null) {
			return MSG.createErrorMSG(1, "保存角色信息失败！");
		}
		return MSG.createSuccessMSG();
	}

	public MSG updateRole(SysRole role) {
		if (roleDAO.update(role)) {
			return MSG.createSuccessMSG();
		}
		return MSG.createErrorMSG(1, "修改角色信息失败！");
	}

	@Transactional
	public MSG deledteRole(JSONArray jsonArray) {
		try {
			for (Object id : jsonArray) {
				roleDAO.delete(id);
			}
			return MSG.createSuccessMSG();
		} catch (Exception e) {
		}
		return MSG.createErrorMSG(1, "删除角色信息失败！");
	}

}
