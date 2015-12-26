package com.jxf.car.dao.system;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.creator.FileUrlsCreator;
import com.jxf.car.db.extractor.FileUrlsExtractor;
import com.jxf.car.model.SysFileUrlsPO;

@Repository
public class SysFileUrlsDao extends BaseDao {

	public Integer create(SysFileUrlsPO fileUrlsPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new FileUrlsCreator(INSERT_SQL, fileUrlsPO), keyHolder);
		return keyHolder.getKey().intValue();
	}

	public SysFileUrlsPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new FileUrlsExtractor());
	}

	private static final String INSERT_SQL = "insert into sys_file_urls (fileName,fileType,tableId,tableName,fileUrl,createdTime) values (?,?,?,?,?,?)";
	private static final String GET_SQL = "select * from  sys_file_urls where id=?";
	private static final String DELETE_SYS_FILE_URL_SQL = "delete from sys_file_urls where id=?";

	/**
	 * 删除sys_file_url表中的一条记录。
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSysFileUrl(Integer id) {
		return this.getJdbcTemplate().update(DELETE_SYS_FILE_URL_SQL, id) > 0;
	}

}
