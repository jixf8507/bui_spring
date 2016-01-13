package com.jxf.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

public class Merchant extends BasePO {

	private Integer id;
	private String name;
	private String address;
	private String img;
	private String des;
	private String corporation;
	private String mobilePhone;

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

	/**
	 * 修改商家信息
	 * 
	 * @param baseDao
	 * @return
	 */
	public int update(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.name,
				this.address, this.des, this.corporation, this.mobilePhone,
				this.id);
	}

	/**
	 * 新增商家信息
	 * 
	 * @param baseDao
	 * @return
	 */
	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setString(2, address);
				ps.setString(3, des);
				ps.setString(4, corporation);
				ps.setString(5, mobilePhone);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static final String GET_BY_ID_SQL = "select * from merchant u  where u.id=? ";
	private static final String INSERT_SQL = "insert into merchant (name,address,des,corporation,mobilePhone) values (?,?,?,?,?)";
	private static final String UPDATE_SQL = "update merchant set name=?,address=?,des=?,corporation=?,mobilePhone=? where id=?";

}
