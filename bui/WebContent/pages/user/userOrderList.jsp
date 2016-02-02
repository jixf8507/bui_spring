<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/user/UserOrder.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/user/userOrderList.js"></script>
<body>

	<div id="contentwrapper" class="contentwrapper">
		<div id="list" class="subcontent">
			<div class="overviewhead">
				<div class="overviewselect">
					<select id="type" class="chzn-select" style="width: 200px;"
						tabindex="2">
						<option value="">请选择</option>
						<option value="1">大马花消费</option>
						<option value="2">商家消费</option>
					</select> <select id="status" class="chzn-select" style="width: 200px;"
						tabindex="2">
						<option value="">请选择</option>
						<option value="1">审核中</option>
						<option value="2">已通过</option>
						<option value="3">未通过</option>
						<option value="4">购买中</option>
						<option value="5">已配送</option>
						<option value="6">已收货</option>
					</select>
				</div>
				用户姓名: &nbsp;<input type="text" id="name" /> &nbsp; 手机号码: &nbsp;<input
					type="text" id="mobilePhone" /> &nbsp; 商品名称: &nbsp;<input
					type="text" id="goodsName" /> &nbsp;
				<button class="publishbutton radius3" id="queryBtn">查询</button>
				<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
			</div>
			<br clear="all" />
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="userTable">
				<colgroup>
					<col class="con1" width="20px" />
					<col class="con0" />
					<col class="con1" />
				</colgroup>
				<thead>
					<tr>
						<th class="head1" width="30px"><input type="checkbox"
							style="width: 20px;" name="checkAll" value="" id="checkAll" /></th>
						<th class="head1">订单号</th>
						<th class="head0">用户姓名</th>
						<th class="head1">手机号码</th>
						<th class="head0">商品名称</th>
						<th class="head1">价格</th>
						<th class="head0">商家</th>
						<th class="head1">首付金额</th>
						<th class="head0">订单金额</th>
						<th class="head1">订单状态</th>
						<th class="head0">类型</th>
						<th class="head1">消费时间</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<br /> <br />
		</div>
	</div>
	<br clear="all" />

</body>
</html>