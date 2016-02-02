package com.jxf.car.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxf.car.dao.system.SysFileUrlsDao;
import com.jxf.car.model.SysFileUrlsPO;
import com.jxf.car.web.MSG;
import com.jxf.common.base.PageResults;

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

	public List<Map<String, Object>> findUrlList(JSONObject jsonObject) {
		return fileUrlsDao.findUrlList(jsonObject);
	}

	public PageResults findUrlPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return fileUrlsDao.findUrlPage(jsonObject, pageSize, iDisplayStart);
	}

	@Transactional
	public MSG deledteFileUrl(JSONArray jsonArray) {
		try {
			for (Object id : jsonArray) {
				fileUrlsDao.deleteSysFileUrl(id);
			}
			return MSG.createSuccessMSG();
		} catch (Exception e) {
		}
		return MSG.createErrorMSG(1, "删除图片失败！");
	}

}
