package com.jxf.car.service.dmh;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.dmh.DmhGoodsDao;
import com.jxf.car.export.dmh.DmhGoodsExport;
import com.jxf.car.model.DmhGoods;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.BaseExport;
import com.jxf.common.base.PageResults;

@Service
public class DmhGoodsService extends BaseService {

	@Autowired
	private DmhGoodsDao dmhGoodsDao;

	public PageResults findGoodsPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return dmhGoodsDao.findMerchantGoodsPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelGoods(HttpServletResponse response, JSONObject jsonObject) {
		BaseExport export = new DmhGoodsExport();
		List<Map<String, Object>> list = dmhGoodsDao
				.findMerchantGoodsList(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MSG createGoods(DmhGoods dmhGoods) {
		MSG msg = new MSG();
		try {
			dmhGoodsDao.create(dmhGoods);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setInfo("新增商品失败");
		}
		return msg;
	}

	public MSG updateGoods(DmhGoods dmhGoods) {
		MSG msg = new MSG();
		try {
			dmhGoodsDao.update(dmhGoods);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setInfo("修改商品失败");
		}
		return msg;
	}

	public Map<String, Object> getGoodsMap(Integer id) {
		return dmhGoodsDao.getMerchantGoodsMap(id);
	}

	@Transactional
	public MSG deleteGoods(JSONArray jsonArray) {
		MSG msg = new MSG();
		try {
			for (Object id : jsonArray) {
				dmhGoodsDao.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setInfo("下架商品失败！");
		}
		return msg;
	}

}
