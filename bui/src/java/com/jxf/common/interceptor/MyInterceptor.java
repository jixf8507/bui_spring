package com.jxf.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jxf.common.SysConfig;

/**
 * 系统过滤器
 * 
 * @author Administrator
 * 
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("=========================>>请求 url " + url);
		if (url.indexOf("login") != -1 || url.indexOf("upload") != -1) {
			return true;
		}

		Object obj = request.getSession().getAttribute(SysConfig.SESSION_USER);

		if (obj == null) {
			PrintWriter out = response.getWriter();
			StringBuilder sb = new StringBuilder();
			sb.append("<script type='text/javascript' charset='utf-8'>");
			sb.append("alert('页面过期，请重新登录');");
			sb.append("window.location.href='" + request.getContextPath()
					+ "/index.html';");
			sb.append("</script>");
			out.print(sb.toString());
			out.close();
			return false;
		}

		return super.preHandle(request, response, handler);
	}

}
