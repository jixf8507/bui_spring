package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.UserAccountExtractor;

/**
 * 客户账户
 * 
 * @author Administrator
 * 
 */
public class UserAccount extends BasePO {

	private Integer id;
	private Integer userId;
	private BigDecimal usableLimit;
	private BigDecimal curUsableLimit;
	private BigDecimal whiteBarLimit;
	private BigDecimal curWhiteBarLimit;
	private BigDecimal balance;
	private String statementDate;
	private String repaymentDate;
	private int status;

	public static UserAccount create(ResultSet rs) throws SQLException {
		UserAccount ua = new UserAccount();
		ua.id = rs.getInt("id");
		ua.userId = rs.getInt("userId");
		ua.usableLimit = rs.getBigDecimal("usableLimit");
		ua.curUsableLimit = rs.getBigDecimal("curUsableLimit");
		ua.whiteBarLimit = rs.getBigDecimal("whiteBarLimit");
		ua.curWhiteBarLimit = rs.getBigDecimal("curWhiteBarLimit");
		ua.balance = rs.getBigDecimal("balance");
		ua.statementDate = rs.getString("statementDate");
		ua.repaymentDate = rs.getString("repaymentDate");
		return ua;
	}

	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getUsableLimit() {
		return usableLimit;
	}

	public void setUsableLimit(BigDecimal usableLimit) {
		this.usableLimit = usableLimit;
	}

	public BigDecimal getCurUsableLimit() {
		return curUsableLimit;
	}

	public void setCurUsableLimit(BigDecimal curUsableLimit) {
		this.curUsableLimit = curUsableLimit;
	}

	public BigDecimal getWhiteBarLimit() {
		return whiteBarLimit;
	}

	public void setWhiteBarLimit(BigDecimal whiteBarLimit) {
		this.whiteBarLimit = whiteBarLimit;
	}

	public BigDecimal getCurWhiteBarLimit() {
		return curWhiteBarLimit;
	}

	public void setCurWhiteBarLimit(BigDecimal curWhiteBarLimit) {
		this.curWhiteBarLimit = curWhiteBarLimit;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int update(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(getUpdateSQL(),
				this.usableLimit, this.whiteBarLimit, this.id);
	}

	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(getInsertSQL(),
						PreparedStatement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setInt(i++, userId);
				ps.setBigDecimal(i++, usableLimit);
				ps.setBigDecimal(i++, usableLimit);
				ps.setBigDecimal(i++, whiteBarLimit);
				ps.setBigDecimal(i++, whiteBarLimit);
				return ps;
			}
		}, keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(getLoadSQL(), new Object[] { id });
	}

	public static UserAccount getByUserId(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_BY_USER_ID_SQL,
				new Object[] { id }, new UserAccountExtractor());
	}

	public static int addCurUsableLimit(BigDecimal balance, Integer id,
			BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(ADD_CUR_USABLE_LIMIT_SQL,
				new Object[] { balance, id });
	}

	public static boolean batchAddCurUsableLimit(
			final List<UserAccount> accounts, BaseDao baseDao) {
		int[] count = baseDao.getJdbcTemplate().batchUpdate(
				ADD_CUR_USABLE_LIMIT_SQL, new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						UserAccount uo = accounts.get(i);
						int num = 1;
						ps.setBigDecimal(num++, uo.getCurUsableLimit());
						ps.setInt(num++, uo.getId());
					}

					@Override
					public int getBatchSize() {
						return accounts.size();
					}
				});
		return count.length == accounts.size();
	}

	public static String getStatus(String accountStatus) {
		switch (accountStatus) {
		case "1":
			return "正常";
		case "2":
			return "不可取现可分期";
		case "3":
			return "不可取现不可分期";
		default:
			return "其它";
		}
	}

	private static final String getLoadSQL() {
		return "select a.*,u.`name`,u.mobilePhone,u.idCard from user_account a INNER JOIN user u on a.userId=u.id where a.id=? ";
	}

	private static final String getInsertSQL() {
		return "insert into user_account (userId,usableLimit,curUsableLimit,whiteBarLimit,curWhiteBarLimit,statementDate,repaymentDate,`status`) values (?,?,?,?,?,now(),now(),1)";
	}

	private static final String getUpdateSQL() {
		return "update user_account set usableLimit=?,whiteBarLimit=? where id=?";
	}

	public static final String GET_BY_USER_ID_SQL = "select * from user_account where userId=?";
	public static final String ADD_CUR_USABLE_LIMIT_SQL = "update user_account set curUsableLimit=curUsableLimit+? where id=?";
}
