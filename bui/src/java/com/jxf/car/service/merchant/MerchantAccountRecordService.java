package com.jxf.car.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.merchant.MerchantAccountRecordDao;
import com.jxf.car.export.mechant.MerchantAccountRecordExport;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;

@Service
public class MerchantAccountRecordService extends BaseService {

	@Autowired
	private MerchantAccountRecordDao accountRecordDao;

	public PageResults findMerchantAccountRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return accountRecordDao.findMerchantAccountRecordPage(jsonObject,
				pageSize, iDisplayStart);
	}

	public void excelMerchantAccountRecord(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = accountRecordDao
					.findMerchantAccountRecordList(jsonObject);
			MerchantAccountRecordExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
