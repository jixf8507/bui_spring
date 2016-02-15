package com.jxf.car.service.customer;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.customer.UserBillDetailDao;
import com.jxf.car.dao.customer.UserMonthBillDao;
import com.jxf.car.export.user.UserMonthBillExport;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.MyDateUtil;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class UserMonthBillService extends BaseService {

	@Autowired
	private UserMonthBillDao userMonthBillDao;
	@Autowired
	private UserBillDetailDao billDetailDao;
	
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
	
	@Transactional
	public boolean createUserMonthBillByDay(String day){
		try {
			Date date = MyDateUtil.stringToDate(day);
			Calendar current = Calendar.getInstance();
			current.setTime(date);
			current.add(Calendar.MONTH, -1);
			String beginDate = MyDateUtil.getDayString(current.getTime());
			List<Map<String, Object>> list = billDetailDao.staticsUserMonthBillList(beginDate, day) ;
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return false ;
	}

}
