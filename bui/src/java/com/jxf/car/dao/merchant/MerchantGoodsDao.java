package com.jxf.car.dao.merchant;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.db.creator.MerchantGoodsCreator;
import com.jxf.car.model.MerchantGoods;
import com.jxf.common.base.PageResults;
import com.jxf.common.sql.JSONSqlMapping;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class MerchantGoodsDao extends BaseDao {

	@Resource(name = "merchant_goods_select_sql")
	private JSONSqlMapping goodsSelectSQL;

	/**
	 * 分页查找系统用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findMerchantGoodsPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(goodsSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	public Map<String, Object> getMerchantGoodsMap(Integer id) {
		return this.get(GET_BY_ID_SQL, new Object[] { id });
	}

	/**
	 * 新增用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	public Integer create(MerchantGoods merchantGoods) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new MerchantGoodsCreator(INSERT_SQL, merchantGoods), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改用户账户
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(MerchantGoods merchantGoods) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				merchantGoods.getMerchantId(), merchantGoods.getName(),
				merchantGoods.getPrice(), merchantGoods.getImg(),
				merchantGoods.getDes1(), merchantGoods.getDes2(),
				merchantGoods.getDes3(), merchantGoods.getId());
		return count > 0;
	}

	private static final String GET_BY_ID_SQL = "select * from merchant_goods g  where g.id=? ";

	private static final String INSERT_SQL = "insert into merchant_goods (merchantId,`name`,price,img,des1,des2,des3) values (?,?,?,?,?,?,?)";

	private static final String UPDATE_SQL = "update merchant_goods set merchantId=?,`name`=?,price=?,img=?,des1=?,des2=?,des3=? where id=?";

}
