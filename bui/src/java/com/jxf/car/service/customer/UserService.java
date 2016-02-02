package com.jxf.car.service.customer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.customer.UserDao;
import com.jxf.car.export.user.UserExport;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;

/**
 * 用户审核
 * 
 * @author Administrator
 * 
 */
@Service
public class UserService extends BaseService {

	@Autowired
	private UserDao userDao;

	public PageResults findUserPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return userDao.findUserPage(jsonObject, pageSize, iDisplayStart);
	}

	public void excelUser(HttpServletResponse response, JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = userDao.findUserList(jsonObject);
			UserExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getUserMap(Integer id) {
		return userDao.getUserMap(id);
	}

}
