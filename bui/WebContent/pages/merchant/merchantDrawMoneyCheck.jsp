<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript"
	src="${ctx}/js/models/merchant/MerchantDrawMoney.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/merchant/merchantDrawMoneyCheck.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<input type="hidden" name="id" value="${drawMoney.id}" />
		<p>
			<label>商家名称</label> <span class="field">
				${drawMoney.name}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>商家登录号</label> <span class="field">
				${drawMoney.code}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>提款金额</label> <span class="field">${drawMoney.money}&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>备注说明</label> <span class="field">${drawMoney.remarks}&nbsp;&nbsp;</span>
		</p>

		<p>
			<label>银行名称</label> <span class="field">${drawMoney.bankName}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>银行卡号</label> <span class="field">
				${drawMoney.cardNumber} &nbsp;&nbsp; </span>
		</p>
		<p>
			<label>申请时间</label> <span class="field">${drawMoney.createdTime}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>审核状态</label> <span class="field"> <select id="status"
				name="status" class="chzn-select" style="width: 200px;"
				tabindex="2">
					<option value="">请选择</option>
					<option value="1">通过</option>
					<option value="2">不通过</option>
			</select>&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>审核说明</label> <span class="field"> <input type="text"
				name="checkRemarks" id="checkRemarks" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>