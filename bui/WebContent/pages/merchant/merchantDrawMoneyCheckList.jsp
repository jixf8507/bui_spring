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
	src="${ctx}/js/pages/merchant/merchantDrawMoneyCheckList.js"></script>
<body>

	<div id="contentwrapper" class="contentwrapper">
		<div id="list" class="subcontent">
			<div class="overviewhead">
				<div class="overviewselect">
					<select id="status" class="chzn-select" style="width: 200px;"
						tabindex="2">
						<option value="">请选择提现状态</option>
						<option value="0">审核中</option>
						<option value="1">完成提款</option>
						<option value="2">不通过</option>
						<option value="3">已取消</option>
					</select>
				</div>
				商户名称: &nbsp;<input type="text" id="name" /> &nbsp; 负责人: &nbsp;<input
					type="text" id="corporation" /> &nbsp;
				<button class="publishbutton radius3" id="queryBtn">查询</button>
				<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
			</div>
			<br clear="all" />
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="userTable">
				<colgroup>
					<col class="con0" width="20px" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
				</colgroup>
				<thead>
					<tr>
						<th class="head0" width="30px"><input type="checkbox"
							style="width: 20px;" name="checkAll" value="" id="checkAll" /></th>
						<th class="head1">登录号</th>
						<th class="head0">商户名称</th>
						<th class="head1">提款金额</th>
						<th class="head0">银行名称</th>
						<th class="head1">银行卡号</th>
						<th class="head0">状态</th>
						<th class="head1">申请时间</th>
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