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
			<li class="current"><a href="#borrow">商品信息</a></li>
			<li><a href="#user">用户信息</a></li>
		</ul>
	</div>

	<div id="contentwrapper" class="contentwrapper">
		<div id="borrow" class="subcontent">
			<form class="stdform stdform2">
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
				<p>
					<label>状态</label> <span class="field"> <c:if
							test="${userBorrow.status == '1' }">审核中	</c:if> <c:if
							test="${userBorrow.status == '2' }">已通过	</c:if> <c:if
							test="${userBorrow.status == '3' }">未通过	</c:if> &nbsp;&nbsp;
					</span>
				</p>
				<p>
					<label>申请时间</label> <span class="field">
						${userBorrow.createTime}&nbsp;&nbsp; </span>
				</p>
				<c:if test="${userBorrow.status != '1' }">
					<p>
						<label>审核人</label> <span class="field">
							${userBorrow.checkMen}&nbsp;&nbsp; </span>
					</p>
					<p>
						<label>审核说明</label> <span class="field">
							${userBorrow.checkDisc}&nbsp;&nbsp; </span>
					</p>
				</c:if>
			</form>
		</div>

		<div id="user" class="subcontent" style="display: none;">
			<form class="stdform stdform2">
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
			</form>
		</div>
	</div>

</body>
</html>