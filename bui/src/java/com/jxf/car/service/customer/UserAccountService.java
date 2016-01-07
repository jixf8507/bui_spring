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
import com.jxf.common.base.BaseExport;
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

	/**
	 * 分页查询系统用户列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserAccountPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return accountDao.findUserAccountPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelUserAccount(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new UserAccountExport();
		List<Map<String, Object>> list = accountDao
				.findUserAccountList(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据用户ID查找用户信息
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getAccountMap(Integer id) {
		return accountDao.getAccountMap(id);
	}

	/**
	 * 
	 * @param account
	 * @return
	 */
	@Transactional
	public MSG createUserAccount(UserAccount userAccount) {
		MSG msg = new MSG();
		try {
			userDao.updateStatus(2, userAccount.getUser().getStatusDesc(),
					userAccount.getUserId());
			accountDao.create(userAccount);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setInfo("新增用户账户失败");
		}
		return msg;
	}

	@Transactional
	public MSG updateUserAccount(UserAccount userAccount) {
		MSG msg = new MSG();
		try {
			userDao.update(userAccount.getUser());
			accountDao.update(userAccount);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setInfo("修改用户账户失败");
		}
		return msg;
	}

}
