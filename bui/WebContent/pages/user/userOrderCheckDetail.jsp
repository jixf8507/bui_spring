<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/models/user/UserOrder.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/user/userOrderCheckDetail.js"></script>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<fieldset>
			<legend><b>商品信息</b></legend>
			<p>
				<label>商品名称</label> <span class="field">${userOrder.goodName}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>商品图片</label> <span class="field"><img
					src=" ${ctx}/${userOrder.img}" alt="" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>价格</label> <span class="field">
					${userOrder.price}&nbsp;&nbsp; </span>
			</p>
		</fieldset>
		<fieldset>
			<legend><b>用户信息</b></legend>
			<p>
				<label>用户名称</label> <span class="field">
					${userOrder.userName}&nbsp;&nbsp; </span>
			</p>
			<p>
				<label>手机号码</label> <span class="field">${userOrder.mobilePhone}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>身份证</label> <span class="field">
					${userOrder.idCard}&nbsp;&nbsp; </span>
			</p>
		</fieldset>
		<fieldset>
			<legend><b>订单信息</b></legend>
			<p>
				<label>首付金额</label> <span class="field">
					${userOrder.sfMoney}&nbsp;&nbsp; </span>
			</p>
			<p>
				<label>分期数</label> <span class="field">
					${userOrder.aging}&nbsp;&nbsp; </span>
			</p>
			<p>
				<label>消费时间</label> <span class="field">
					${userOrder.createTime}&nbsp;&nbsp; </span>
			</p>
		</fieldset>
		<fieldset>
			<legend><b>购买审核</b></legend>
			<input type="hidden" name="id" value="${userOrder.id}" />
			<p>
				<label>审核状态</label> <span class="field"> <select id="status"
					name="status" class="chzn-select" style="width: 200px;"
					tabindex="2">
						<option value="">请选择</option>
						<option value="2">通过</option>
						<option value="3">不通过</option>
				</select>&nbsp;&nbsp;
				</span>
			</p>
			<label>审核说明</label> <span class="field"> <textarea rows="5"
					cols="6" name="des1"></textarea> &nbsp;&nbsp;
			</span>
		</fieldset>
	</form>

</body>
</html>