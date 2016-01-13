package com.jxf.car.dao.system;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.FileUrlsExtractor;
import com.jxf.car.model.SysFileUrlsPO;

@Repository
public class SysFileUrlsDao extends BaseDao {

	public Integer create(SysFileUrlsPO fileUrlsPO) {
		return fileUrlsPO.create(this);
	}

	public SysFileUrlsPO get(Integer id) {
		return this.getJdbcTemplate().query(SysFileUrlsPO.GET_SQL,
				new Object[] { id }, new FileUrlsExtractor());
	}

	/**
	 * 删除sys_file_url表中的一条记录。
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSysFileUrl(Integer id) {
		return this.getJdbcTemplate().update(
				SysFileUrlsPO.DELETE_SYS_FILE_URL_SQL, id) > 0;
	}

}
