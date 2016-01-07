<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>用户名称</label> <span class="field">
				${userAccount.name}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>手机号码</label> <span class="field">${userAccount.mobilePhone}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证</label> <span class="field">
				${userAccount.idCard}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>可用额度</label> <span class="field">${userAccount.usableLimit}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>白条额度</label> <span class="field">
				${userAccount.whiteBarLimit}&nbsp;&nbsp; </span>
		</p>
	</form>

</body>
</html>