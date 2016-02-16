package com.jxf.car.dao.customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.UserMonthBill;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class UserMonthBillDao extends BaseDao {

	@Resource(name = "user_month_bill_select_sql")
	private JSONSqlMapping billSelectSQL;

	public PageResults findUserMonthBillPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(billSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserMonthBillList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(billSelectSQL, jsonObject);
	}

	public Map<String, Object> getUserMonthBillMap(Integer id) {
		return this.get(GET_BY_ID_SQL, new Object[] { id });
	}

	public boolean batchCreateUserMonthBill(
			final List<UserMonthBill> userMonthBills) {
		int[] count = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						UserMonthBill uo = userMonthBills.get(i);
						int num = 1;
						ps.setInt(num++, uo.getUserId());
						ps.setBigDecimal(num++, uo.getCurBalance());
						ps.setBigDecimal(num++, uo.getLastBalance());
						ps.setBigDecimal(num++, uo.getPaid());
						ps.setBigDecimal(num++, uo.getLastLnterest());
						ps.setBigDecimal(num++, uo.getCurLnterest());
						ps.setInt(num++, 0);
						ps.setTimestamp(num++, uo.getRepaymentDate());
					}

					@Override
					public int getBatchSize() {
						return userMonthBills.size();
					}
				});
		return count.length == userMonthBills.size();
	}

	public boolean batchUpdateUserMonthBill(
			final List<UserMonthBill> userMonthBills) {
		int[] count = this.getJdbcTemplate().batchUpdate(UPDATE_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						UserMonthBill uo = userMonthBills.get(i);
						int num = 1;
						ps.setBigDecimal(num++, uo.getCurBalance());
						ps.setBigDecimal(num++, uo.getLastBalance());
						ps.setBigDecimal(num++, uo.getPaid());
						ps.setBigDecimal(num++, uo.getLastLnterest());
						ps.setBigDecimal(num++, uo.getCurLnterest());
						ps.setInt(num++, uo.getStatus());
						ps.setInt(num++, uo.getId());
					}

					@Override
					public int getBatchSize() {
						return userMonthBills.size();
					}
				});
		return count.length == userMonthBills.size();
	}

	public boolean batchClostLastUserMonthBill() {
		return this.getJdbcTemplate().update(CLOSE_ALL_BILL_SQL) > 0;
	}

	public List<Map<String, Object>> findLastMonthBill() {
		return this.findListBySQL(LAST_MONTH_BILL_SELECT_SQL, null);
	}

	public static final String LAST_MONTH_BILL_SELECT_SQL = "select * from user_month_bill where `status`=0";
	public static final String CLOSE_ALL_BILL_SQL = "update user_month_bill set `status`=1 where `status`=0";
	private static final String INSERT_SQL = "insert into user_month_bill (userId,curBalance,lastBalance,paid,lastLnterest,curLnterest,`status`,repaymentDate,createTime) values(?,?,?,?,?,?,?,?,now())";
	private static final String GET_BY_ID_SQL = "select b.*,u.`name` as userName,u.mobilePhone,u.idCard from user_month_bill b LEFT JOIN `user` u on b.userId=u.id where b.id=? ";
	private static final String UPDATE_SQL = "update user_month_bill set curBalance=?,lastBalance=?,paid=?,lastLnterest=?,curLnterest=?,`status`=? where id=?";

}
