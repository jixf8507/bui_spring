package com.jxf.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import net.sf.json.JSONArray;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.FileUrlsExtractor;

/**
 * 系统文件
 * 
 * @author Administrator
 * 
 */
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
				int i = 1;
				ps.setString(i++, fileName);
				ps.setString(i++, fileType);
				ps.setInt(i++, tableId);
				ps.setString(i++, tableName);
				ps.setString(i++, fileUrl);
				ps.setTimestamp(i++, new Timestamp(System.currentTimeMillis()));
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public static SysFileUrlsPO get(Integer id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new FileUrlsExtractor());
	}

	public static int delete(Object id, BaseDao baseDao) {
		return baseDao.getJdbcTemplate().update(DELETE_SYS_FILE_URL_SQL, id);
	}

	public static boolean batchUpdateSorts(final JSONArray ids,
			final JSONArray sorts, BaseDao baseDao) {
		if (ids.size() != sorts.size()) {
			return false;
		}
		int[] count = baseDao.getJdbcTemplate().batchUpdate(UPDATE_SORTS_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setObject(1, sorts.get(i));
						ps.setObject(2, ids.get(i));
					}

					@Override
					public int getBatchSize() {
						return ids.size();
					}
				});
		return count.length == ids.size();
	}

	private static final String INSERT_SQL = "insert into sys_file_urls (fileName,fileType,tableId,tableName,fileUrl,createdTime) values (?,?,?,?,?,?)";
	private static final String GET_SQL = "select * from  sys_file_urls where id=?";
	private static final String DELETE_SYS_FILE_URL_SQL = "delete from sys_file_urls where id=?";
	private static final String UPDATE_SORTS_SQL = "update sys_file_urls set sort=? where id=?";
}
