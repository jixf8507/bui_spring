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
import com.jxf.common.base.BaseExport;
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

	/**
	 * 分页查找系统用户月还款账单
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserMonthBillPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return userMonthBillDao.findUserMonthBillPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelUserMonthBill(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new UserMonthBillExport();
		List<Map<String, Object>> list = userMonthBillDao
				.findUserMonthBillList(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getUserMonthBillMap(Integer id) {
		return userMonthBillDao.getUserMonthBillMap(id);
	}

}
