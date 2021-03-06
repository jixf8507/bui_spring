package com.jxf.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;
import com.jxf.common.SysConfig;

/**
 * 客户对象
 * 
 * @author Administrator
 * 
 */
public class User extends BasePO {

	private Integer id;
	private String loginPassword;
	private String mobilePhone;
	private String payPassword;
	private Integer status;
	private String statusDesc;
	private String name;
	private String idCard;
	private String idCardImg;
	private String studentIdCardImg;
	private String otherImg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdCardImg() {
		return idCardImg;
	}

	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
	}

	public String getStudentIdCardImg() {
		return studentIdCardImg;
	}

	public void setStudentIdCardImg(String studentIdCardImg) {
		this.studentIdCardImg = studentIdCardImg;
	}

	public String getOtherImg() {
		return otherImg;
	}

	public void setOtherImg(String otherImg) {
		this.otherImg = otherImg;
	}

	public int update(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(UPDATE_SQL, this.getName(),
				this.getMobilePhone(), this.getIdCard(), this.idCardImg,
				this.studentIdCardImg, this.otherImg, this.getId());
	}

	public int updateUserForCheck(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(USER_CHECK_UPDATE_SQL,
				this.getName(), this.getMobilePhone(), this.getIdCard(),
				this.idCardImg, this.studentIdCardImg, this.otherImg,
				this.status, this.getId());
	}

	public static User createUser(Integer status, String statusDesc, Integer id) {
		User user = new User();
		user.setId(id);
		user.setStatusDesc(statusDesc);
		user.setStatus(status);
		return user;
	}

	public int updateStatus(BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(STATUS_UPDATE_SQL, status,
				statusDesc, id);
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
				ps.setString(i++, getName());
				ps.setString(i++, getMobilePhone());
				ps.setString(i++, getIdCard());
				ps.setString(i++, SysConfig.PASSWORD);
				ps.setString(i++, SysConfig.PASSWORD);
				return ps;
			}
		}, keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	public static Map<String, Object> get(Integer id, BaseDao baseDao) {
		return baseDao.get(GET_BY_ID_SQL, new Object[] { id });
	}

	private static final String USER_CHECK_UPDATE_SQL = "update user set name=?,mobilePhone=?,idCard=?,idCardImg=?,studentIdCardImg=?,otherImg=?,status=? where id=?";
	private static final String UPDATE_SQL = "update user set name=?,mobilePhone=?,idCard=?,idCardImg=?,studentIdCardImg=?,otherImg=? where id=?";
	private static final String STATUS_UPDATE_SQL = "update user set status=?,statusDesc=? where id=?";
	private static final String INSERT_SQL = "insert into user (name,mobilePhone,idCard,loginPassword,payPassword,lastVisitorTime,`status`,statusDesc) values (?,?,?,?,?,now(),1,'')";
	private static final String GET_BY_ID_SQL = "select * from user u  where u.id=? ";
}
