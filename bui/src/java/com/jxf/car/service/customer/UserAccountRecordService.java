package com.jxf.car.service.customer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.customer.UserAccountRecordDao;
import com.jxf.car.export.user.UserAccountRecordExport;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;

@Service
public class UserAccountRecordService extends BaseService {

	@Autowired
	private UserAccountRecordDao accountRecordDao;

	public PageResults findUserAccountRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return accountRecordDao.findUserAccountRecordPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelUserAccountRecord(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = accountRecordDao
					.findUserAccountRecordList(jsonObject);
			UserAccountRecordExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
