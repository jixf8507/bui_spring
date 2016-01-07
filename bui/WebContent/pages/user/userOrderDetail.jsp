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
			<li class="current"><a href="#user">用户信息</a></li>
			<li><a href="#goods">商品信息</a></li>
			<li><a href="#order">消费详情</a></li>
		</ul>
	</div>

	<div id="contentwrapper" class="contentwrapper">
		<div id="user" class="subcontent">
			<form class="stdform stdform2">
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
			</form>
		</div>

		<div id="goods" class="subcontent" style="display: none;">
			<form class="stdform stdform2">
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
				<p>
					<label>商品描述1</label> <span class="field">
						${userOrder.des1}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>商品描述2</label> <span class="field">
						${userOrder.des2}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>商品描述3</label> <span class="field">
						${userOrder.des3}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>商家</label> <span class="field">
						${userOrder.merchantName}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>商家地址</label> <span class="field">
						${userOrder.address}&nbsp;&nbsp; </span>
				</p>
			</form>
		</div>
		<div id="order" class="subcontent" style="display: none;">
			<form id="form1" class="stdform stdform2" method="post" action="">
				<p>
					<label>首付金额</label> <span class="field">
						${userOrder.sfMoney}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>消费时间</label> <span class="field">
						${userOrder.createTime}&nbsp;&nbsp; </span>
				</p>
				<p>
					<label>消费类型</label> <span class="field"> <c:if
							test="${userOrder.type == '1' }">大马花消费	</c:if> <c:if
							test="${userOrder.type == '2' }">商家消费	</c:if> &nbsp;&nbsp;
					</span>
				</p>
				<p>
					<label>订单状态</label> <span class="field"> <c:if
							test="${userOrder.status == '1' }">审核中	</c:if> <c:if
							test="${userOrder.status == '2' }">已通过	</c:if> <c:if
							test="${userOrder.status == '3' }">未通过	</c:if> <c:if
							test="${userOrder.status == '4' }">购买中	</c:if> <c:if
							test="${userOrder.status == '5' }">已配送	</c:if> <c:if
							test="${userOrder.status == '5' }">已收货	</c:if> &nbsp;&nbsp;
					</span>
				</p>
			</form>
		</div>
	</div>

</body>
</html>