package com.jxf.car.service.merchant;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.merchant.MerchantDao;
import com.jxf.car.model.Merchant;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
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
