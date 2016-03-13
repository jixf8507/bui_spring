package com.jxf.car.service.customer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.customer.UserBillDetailDao;
import com.jxf.car.export.user.UserBillDetailExport;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class UserBillDetailService extends BaseService {

	@Autowired
	private UserBillDetailDao billDetailDao;

	public PageResults findUserBillDetailPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return billDetailDao.findUserBillDetailPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelUserBillDetail(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = billDetailDao
					.findUserBillDetailList(jsonObject);
			UserBillDetailExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
