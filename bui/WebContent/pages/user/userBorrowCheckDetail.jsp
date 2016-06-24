<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/models/user/UserBorrow.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/user/userBorrowCheckDetail.js"></script>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<fieldset>
			<legend>
				<b>提现信息</b>
			</legend>
			<p>
				<label>提现金额</label> <span class="field">${userBorrow.cost}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>提现用途</label> <span class="field">${userBorrow.purpose}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>分期数</label> <span class="field">
					${userBorrow.aging}&nbsp;&nbsp; </span>
			</p>
			<p>
				<label>银行名称</label> <span class="field">${userBorrow.bankName}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>银行卡号</label> <span class="field">
					${userBorrow.cardNumber}&nbsp;&nbsp; </span>
			</p>
		</fieldset>
		<fieldset>
			<legend>
				<b>用户信息</b>
			</legend>
			<p>
				<label>用户名称</label> <span class="field">
					${userBorrow.name}&nbsp;&nbsp; </span>
			</p>
			<p>
				<label>手机号码</label> <span class="field">${userBorrow.mobilePhone}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>身份证</label> <span class="field">
					${userBorrow.idCard}&nbsp;&nbsp; </span>
			</p>
		</fieldset>
		<fieldset>
			<legend>
				<b>提现审核</b>
			</legend>
			<input type="hidden" name="id" value="${userBorrow.id}" />
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
					cols="6" name="checkDisc"></textarea> &nbsp;&nbsp;
			</span>
		</fieldset>
	</form>

</body>
</html>