package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.MerchantDrawMoneyExtractor;

public class MerchantDrawMoney {

	private Integer id;
	private Integer merchantId;
	private BigDecimal money;
	private String remarks;
	private String bankName;
	private String cardNumber;
	private Integer status;
	private String checkMan;
	private String checkRemarks;

	public static MerchantDrawMoney create(ResultSet rs) throws SQLException {
		MerchantDrawMoney drawMoney = new MerchantDrawMoney();
		drawMoney.id = rs.getInt("id");
		drawMoney.merchantId = rs.getInt("merchantId");
		drawMoney.money = rs.getBigDecimal("money");
		drawMoney.remarks = rs.getString("remarks");
		drawMoney.bankName = rs.getString("bankName");
		drawMoney.cardNumber = rs.getString("cardNumber");
		drawMoney.checkMan = rs.getString("checkMan");
		drawMoney.checkRemarks = rs.getString("checkRemarks");
		drawMoney.status = rs.getInt("status");
		return drawMoney;
	}

	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setInt(i++, merchantId);
				ps.setBigDecimal(i++, money);
				ps.setString(i++, remarks);
				ps.setString(i++, bankName);
				ps.setString(i++, cardNumber);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(GET_BY_ID_SQL, new Object[] { id });
	}

	public static MerchantDrawMoney getDrawMoney(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_BY_ID_SQL,
				new Object[] { id }, new MerchantDrawMoneyExtractor());
	}

	public int updateForCheck(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_FOR_CHECK_SQL,
				this.status, this.checkMan, this.checkRemarks, id);
	}

	public static final String getStatus(String statusCode) {
		switch (statusCode) {
		case "1":
			return "申请中";
		case "2":
			return "完成提款";
		case "3":
			return "不通过";
		case "4":
			return "已取消";
		default:
			return "其它";
		}
	}

	private static final String GET_BY_ID_SQL = "select d.*,m.`name`,m.`code` from merchant_draw_money d,merchant m   where d.merchantId=m.id and d.id=?";
	private static final String INSERT_SQL = "INSERT INTO merchant_draw_money(merchantId,money,remarks,bankName,cardNumber,`status`,createdTime,updatedTime) values (?,?,?,?,?,0,NOW(),NOW())";
	private static final String UPDATE_FOR_CHECK_SQL = "UPDATE merchant_draw_money SET `status`=?,checkMan=?,checkRemarks=?,updatedTime=NOW() WHERE id=?";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public String getCheckRemarks() {
		return checkRemarks;
	}

	public void setCheckRemarks(String checkRemarks) {
		this.checkRemarks = checkRemarks;
	}

}
