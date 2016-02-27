package com.jxf.car.dao.merchant;

import org.springframework.stereotype.Repository;

import com.jxf.car.dao.BaseDao;
import com.jxf.car.model.MerchantAccountRecord;

@Repository
public class MerchantAccountRecordDao extends BaseDao {

	public Integer create(MerchantAccountRecord accountRecord) {
		return accountRecord.create(this);
	}
}
