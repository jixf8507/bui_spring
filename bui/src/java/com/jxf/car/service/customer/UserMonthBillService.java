package com.jxf.car.service.customer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.customer.UserAccountDao;
import com.jxf.car.dao.customer.UserBillDetailDao;
import com.jxf.car.dao.customer.UserMonthBillDao;
import com.jxf.car.dao.system.SysSettingDao;
import com.jxf.car.export.user.UserMonthBillExport;
import com.jxf.car.model.UserAccount;
import com.jxf.car.model.UserMonthBill;
import com.jxf.car.service.BaseService;
import com.jxf.common.base.PageResults;
import com.jxf.common.tools.MyDateUtil;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class UserMonthBillService extends BaseService {

	@Autowired
	private UserMonthBillDao userMonthBillDao;
	@Autowired
	private UserBillDetailDao billDetailDao;
	@Autowired
	private UserAccountDao accountDao;
	@Autowired
	private SysSettingDao settingDao;

	public PageResults findUserMonthBillPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return userMonthBillDao.findUserMonthBillPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelUserMonthBill(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = userMonthBillDao
					.findUserMonthBillList(jsonObject);
			UserMonthBillExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getUserMonthBillMap(Integer id) {
		return userMonthBillDao.getUserMonthBillMap(id);
	}

	@Transactional
	public void countUserMonthBillForDay() {
		List<Map<String, Object>> lastMonthBill = userMonthBillDao
				.findLastMonthBill();
		BigDecimal dayInterest = settingDao.findSysDayInterest();
		List<UserMonthBill> userBills = new ArrayList<>();
		List<UserAccount> accounts = new ArrayList<>();
		for (Map<String, Object> billMap : lastMonthBill) {
			UserAccount account = new UserAccount();
			UserMonthBill bill = UserMonthBill.createUserBill(billMap);
			if (bill.getPaid().compareTo(bill.getLastBalance()) <= 0) {
				account.setCurUsableLimit(bill.getPaid());
				bill.setLastBalance(bill.getLastBalance().subtract(
						bill.getPaid()));
				bill.setLastLnterest(bill.getLastLnterest().add(
						bill.getLastBalance().multiply(dayInterest)));
			} else if (bill.getPaid().compareTo(
					bill.getLastBalance().add(bill.getCurBalance())) <= 0) {
				account.setCurUsableLimit(bill.getPaid());
				bill.setCurBalance(bill.getCurBalance()
						.add(bill.getLastBalance()).subtract(bill.getPaid()));
				bill.setLastBalance(new BigDecimal(0));
			} else if (bill.getPaid().compareTo(
					bill.getLastBalance().add(bill.getCurBalance())
							.add(bill.getLastLnterest())) <= 0) {
				bill.setLastBalance(new BigDecimal(0));
				bill.setCurBalance(new BigDecimal(0));
				bill.setLastLnterest(bill.getLastBalance()
						.add(bill.getCurBalance()).add(bill.getLastLnterest())
						.subtract(bill.getPaid()));
				account.setCurUsableLimit(bill.getCurBalance().add(
						bill.getLastBalance()));
			} else if (bill.getPaid().compareTo(
					bill.getLastBalance().add(bill.getCurBalance())
							.add(bill.getLastLnterest())
							.add(bill.getCurLnterest())) < 0) {
				bill.setLastBalance(new BigDecimal(0));
				bill.setCurBalance(new BigDecimal(0));
				bill.setLastLnterest(new BigDecimal(0));
				bill.setCurLnterest(bill.getLastBalance()
						.add(bill.getCurBalance()).add(bill.getLastLnterest())
						.add(bill.getCurLnterest()).subtract(bill.getPaid()));
				account.setCurUsableLimit(bill.getCurBalance().add(
						bill.getLastBalance()));
			} else {
				bill.setLastBalance(new BigDecimal(0));
				bill.setCurBalance(new BigDecimal(0));
				bill.setLastLnterest(new BigDecimal(0));
				bill.setCurLnterest(new BigDecimal(0));
				account.setCurUsableLimit(bill.getPaid().subtract(
						bill.getCurBalance().add(bill.getLastBalance())));
				bill.setStatus(1);
			}
			if (bill.isBeyondRepaymentDate()) {
				bill.setCurLnterest(bill.getCurLnterest().add(
						bill.getCurBalance().multiply(dayInterest)));
			}
			bill.setPaid(new BigDecimal(0));

			account.setUserId(bill.getUserId());
			accounts.add(account);

			userBills.add(bill);
		}

		accountDao.batchAddCurUsableLimit(accounts);
		userMonthBillDao.batchUpdateUserMonthBill(userBills);
	}

	@Transactional
	public boolean createUserMonthBillByDay(String day) {
		try {
			Map<String, UserMonthBill> userBillMap = getUserBillMap(day);
			List<Map<String, Object>> accountList = getUserAccountByStatementDate(day);
			Map<String, UserMonthBill> lastUserBillMap = getLastUserMonthBill();
			List<UserMonthBill> userMonthBills = getUserMonthBills(userBillMap,
					lastUserBillMap, accountList);
			userMonthBillDao.batchClostLastUserMonthBill();
			userMonthBillDao.batchCreateUserMonthBill(userMonthBills);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Map<String, UserMonthBill> getLastUserMonthBill() {
		List<Map<String, Object>> lastMonthBill = userMonthBillDao
				.findLastMonthBill();
		Map<String, UserMonthBill> lastUserBillMap = new HashMap<>();
		for (Map<String, Object> billMap : lastMonthBill) {
			UserMonthBill bill = new UserMonthBill();
			bill.setId(Integer.parseInt(billMap.get("id") + ""));
			bill.setUserId(Integer.parseInt(billMap.get("userId") + ""));
			bill.setCurBalance(new BigDecimal(billMap.get("curBalance") + ""));
			bill.setLastBalance(new BigDecimal(billMap.get("lastBalance") + ""));
			bill.setLastLnterest(new BigDecimal(billMap.get("lastLnterest")
					+ ""));
			bill.setCurLnterest(new BigDecimal(billMap.get("curLnterest") + ""));
			lastUserBillMap.put(bill.getUserId() + "", bill);
		}
		return lastUserBillMap;
	}

	private List<UserMonthBill> getUserMonthBills(
			Map<String, UserMonthBill> userBillMap,
			Map<String, UserMonthBill> lastUserBillMap,
			List<Map<String, Object>> accountList) {
		List<UserMonthBill> userMonthBills = new ArrayList<>();
		for (Map<String, Object> accountMap : accountList) {
			String userId = accountMap.get("userId") + "";
			String repaymentDate = accountMap.get("repaymentDate") + "";
			UserMonthBill curbill = userBillMap.get(userId);
			UserMonthBill lastBill = lastUserBillMap.get(userId);
			UserMonthBill bill = mergeBill(curbill, lastBill);
			if (bill != null) {
				bill.setRepaymentDate(MyDateUtil
						.getNextRepaymentDate(repaymentDate));
				userMonthBills.add(bill);
			}
		}
		return userMonthBills;
	}

	private UserMonthBill mergeBill(UserMonthBill curbill,
			UserMonthBill lastBill) {
		UserMonthBill bill = null;
		if (curbill != null) {
			bill = curbill;
		}
		if (lastBill != null) {
			if (bill == null) {
				bill = new UserMonthBill();
				bill.setUserId(lastBill.getUserId());
			}
			bill.setLastBalance(lastBill.getCurBalance().add(
					lastBill.getLastBalance()));
			bill.setLastLnterest(lastBill.getLastLnterest().add(
					lastBill.getCurLnterest()));
		}
		return bill;
	}

	private List<Map<String, Object>> getUserAccountByStatementDate(String day) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("statementDate", day.substring(8, 10));
		List<Map<String, Object>> accountList = accountDao
				.findUserAccountList(jsonObject);
		return accountList;
	}

	private Map<String, UserMonthBill> getUserBillMap(String day)
			throws ParseException {
		Map<String, UserMonthBill> userBillMap = new HashMap<>();
		Date date = MyDateUtil.stringToDate(day);
		Calendar current = Calendar.getInstance();
		current.setTime(date);
		current.add(Calendar.MONTH, -1);
		String beginDate = MyDateUtil.getDayString(current.getTime());
		List<Map<String, Object>> list = billDetailDao
				.staticsUserMonthBillList(beginDate, day);
		for (Map<String, Object> map : list) {
			Integer userId = Integer.parseInt(map.get("userId") + "");
			UserMonthBill bill = new UserMonthBill();
			bill.setUserId(userId);
			bill.setCurBalance(new BigDecimal(map.get("capital") + ""));
			bill.setCurLnterest(new BigDecimal(map.get("interest") + ""));
			userBillMap.put(userId + "", bill);
		}
		return userBillMap;
	}
}
