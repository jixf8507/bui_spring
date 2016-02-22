package com.jxf.car.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.merchant.MerchantOrderDao;
import com.jxf.car.export.mechant.MerchantOrderExport;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;

@Service
public class MerchantOrderService extends BaseService {

	@Autowired
	private MerchantOrderDao merchantOrderDao;

	public PageResults findMerchantOrderPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return merchantOrderDao.findMerchantOrderPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelMerchantOrder(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = merchantOrderDao
					.findMerchantderList(jsonObject);
			MerchantOrderExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getMerchantOrderMap(Integer id) {
		return merchantOrderDao.get(id);
	}

}
