package com.jxf.car.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.bui.BuiMenu;
import com.jxf.car.dao.merchant.MerchantDao;
import com.jxf.car.export.mechant.MerchantExport;
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

	public void excelMerchant(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = this.findMerchantList(jsonObject);
			MerchantExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MSG createMerchant(Merchant merchant) {
		try {
			if (merchantDao.getMerchantMap(merchant.getCode()) != null) {
				return MSG.createErrorMSG(1, "商家登录号已经存在");
			}
			merchantDao.create(merchant);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "新增商家失败");
	}

	public MSG updateMerchant(Merchant merchant) {
		try {
			Merchant merchantMap = merchantDao.getMerchantMap(merchant
					.getCode());
			if (merchantMap != null && merchantMap.getId() != merchant.getId()) {
				return MSG.createErrorMSG(1, "商家登录号已经存在");
			}
			merchantDao.update(merchant);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "修改商家失败");
	}

	public MSG updateMerchantPassword(Merchant merchant) {
		try {
			merchantDao.updatePassword(merchant);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "修改密码失败");
	}

	public Map<String, Object> getMerchantMap(Integer id) {
		return merchantDao.getMerchantMap(id);
	}

	public Merchant getMerchantMap(String code) {
		return merchantDao.getMerchantMap(code);
	}

	public List<Map<String, Object>> findMerchantList(JSONObject jsonObject) {
		return merchantDao.findMerchantList(jsonObject);
	}

	public BuiMenu getConfigMunes(String contextPath) {
		List<Map<String, Object>> menus = merchantDao.findMerchantMenus();
		return BuiMenu.createBuiMenu(menus, contextPath);
	}

}
