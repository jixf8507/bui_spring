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
import com.jxf.car.model.UserBillDetail;
import com.jxf.car.model.UserMonthBill;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

@Repository
public class UserBillDetailDao extends BaseDao {

	@Resource(name = "user_bill_detail_select_sql")
	private JSONSqlMapping billDetailSelectSQL;

	public PageResults findUserBillDetailPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(billDetailSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserBillDetailList(
			JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(billDetailSelectSQL, jsonObject);
	}

	public boolean batchCreateUserBillDetail(
			final List<UserBillDetail> userBillList) {
		int[] count = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						UserBillDetail uo = userBillList.get(i);
						int num = 1;
						ps.setInt(num++, uo.getUserId());
						ps.setInt(num++, uo.getOrderId());
						ps.setString(num++, uo.getOrderTable());
						ps.setBigDecimal(num++, uo.getCapital());
						ps.setBigDecimal(num++, uo.getInterest());
						ps.setBigDecimal(num++, uo.getTotleCost());
						ps.setTimestamp(num++, uo.getRepaymentTime());
					}

					@Override
					public int getBatchSize() {
						return userBillList.size();
					}
				});
		return count.length == userBillList.size();
	}

	public boolean batchUpdateUserBillDetail(
			final List<UserMonthBill> userMonthBills, final String endDate) {
		int[] count = this.getJdbcTemplate().batchUpdate(UPDATE_BILL_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						UserMonthBill bill = userMonthBills.get(i);
						ps.setString(1, bill.getUuid());
						ps.setInt(2, bill.getUserId());
						ps.setObject(3, endDate + " 23:59:59");
					}

					@Override
					public int getBatchSize() {
						return userMonthBills.size();
					}
				});
		return count.length == userMonthBills.size();
	}

	public List<Map<String, Object>> staticsUserMonthBillList(String endDate) {
		return this.findListBySQL(DATE_SELECT_SQL, new Object[] { endDate
				+ " 23:59:59" });
	}

	private static final String INSERT_SQL = "insert into user_bill_detail (userId,orderId,orderTable,capital,interest,totleCost,repaymentTime,`createdTime`) values(?,?,?,?,?,?,?,now())";
	private static final String DATE_SELECT_SQL = "select userId,SUM(capital) capital,SUM(interest) interest,SUM(totleCost) totleCost from user_bill_detail where repaymentTime<=? and monthBillUuid ='' GROUP BY userId";
	private static final String UPDATE_BILL_SQL = "update user_bill_detail set monthBillUuid=? where userId=? and repaymentTime<=? and monthBillUuid =''";

}
