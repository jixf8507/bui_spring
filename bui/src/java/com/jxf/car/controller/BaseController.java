package com.jxf.car.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jxf.car.web.SessionUserBO;
import com.jxf.common.SysConfig;

public class BaseController {

	protected static String datafomat = "yyyy-MM-dd";
	protected static Logger logger = Logger.getLogger(BaseController.class);

	/**
	 * 从session中获取登录用户信息
	 * 
	 * @param session
	 * @return
	 */
	public SessionUserBO getSesseionUser(HttpSession session) {
		return (SessionUserBO) session.getAttribute(SysConfig.SESSION_USER);
	}

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public String getJsonStr(Object obj) {
		Gson gson = new GsonBuilder().setDateFormat(datafomat).create();
		String json = null;
		try {
			json = gson.toJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("json " + json);
		return json;
	}

	public static void render(HttpServletResponse response,
			final String contentType, final String content,
			final String... headers) {
		try {
			// 分析headers参数
			String encoding = SysConfig.UTF8;
			boolean noCache = SysConfig.NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName,
						SysConfig.ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName,
						SysConfig.NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName
							+ "不是一个合法的header类型");
			}
			// 设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
