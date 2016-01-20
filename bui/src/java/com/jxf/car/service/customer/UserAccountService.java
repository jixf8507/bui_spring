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

	/**
	 * 分页查询系统用户列表
	 * 
	 * @param jsonObject
	 *            查询条件
	 * @param pageSize
	 *            每页条数
	 * @param iDisplayStart
	 *            起始条数
	 * @return
	 */
	public PageResults findUserAccountPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return accountDao.findUserAccountPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 导出用户账户信息
	 * 
	 * @param response
	 * @param jsonObject
	 *            查询条件
	 */
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
	 * 新增用户账户
	 * 
	 * @param account
	 *            用户账户信息
	 * @return
	 */
	@Transactional
	public MSG createUserAccount(UserAccount userAccount) {
		try {
			userDao.updateStatus(2, userAccount.getUser().getStatusDesc(),
					userAccount.getUserId());
			accountDao.create(userAccount);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "新增用户账户失败");
	}

	/**
	 * 修改用户账户
	 * 
	 * @param userAccount
	 *            用户账户信息
	 * @return
	 */
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
