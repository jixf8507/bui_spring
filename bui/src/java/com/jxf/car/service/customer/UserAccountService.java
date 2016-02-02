package com.jxf.car.service.customer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.customer.UserAccountDao;
import com.jxf.car.dao.customer.UserDao;
import com.jxf.car.export.user.UserAccountExport;
import com.jxf.car.model.UserAccount;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class UserAccountService extends BaseService {

	@Autowired
	private UserAccountDao accountDao;
	@Autowired
	private UserDao userDao;

	public PageResults findUserAccountPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return accountDao.findUserAccountPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelUserAccount(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = accountDao
					.findUserAccountList(jsonObject);
			UserAccountExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getAccountMap(Integer id) {
		return accountDao.getAccountMap(id);
	}

	@Transactional
	public MSG createUserAccount(UserAccount userAccount) {
		try {
			// 更改用户状态
			userDao.updateStatus(2, userAccount.getUser().getStatusDesc(),
					userAccount.getUserId());
			accountDao.create(userAccount);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "新增用户账户失败");
	}

	@Transactional
	public MSG updateUserAccount(UserAccount userAccount) {
		try {
			userDao.update(userAccount.getUser());
			accountDao.update(userAccount);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "修改用户账户失败");
	}

}
