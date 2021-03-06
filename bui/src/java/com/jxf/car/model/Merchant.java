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
import com.jxf.car.db.extractor.MerchantExtractor;

/**
 * 商家
 * 
 * @author Administrator
 * 
 */
public class Merchant extends BasePO {

	private Integer id;
	private String name;
	private String address;
	private String img;
	private String des;
	private String corporation;
	private String mobilePhone;
	private String code;
	private String password = "123456";
	private BigDecimal totalMoney;
	private BigDecimal freezeMoney;
	
	private Integer status;

	public static Merchant create(ResultSet rs) throws SQLException {
		Merchant merchant = new Merchant();
		merchant.id = rs.getInt("id");
		merchant.code = rs.getString("code");
		merchant.name = rs.getString("name");
		merchant.password = rs.getString("password");
		merchant.address = rs.getString("address");
		merchant.img = rs.getString("img");
		merchant.des = rs.getString("des");
		merchant.corporation = rs.getString("corporation");
		merchant.mobilePhone = rs.getString("mobilePhone");
		merchant.totalMoney = rs.getBigDecimal("totalMoney");
		merchant.freezeMoney = rs.getBigDecimal("freezeMoney");
		merchant.status = rs.getInt("status");
		return merchant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(BigDecimal freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean checkPassword(String password) {
		if (this.password.equals(password)) {
			return true;
		}
		return false;
	}

	public int update(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.img,
				this.name, this.address, this.des, this.corporation,
				this.mobilePhone, this.code, this.id);
	}

	public int updatePassword(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_PASSWORD_SQL,
				this.password, this.id);
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
				ps.setString(i++, img);
				ps.setString(i++, name);
				ps.setString(i++, address);
				ps.setString(i++, des);
				ps.setString(i++, corporation);
				ps.setString(i++, mobilePhone);
				ps.setString(i++, code);
				ps.setString(i++, password);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(GET_BY_ID_SQL, new Object[] { id });
	}

	public static Merchant get(String code, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_BY_CODE_SQL,
				new Object[] { code }, new MerchantExtractor());
	}

	public static Merchant getById(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_BY_ID_SQL,
				new Object[] { id }, new MerchantExtractor());
	}

	public static int freezeMoney(BigDecimal money, Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(FREEZE_MONEY_SQL, money, money,
				id);
	}
	
	public static int drawMoney(BigDecimal money, Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(DRAW_MONEY_SQL, money,
				id);
	}

	private static final String GET_BY_ID_SQL = "select * from merchant u  where u.id=? ";
	private static final String GET_BY_CODE_SQL = "select * from merchant u  where u.code=? ";
	private static final String INSERT_SQL = "insert into merchant (img,name,address,des,corporation,mobilePhone,code,password) values (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "update merchant set img=?,name=?,address=?,des=?,corporation=?,mobilePhone=?,code=? where id=?";
	private static final String UPDATE_PASSWORD_SQL = "update merchant set password=? where id=?";
	private static final String FREEZE_MONEY_SQL = "update merchant set freezeMoney=freezeMoney+?,totalMoney=totalMoney-? where id=?";
	private static final String DRAW_MONEY_SQL = "update merchant set freezeMoney=freezeMoney-? where id=?";
	public static final String getStatus(String statusCode) {
		switch (statusCode) {
		case "0":
			return "冻结";
		case "1":
			return "正常";
		default:
			return "其它";
		}
	}

}
