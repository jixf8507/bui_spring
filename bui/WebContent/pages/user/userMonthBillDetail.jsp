<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/plugins/general.js"></script>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<div class="pageheader">
		<ul class="hornav">
			<li class="current"><a href="#bill">用户信息</a></li>
			<li><a href="#user">账单信息</a></li>
		</ul>
	</div>

	<div id="contentwrapper" class="contentwrapper">


		<div id="bill" class="subcontent">
			<form class="stdform stdform2">
				<p>
					<label>还款日期</label> <span class="field">${userMonthBill.repaymentDate}&nbsp;&nbsp;</span>
				</p>

				<p>
					<label>本期应还本金</label> <span class="field">
						${userMonthBill.curBalance}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>上期欠款本金</label> <span class="field">
						${userMonthBill.lastBalance}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>本期已还金额</label> <span class="field">
						${userMonthBill.paid}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>上期账单利息</label> <span class="field">
						${userMonthBill.lastLnterest}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>本期利息</label> <span class="field">
						${userMonthBill.curLnterest}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>消费类型</label> <span class="field"> <c:if
							test="${userMonthBill.status == '0' }">未还清	</c:if> <c:if
							test="${userMonthBill.status == '1' }">已还清	</c:if> &nbsp;&nbsp;
					</span>
				</p>
				<p>
					<label>账单创建时间</label> <span class="field">
						${userMonthBill.createTime}&nbsp;&nbsp; </span>
				</p>
			</form>
		</div>

		<div id="user" class="subcontent" style="display: none;">
			<form class="stdform stdform2">
				<p>
					<label>用户名称</label> <span class="field">
						${userMonthBill.userName}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>手机号码</label> <span class="field">${userMonthBill.mobilePhone}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>身份证</label> <span class="field">
						${userMonthBill.idCard}&nbsp;&nbsp; </span>
				</p>
			</form>
		</div>
	</div>

</body>
</html>