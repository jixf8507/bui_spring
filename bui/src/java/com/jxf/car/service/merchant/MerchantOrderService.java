package com.jxf.car.service.merchant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.customer.UserAccountDao;
import com.jxf.car.dao.customer.UserBillDetailDao;
import com.jxf.car.dao.merchant.MerchantOrderDao;
import com.jxf.car.dao.system.SysSettingDao;
import com.jxf.car.export.mechant.MerchantOrderExport;
import com.jxf.car.model.MerchantOrder;
import com.jxf.car.model.UserAccount;
import com.jxf.car.model.UserBillDetail;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageResults;

@Service
public class MerchantOrderService extends BaseService {

	@Autowired
	private MerchantOrderDao merchantOrderDao;
	@Autowired
	private UserAccountDao accountDao;
	@Autowired
	private SysSettingDao settingDao;
	@Autowired
	private UserBillDetailDao billDetailDao;

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

	@Transactional
	public MSG submitCheckOrder(MerchantOrder mo) {
		UserAccount ua = accountDao.getByUserId(mo.getUserId());
		if (ua.getStatus() != 1 && ua.getStatus() != 2) {
			return MSG.createErrorMSG(1, "该用户账户状态不可分期");
		}
		if (ua.getCurUsableLimit().compareTo(mo.getCost()) < 0) {
			return MSG.createErrorMSG(1, "用户账户余额不足");
		}
		BigDecimal interest = settingDao.findSysInterest();
		accountDao.addCurUsableLimit(new BigDecimal(0).subtract(mo.getCost()),
				ua.getId());
		List<UserBillDetail> userBillList = UserBillDetail
				.createUserBillDetailByOrder(mo, interest);
		billDetailDao.batchCreateUserBillDetail(userBillList);
		return MSG.createSuccessMSG();
	}

}
