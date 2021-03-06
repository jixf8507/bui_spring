package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

/**
 * 商家商品
 * 
 * @author Administrator
 * 
 */
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
				ps.setString(i++, name);
				ps.setBigDecimal(i++, price);
				ps.setString(i++, img);
				ps.setString(i++, des1);
				ps.setString(i++, des2);
				ps.setString(i++, des3);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(GET_BY_ID_SQL, new Object[] { id });
	}

	private static final String GET_BY_ID_SQL = "select * from merchant_goods g  where g.id=? ";
	private static final String INSERT_SQL = "insert into merchant_goods (merchantId,`name`,price,img,des1,des2,des3) values (?,?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "update merchant_goods set merchantId=?,`name`=?,price=?,img=?,des1=?,des2=?,des3=? where id=?";

}
