package com.jxf.car.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxf.car.dao.merchant.MerchantGoodsDao;
import com.jxf.car.export.mechant.MerchantGoodsExport;
import com.jxf.car.model.MerchantGoods;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageResults;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class MerchantGoodsService extends BaseService {

	@Autowired
	private MerchantGoodsDao merchantGoodsDao;

	public PageResults findMerchantGoodsPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return merchantGoodsDao.findMerchantGoodsPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelMerchantGoods(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = merchantGoodsDao
					.findMerchantGoodsList(jsonObject);
			MerchantGoodsExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MSG createMerchantGoods(MerchantGoods merchantGoods) {
		try {
			merchantGoodsDao.create(merchantGoods);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
		}
		return MSG.createErrorMSG(1, "新增商家失败");
	}

	public MSG updateMerchantGoods(MerchantGoods merchantGoods) {
		try {
			merchantGoodsDao.update(merchantGoods);
			return MSG.createSuccessMSG();
		} catch (Exception e) {
		}
		return MSG.createErrorMSG(1, "修改商家失败");
	}

	public Map<String, Object> getMerchantGoodsMap(Integer id) {
		return merchantGoodsDao.getMerchantGoodsMap(id);
	}

}
