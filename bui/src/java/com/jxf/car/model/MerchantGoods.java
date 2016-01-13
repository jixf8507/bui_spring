package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

public class MerchantGoods extends BasePO {

	private Integer id;
	private Integer merchantId;
	private String name;
	private BigDecimal price;
	private String img;
	private String des1;
	private String des2;
	private String des3;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDes1() {
		return des1;
	}

	public void setDes1(String des1) {
		this.des1 = des1;
	}

	public String getDes2() {
		return des2;
	}

	public void setDes2(String des2) {
		this.des2 = des2;
	}

	public String getDes3() {
		return des3;
	}

	public void setDes3(String des3) {
		this.des3 = des3;
	}

	public int update(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.merchantId,
				this.name, this.price, this.img, this.des1, this.des2,
				this.des3, this.id);
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
				ps.setInt(1, merchantId);
				ps.setString(2, name);
				ps.setBigDecimal(3, price);
				ps.setString(4, img);
				ps.setString(5, des1);
				ps.setString(6, des2);
				ps.setString(7, des3);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static final String GET_BY_ID_SQL = "select * from merchant_goods g  where g.id=? ";
	private static final String INSERT_SQL = "insert into merchant_goods (merchantId,`name`,price,img,des1,des2,des3) values (?,?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "update merchant_goods set merchantId=?,`name`=?,price=?,img=?,des1=?,des2=?,des3=? where id=?";

}
