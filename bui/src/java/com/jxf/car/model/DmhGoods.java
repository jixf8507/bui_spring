package com.jxf.car.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

public class DmhGoods extends BasePO {

	private Integer id;
	private String name;
	private BigDecimal price;
	private String img;
	private String detailsImg;
	private String manufacturer = "大马花";
	private String type;
	private Integer isTop;
	private Integer status;
	private String des1;
	private String des2;
	private String des3;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetailsImg() {
		return detailsImg;
	}

	public void setDetailsImg(String detailsImg) {
		this.detailsImg = detailsImg;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.manufacturer,
				this.name, this.price, this.img, this.des1, this.des2,
				this.des3, this.type, this.isTop, this.id);
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
				ps.setString(1, manufacturer);
				ps.setString(2, name);
				ps.setBigDecimal(3, price);
				ps.setString(4, img);
				ps.setString(5, des1);
				ps.setString(6, des2);
				ps.setString(7, des3);
				ps.setString(8, type);
				ps.setInt(9, isTop);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static final String GET_BY_ID_SQL = "select * from dmh_goods g  where g.id=? ";
	private static final String INSERT_SQL = "insert into dmh_goods (manufacturer,`name`,price,img,des1,des2,des3,type,isTop,status) values (?,?,?,?,?,?,?,?,?,1)";
	private static final String UPDATE_SQL = "update dmh_goods set manufacturer=?,`name`=?,price=?,img=?,des1=?,des2=?,des3=?,type=?,isTop=? where id=?";
	public static final String DELETE_ID_SQL = "update dmh_goods set  status=0 where id=? ";

	public static String getStatus(String status) {
		if ("0".equals(status)) {
			return "已下架";
		}
		if ("1".equals(status)) {
			return "已上架";
		}
		return "";
	}

	public static String getTop(String isTop) {
		if ("0".equals(isTop)) {
			return "否";
		}
		if ("1".equals(isTop)) {
			return "是";
		}
		return "";
	}

}
