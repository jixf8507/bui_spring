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
 * 大马花商品
 * 
 * @author Administrator
 * 
 */
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

	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, manufacturer);
				ps.setString(i++, name);
				ps.setBigDecimal(i++, price);
				ps.setString(i++, img);
				ps.setString(i++, des1);
				ps.setString(i++, des2);
				ps.setString(i++, des3);
				ps.setString(i++, type);
				ps.setInt(i++, isTop);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(GET_BY_ID_SQL, new Object[] { id });
	}

	public static int delete(Object id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(DELETE_ID_SQL, id);
	}

	private static final String GET_BY_ID_SQL = "select * from dmh_goods g  where g.id=? ";
	private static final String INSERT_SQL = "insert into dmh_goods (manufacturer,`name`,price,img,des1,des2,des3,type,isTop,status) values (?,?,?,?,?,?,?,?,?,1)";
	private static final String UPDATE_SQL = "update dmh_goods set manufacturer=?,`name`=?,price=?,img=?,des1=?,des2=?,des3=?,type=?,isTop=? where id=?";
	private static final String DELETE_ID_SQL = "update dmh_goods set  status=0 where id=? ";

	/**
	 * 获取商品状态
	 * 
	 * @param status
	 *            商品状态编码
	 * @return
	 */
	public static String getStatus(String status) {
		switch (status) {
		case "0":
			return "已下架";
		case "1":
			return "已上架";
		default:
			return "";
		}
	}

	/**
	 * 是否首页显示
	 */
	public static String getTop(String isTop) {
		switch (isTop) {
		case "0":
			return "否";
		case "1":
			return "是";
		default:
			return "";
		}
	}

}
