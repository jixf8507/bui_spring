package com.jxf.car.dao.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.UserAccount;
import com.jxf.car.model.UserAccountRecord;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class UserAccountDao extends BaseDao {

	@Resource(name = "user_account_select_sql")
	private JSONSqlMapping userSelectSQL;

	public PageResults findUserAccountPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(userSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserAccountList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(userSelectSQL, jsonObject);
	}

	public boolean batchCurWhiteBarLimit(List<UserAccountRecord> repaidRecords) {
		return UserAccount.batchCurWhiteBarLimit(repaidRecords, this);
	}

	public void curWhiteBarLimitAllUpdate() {
		UserAccount.curWhiteBarLimitAllUpdate(this);
	}

	public void updateAllCurWhiteBarLimitForSup() {
		UserAccount.updateAllCurWhiteBarLimitForSup(this);
	}

	public Map<String, Object> getAccountMap(Integer id) {
		return UserAccount.get(id, this);
	}

	public UserAccount getByUserId(Integer id) {
		return UserAccount.getByUserId(id, this);
	}

	public Integer create(UserAccount userAccount) {
		return userAccount.create(this);
	}

	public boolean update(UserAccount userAccount) {
		return userAccount.update(this) > 0;
	}

	public void addCurUsableLimit(BigDecimal balance, Integer id) {
		UserAccount.addCurUsableLimit(balance, id, this);
		UserAccount.updateCurWhiteBarLimit(id, this);
	}

	public boolean addCurWhiteBarLimit(BigDecimal balance, Integer id) {
		return UserAccount.addCurWhiteBarLimit(balance, id, this) > 0;
	}

}
