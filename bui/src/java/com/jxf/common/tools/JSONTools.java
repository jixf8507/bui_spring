package com.jxf.common.tools;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jxf.car.web.SessionUserBO;
import com.jxf.common.base.PageHelp;

/**
 * JSON工具类
 * 
 * @author Administrator
 * 
 */
public class JSONTools {

	/**
	 * 从SJON数组中提取分页参数
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static PageHelp toPageHelp(JSONArray jsonArray) {
		PageHelp pageHelp = new PageHelp();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			if (obj.get("name").equals("sEcho")) {
				pageHelp.setsEcho(obj.get("value").toString());
			}
			if (obj.get("name").equals("iDisplayStart")) {
				pageHelp.setiDisplayStart(obj.getInt("value"));
			}
			if (obj.get("name").equals("iDisplayLength")) {
				pageHelp.setiDisplayLength(obj.getInt("value"));
			}
		}
		return pageHelp;
	}

	/**
	 * 从request中获取分页数据
	 * 
	 * @param request
	 * @return
	 */
	public static PageHelp toPageHelp(HttpServletRequest request) {
		String aoData = request.getParameter("aoData");
		return toPageHelp(aoData);
	}

	/**
	 * 根据字符串解析分页数据
	 * 
	 * @param aoData
	 * @return
	 */
	public static PageHelp toPageHelp(String aoData) {
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		return toPageHelp(jsonArray);
	}

	/**
	 * 从request中解析查询条件
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject getJsonPara(HttpServletRequest request) {
		String paraData = request.getParameter("paraData");
		return getJsonPara(paraData);
	}

	/**
	 * 根据JSON字符串解析查询条件
	 * 
	 * @param paraData
	 * @return
	 */
	public static JSONObject getJsonPara(String paraData) {
		paraData = StringTools.decodeMethod(paraData);
		return JSONObject.fromObject(paraData);
	}

	public static JSONObject getJsonPara(String paraData, SessionUserBO userBO) {
		JSONObject jsonObject = getJsonPara(paraData);
		if (userBO.getMerchant() != null) {
			jsonObject.put("merchantId", userBO.getMerchant().getId());
		}
		return jsonObject;
	}
}
