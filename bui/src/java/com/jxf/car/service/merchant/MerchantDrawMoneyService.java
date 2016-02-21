package com.jxf.car.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.merchant.MerchantDao;
import com.jxf.car.dao.merchant.MerchantDrawMoneyDao;
import com.jxf.car.export.mechant.MerchantDrawMoneyExport;
import com.jxf.car.model.Merchant;
import com.jxf.car.model.MerchantDrawMoney;
import com.jxf.car.service.BaseService;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageResults;

@Service
public class MerchantDrawMoneyService extends BaseService {

	@Autowired
	private MerchantDrawMoneyDao drawMoneyDao;
	@Autowired
	private MerchantDao merchantDao;

	public PageResults findMerchantDrawMoneyPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return drawMoneyDao.findMerchantDrawMoneyPage(jsonObject, pageSize,
				iDisplayStart);
	}

	public void excelMerchantDrawMoney(HttpServletResponse response,
			JSONObject jsonObject) {
		try {
			List<Map<String, Object>> list = drawMoneyDao
					.findMerchantDrawMoneyList(jsonObject);
			MerchantDrawMoneyExport.createExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public MSG createMerchantDrawMoney(MerchantDrawMoney drawMoney) {
		try {
			Merchant merchant = merchantDao.getMerchant(drawMoney
					.getMerchantId());
			if(merchant.getStatus()!=1){
				return MSG.createErrorMSG(1, "该商家状态不能申请提款");
			}
			if (merchant.getTotalMoney().compareTo(drawMoney.getMoney()) < 0) {
				return MSG.createErrorMSG(1, "商家余额不足");
			}
			drawMoneyDao.create(drawMoney);
			merchantDao.freezeMoney(drawMoney.getMoney(),
					drawMoney.getMerchantId());
			return MSG.createSuccessMSG();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MSG.createErrorMSG(1, "新增商家失败");
	}

	public Map<String, Object> getMerchantDrawMoneyMap(Integer id) {
		return drawMoneyDao.getMerchantDrawMoneyMap(id);
	}

}
