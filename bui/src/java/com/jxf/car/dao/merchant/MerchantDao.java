package com.jxf.car.dao.merchant;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.creator.MerchantCreator;
import com.jxf.car.model.Merchant;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class MerchantDao extends BaseDao {

	@Resource(name = "merchant_merchant_select_sql")
	private JSONSqlMapping merchantSelectSQL;

	/**
	 * 分页查找系统用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findMerchantPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(merchantSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public List<Map<String, Object>> findMerchantList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(merchantSelectSQL, jsonObject);
	}

	public Map<String, Object> getMerchantMap(Integer id) {
		return this.get(GET_BY_ID_SQL, new Object[] { id });
	}

	/**
	 * 新增用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	public Integer create(Merchant merchant) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new MerchantCreator(INSERT_SQL, merchant), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改用户账户
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(Merchant merchant) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				merchant.getName(), merchant.getAddress(), merchant.getDes(),
				merchant.getCorporation(), merchant.getMobilePhone(),
				merchant.getId());
		return count > 0;
	}

	private static final String GET_BY_ID_SQL = "select * from merchant u  where u.id=? ";

	private static final String INSERT_SQL = "insert into merchant (name,address,des,corporation,mobilePhone) values (?,?,?,?,?)";

	private static final String UPDATE_SQL = "update merchant set name=?,address=?,des=?,corporation=?,mobilePhone=? where id=?";

}