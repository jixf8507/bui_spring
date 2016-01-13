package com.jxf.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;

public class SysFileUrlsPO {
	private Integer id;
	private String fileName;
	private String fileType;
	private Integer tableId;
	private String tableName;
	private String fileUrl;
	private Timestamp createdTime;

	public SysFileUrlsPO() {

	}

	public static SysFileUrlsPO create(ResultSet rs) throws SQLException {
		SysFileUrlsPO fileUrlsPO = new SysFileUrlsPO();
		fileUrlsPO.id = rs.getInt("id");
		fileUrlsPO.fileName = rs.getString("fileName");
		fileUrlsPO.fileType = rs.getString("fileType");
		fileUrlsPO.tableId = rs.getInt("tableId");
		fileUrlsPO.tableName = rs.getString("tableName");
		fileUrlsPO.fileUrl = rs.getString("fileUrl");
		return fileUrlsPO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Integer create(BaseDao baseDao) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		baseDao.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, fileName);
				ps.setString(2, fileType);
				ps.setInt(3, tableId);
				ps.setString(4, tableName);
				ps.setString(5, fileUrl);
				ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	private static final String INSERT_SQL = "insert into sys_file_urls (fileName,fileType,tableId,tableName,fileUrl,createdTime) values (?,?,?,?,?,?)";
	public static final String GET_SQL = "select * from  sys_file_urls where id=?";
	public static final String DELETE_SYS_FILE_URL_SQL = "delete from sys_file_urls where id=?";
}
