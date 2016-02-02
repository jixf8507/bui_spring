package com.jxf.car.service.customer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.customer.UserMonthBillDao;
import com.jxf.car.export.user.UserMonthBillExport;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class UserMonthBillService extends BaseService {

	@Autowired
	private UserMonthBillDao userMonthBillDao;

	public PageResults findUserMonthBillPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return userMonthBillDao.findUserMonthBillPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelUserMonthBill(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = userMonthBillDao
					.findUserMonthBillList(jsonObject);
			UserMonthBillExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getUserMonthBillMap(Integer id) {
		return userMonthBillDao.getUserMonthBillMap(id);
	}

}
