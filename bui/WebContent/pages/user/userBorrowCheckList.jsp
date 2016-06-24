<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/user/UserBorrow.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/user/userBorrowCheckList.js"></script>
<body>

	<div id="contentwrapper" class="contentwrapper">
		<div id="list" class="subcontent">
			<div class="overviewhead">
				<div class="overviewselect"></div>
				用户姓名: &nbsp;<input type="text" id="name" /> &nbsp; 手机号码: &nbsp;<input
					type="text" id="mobilePhone" /> &nbsp;<br /> <br />申请提现日期:
				&nbsp;<input type="text" id="beginDate" /> &nbsp;到&nbsp;<input
					type="text" id="endDate" /> &nbsp;
				<button class="publishbutton radius3" id="queryBtn">查询</button>
				<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
			</div>
			<br clear="all" />
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="userTable">

				<thead>
					<tr>
						<th class="head0" width="30px"><input type="checkbox"
							style="width: 20px;" name="checkAll" value="" id="checkAll" /></th>
						<th class="head1">编号</th>
						<th class="head0">用户姓名</th>
						<th class="head1">手机号码</th>
						<th class="head0">提现金额</th>
						<th class="head1">分期数</th>
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