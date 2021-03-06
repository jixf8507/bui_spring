package com.jxf.car.service.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.customer.UserAccountDao;
import com.jxf.car.dao.customer.UserAccountRecordDao;
import com.jxf.car.dao.customer.UserBillDetailDao;
import com.jxf.car.dao.customer.UserOrderDao;
import com.jxf.car.dao.system.SysSettingDao;
import com.jxf.car.export.user.UserOrderExport;
import com.jxf.car.model.UserAccount;
import com.jxf.car.model.UserAccountRecord;
import com.jxf.car.model.UserBillDetail;
import com.jxf.car.model.UserOrder;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class UserOrderService extends BaseService {

	@Autowired
	private UserOrderDao userOrderDao;
	@Autowired
	private UserAccountDao accountDao;
	@Autowired
	private SysSettingDao settingDao;
	@Autowired
	private UserBillDetailDao billDetailDao;
	@Autowired
	private UserAccountRecordDao accountRecordDao;

	public PageResults findUserOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return userOrderDao.findUserOrderPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelUserOrder(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = userOrderDao
					.findUserOrderList(jsonObject);
			UserOrderExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getUserOrderMap(Integer id) {
		return userOrderDao.getUserOrderMap(id);
	}

	@Transactional
	public MSG submitCheckOrder(UserOrder userOrder) {
		UserOrder order = userOrderDao.getUserOrder(userOrder.getId());
		if (order != null && order.getStatus() != 1) {
			return MSG.createErrorMSG(1, "该订单已经被审核，不能重复审核！");
		}
		MSG msg = createUserBill(userOrder);
		if (msg.isSuccess()) {
			if (userOrderDao.submitCheckOrder(userOrder)) {
				return MSG.createSuccessMSG();
			}
			return MSG.createErrorMSG(1, "审核订单失败");
		}
		return msg;
	}

	@Transactional
	public MSG batchUpdateStatus(int status, JSONArray jsonArray) {
		if (userOrderDao.batchUpdateStatus(status, jsonArray)) {
			return MSG.createSuccessMSG();
		}
		return MSG.createErrorMSG(1, "操作订单失败！");
	}

	private MSG createUserBill(UserOrder userOrder) {
		switch (userOrder.getStatus()) {
		case 2:
			UserOrder uo = userOrderDao.getUserOrder(userOrder.getId());
			UserAccount ua = accountDao.getByUserId(uo.getUserId());
			if (ua.getStatus() != 1 && ua.getStatus() != 2) {
				return MSG.createErrorMSG(1, "该用户账户状态不可分期");
			}
			if (ua.getCurUsableLimit().compareTo(uo.getCost()) < 0) {
				return MSG.createErrorMSG(1, "用户账户余额不足");
			}
			BigDecimal interest = settingDao.findSysInterest();
			UserAccountRecord accountRecord = UserAccountRecord
					.createByUserOrder(uo);
			List<UserBillDetail> userBillList = UserBillDetail
					.createUserBillDetailByOrder(uo, interest);
			accountDao.addCurUsableLimit(accountRecord.getMoney(), ua.getId());
			accountRecordDao.create(accountRecord);
			billDetailDao.batchCreateUserBillDetail(userBillList);
			break;
		case 3:

			break;
		default:
			break;
		}
		return MSG.createSuccessMSG();
	}

}
