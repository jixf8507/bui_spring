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
		try {
			List<Map<String, Object>> list = dmhGoodsDao
					.findMerchantGoodsList(jsonObject);
			DmhGoodsExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MSG createGoods(DmhGoods dmhGoods) {
		try {
			dmhGoodsDao.create(dmhGoods);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "新增商品失败！");
	}

	public MSG updateGoods(DmhGoods dmhGoods) {
		try {
			dmhGoodsDao.update(dmhGoods);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "修改商品失败！");
	}

	public Map<String, Object> getGoodsMap(Integer id) {
		return dmhGoodsDao.getMerchantGoodsMap(id);
	}

	@Transactional
	public MSG deleteGoods(JSONArray jsonArray) {
		try {
			for (Object id : jsonArray) {
				dmhGoodsDao.delete(id);
			}
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "下架商品失败！");
	}

}
