package com.jxf.car.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jxf.car.dao.system.SysFileUrlsDao;
import com.jxf.car.model.SysFileUrlsPO;

/**
 * 
 * @author jixf
 * @date 2015年12月25日
 */
@Service
public class UploadService extends BaseService {

	@Resource
	private SysFileUrlsDao fileUrlsDao;

	public SysFileUrlsPO saveFile(SysFileUrlsPO fileUrlsPO) {
		Integer id = fileUrlsDao.create(fileUrlsPO);
		if (id == null) {
			return null;
		}
		fileUrlsPO.setId(id);
		return fileUrlsPO;
	}

}
