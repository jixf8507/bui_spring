package com.jxf.car.service.customer;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.customer.UserOrderDao;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class UserOrderService extends BaseService {

	@Autowired
	private UserOrderDao userOrderDao;

	public PageResults findUserOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return userOrderDao.findUserOrderPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserOrderMap(Integer id) {
		return userOrderDao.getUserOrderMap(id);
	}

}
