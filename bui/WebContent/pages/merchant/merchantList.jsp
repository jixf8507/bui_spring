<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript"
	src="${ctx}/js/models/merchant/Merchant.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/merchant/merchantList.js"></script>
<body>

	<div id="contentwrapper" class="contentwrapper">
		<div id="list" class="subcontent">
			<div class="overviewhead">
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
					<col class="con0" />
				</colgroup>
				<thead>
					<tr>
						<th class="head0" width="30px"><input type="checkbox"
							style="width: 20px;" name="checkAll" value="" id="checkAll" /></th>
						<th class="head1">图片</th>
						<th class="head0">商户名称</th>
						<th class="head1">地址</th>
						<th class="head0">负责人</th>
						<th class="head1">联系电话</th>
						<th class="head0">账户金额</th>
						<th class="head1">冻结金额</th>
						<th class="head0">状态</th>
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