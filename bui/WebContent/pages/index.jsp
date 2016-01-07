<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>大马花</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/assets/css/dpl-min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/assets/css/bui-min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/assets/css/main-min.css" rel="stylesheet"
	type="text/css" />
</head>
<script type="text/javascript">
	var contextPath = '${ctx}';
</script>
<script type="text/javascript"
	src="${ctx}/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/bui.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/config.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/index.js"></script>

<body>

	<div class="header">

		<div class="dl-title">
			<span class="lp-title-port">大马花.</span><span class="dl-title-text">管理平台</span>
		</div>

		<div class="dl-log">
			欢迎您，<span class="dl-log-user">${sessionScope.webUser.sysUser.name}</span><a
				href="${ctx}/login/out.htm" title="退出系统" class="dl-log-quit">[退出]</a><a
				href="#" title="文档库" class="dl-log-quit">帮助文档</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					贴心小秘书<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">首页</div></li>
				<li class="nav-item"><div class="nav-item-inner nav-order">表单页</div></li>
				<li class="nav-item"><div class="nav-item-inner nav-inventory">搜索页</div></li>
				<li class="nav-item"><div class="nav-item-inner nav-supplier">详情页</div></li>
				<li class="nav-item"><div class="nav-item-inner nav-marketing">图表</div></li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>


</body>
</html>