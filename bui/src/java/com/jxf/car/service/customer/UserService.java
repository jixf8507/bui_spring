package com.jxf.car.service.customer;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.customer.UserDao;
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

	/**
	 * 分页查询用户信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return userDao.findUserPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserMap(Integer id) {
		return userDao.getUserMap(id);
	}
	


}
