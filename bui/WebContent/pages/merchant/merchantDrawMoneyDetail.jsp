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
			<label>状态</label> <span class="field"> <c:if
					test="${drawMoney.status == '0' }">申请中	</c:if> <c:if
					test="${drawMoney.status == '1' }">完成提款	</c:if> <c:if
					test="${drawMoney.status == '2' }">不通过	</c:if> <c:if
					test="${drawMoney.status == '4' }">已取消	</c:if> &nbsp;&nbsp;
			</span>
		</p>
		<c:if test="${drawMoney.status != '0' }">
			<p>
				<label>审核人</label> <span class="field">
					${drawMoney.checkMen}&nbsp;&nbsp; </span>
			</p>
			<p>
				<label>审核说明</label> <span class="field">
					${drawMoney.checkDisc}&nbsp;&nbsp; </span>
			</p>
			<p>
				<label>审核时间</label> <span class="field">${drawMoney.updatedTime}&nbsp;&nbsp;</span>
			</p>
		</c:if>
	</form>

</body>
</html>