package com.jxf.car.dao.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.SysFileUrlsPO;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

@Repository
public class SysFileUrlsDao extends BaseDao {

	@Resource(name = "system_file_url_select_sql")
	private JSONSqlMapping urlSelectSQL;

	public List<Map<String, Object>> findUrlList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(urlSelectSQL, jsonObject);
	}

	public PageResults findUrlPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(urlSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public Integer create(SysFileUrlsPO fileUrlsPO) {
		return fileUrlsPO.create(this);
	}

	public SysFileUrlsPO get(Integer id) {
		return SysFileUrlsPO.get(id, this);
	}

	/**
	 * 删除sys_file_url表中的一条记录。
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSysFileUrl(Object id) {
		return SysFileUrlsPO.delete(id, this) > 0;
	}

}
