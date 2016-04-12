package com.jxf.car.dao.customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.UserBillDetail;

@Repository
public class UserBillDetailDao extends BaseDao {

	public boolean batchCreateUserBillDetail(
			final List<UserBillDetail> userBillList) {
		int[] count = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						UserBillDetail uo = userBillList.get(i);
						ps.setInt(1, uo.getUserId());
						ps.setInt(2, uo.getOrderId());
						ps.setBigDecimal(3, uo.getCapital());
						ps.setBigDecimal(4, uo.getInterest());
						ps.setBigDecimal(5, uo.getTotleCost());
						ps.setTimestamp(6, uo.getRepaymentTime());
					}

					@Override
					public int getBatchSize() {
						return userBillList.size();
					}
				});
		return count.length == userBillList.size();
	}
	
	public List<Map<String, Object>> staticsUserMonthBillList(String biginDate,String endDate){
		return this.findListBySQL(DATE_SELECT_SQL, new Object[]{biginDate,endDate+" 23:59:59"}) ;
	}

	private static final String INSERT_SQL = "insert into user_bill_detail (userId,orderId,capital,interest,totleCost,repaymentTime) values(?,?,?,?,?,?)";
	private static final String DATE_SELECT_SQL = "select userId,SUM(capital) capital,SUM(interest) interest,SUM(totleCost) totleCost from user_bill_detail where repaymentTime >= ? and repaymentTime<=? GROUP BY userId";

}
