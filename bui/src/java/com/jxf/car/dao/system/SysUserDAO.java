package com.jxf.car.dao.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.extractor.SysUserExtractor;
import com.jxf.car.model.SysUser;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author jixf
 * @date 2015年11月21日
 */
@Repository
public class SysUserDAO extends BaseDao {

	/**
	 * 根据登录号查找员工
	 * 
	 * @param code
	 * @return
	 */
	public SysUser findByCode(String code) {
		return this.getJdbcTemplate().query(SysUser.GET_BY_CODE_SQL,
				new Object[] { code }, new SysUserExtractor());
	}

	public SysUser get(Integer id) {
		return this.getJdbcTemplate().query(SysUser.GET_BY_ID_SQL,
				new Object[] { id }, new SysUserExtractor());
	}

	@Resource(name = "system_user_select_sql")
	private JSONSqlMapping userSelectSQL;

	/**
	 * 分页查找系统员工信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(userSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findUserList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(userSelectSQL, jsonObject);
	}

	/**
	 * 新增商家工作人员
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public Integer create(SysUser sysUser) {
		return sysUser.create(this);
	}

	/**
	 * 修改商家工作人员信息
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(SysUser sysUser) {
		return sysUser.update(this) > 0;
	}

	/**
	 * 删除商家工作人员
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Object id) {
		int count = this.getJdbcTemplate().update(SysUser.DELETE_SQL, id);
		return count > 0;
	}

}
