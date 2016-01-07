package com.jxf.car.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.merchant.MerchantDao;
import com.jxf.car.export.mechant.MerchantExport;
import com.jxf.car.export.user.UserExport;
import com.jxf.car.model.Merchant;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.BaseExport;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class MerchantService extends BaseService {

	@Autowired
	private MerchantDao merchantDao;

	public PageResults findMerchantPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return merchantDao
				.findMerchantPage(jsonObject, pageSize, iDisplayStart);
	}

	public void excelMerchant(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MerchantExport();
		List<Map<String, Object>> list = this.findMerchantList(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MSG createMerchant(Merchant merchant) {
		MSG msg = new MSG();
		try {
			merchantDao.create(merchant);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setInfo("新增商家失败");
		}
		return msg;
	}

	public MSG updateMerchant(Merchant merchant) {
		MSG msg = new MSG();
		try {
			merchantDao.update(merchant);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setInfo("修改商家失败");
		}
		return msg;
	}

	public Map<String, Object> getMerchantMap(Integer id) {
		return merchantDao.getMerchantMap(id);
	}

	public List<Map<String, Object>> findMerchantList(JSONObject jsonObject) {
		return merchantDao.findMerchantList(jsonObject);
	}

}
