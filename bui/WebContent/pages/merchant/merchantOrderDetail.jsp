<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<form class="stdform stdform2">
		<p>
			<label>用户名称</label> <span class="field">
				${merchantOrder.userName}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>手机号码</label> <span class="field">${merchantOrder.mobilePhone}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证</label> <span class="field">
				${merchantOrder.idCard}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>商家</label> <span class="field">${merchantOrder.merchantName}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>消费金额</label> <span class="field">
				${merchantOrder.cost}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>分期数</label> <span class="field">
				${merchantOrder.aging}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>消费时间</label> <span class="field">
				${merchantOrder.createTime}&nbsp;&nbsp; </span>
		</p>
	</form>

</body>
</html>