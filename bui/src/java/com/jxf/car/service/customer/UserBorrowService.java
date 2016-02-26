package com.jxf.car.service.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.customer.UserAccountDao;
import com.jxf.car.dao.customer.UserAccountRecordDao;
import com.jxf.car.dao.customer.UserBillDetailDao;
import com.jxf.car.dao.customer.UserBorrowDao;
import com.jxf.car.dao.system.SysSettingDao;
import com.jxf.car.export.user.UserBorrowExport;
import com.jxf.car.model.UserAccount;
import com.jxf.car.model.UserAccountRecord;
import com.jxf.car.model.UserBillDetail;
import com.jxf.car.model.UserBorrow;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageResults;

@Service
public class UserBorrowService extends BaseService {

	@Autowired
	private UserBorrowDao borrowDao;
	@Autowired
	private UserAccountDao accountDao;
	@Autowired
	private SysSettingDao settingDao;
	@Autowired
	private UserBillDetailDao billDetailDao;
	@Autowired
	private UserAccountRecordDao accountRecordDao;

	public PageResults findUserBorrowPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return borrowDao
				.findUserBorrowPage(jsonObject, pageSize, iDisplayStart);
	}

	public void excelUserBorrow(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = borrowDao
					.findUserBorrowList(jsonObject);
			UserBorrowExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getUserBorrowMap(Integer id) {
		return borrowDao.getUserBorrowMap(id);
	}

	@Transactional
	public MSG submitCheckOrder(UserBorrow userBorrow) {
		MSG msg = createUserBill(userBorrow);
		if (msg.isSuccess()) {
			if (borrowDao.submitCheckOrder(userBorrow)) {
				return MSG.createSuccessMSG();
			}
			return MSG.createErrorMSG(1, "审核订单失败");
		}
		return msg;
	}

	private MSG createUserBill(UserBorrow userBorrow) {
		switch (userBorrow.getStatus()) {
		case 2:
			UserBorrow ub = borrowDao.getUserBorrow(userBorrow.getId());
			UserAccount ua = accountDao.getByUserId(ub.getUserId());
			if (ua.getStatus() != 1) {
				return MSG.createErrorMSG(1, "该用户账户状态不可提现");
			}
			if (ua.getCurWhiteBarLimit().compareTo(ub.getCost()) < 0) {
				return MSG.createErrorMSG(1, "用户账户提现额度不足");
			}
			BigDecimal interest = settingDao.findSysInterest();
			UserAccountRecord accountRecord = UserAccountRecord
					.createByUserBorrow(userBorrow);
			List<UserBillDetail> userBillList = UserBillDetail
					.createUserBillDetailByOrder(ub, interest);
			accountRecordDao.create(accountRecord);
			accountDao.addCurUsableLimit(accountRecord.getMoney(), ua.getId());
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
