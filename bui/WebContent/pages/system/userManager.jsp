<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/system/User.js"></script>
<script type="text/javascript" src="${ctx}/js/models/system/Role.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/system/userManager.js"></script>
<body>

	<div id="contentwrapper" class="contentwrapper">
		<div id="list" class="subcontent">
			<div class="overviewhead">
				<div class="overviewselect">
					<select id="roleId" class="chzn-select" style="width: 200px;"
						tabindex="2">
						<option value=""></option>
					</select>
				</div>
				姓名: &nbsp;<input type="text" id="name" /> &nbsp; 登录号: &nbsp;<input
					type="text" id="code" /> &nbsp;
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
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
				</colgroup>
				<thead>
					<tr>
						<th class="head1" width="30px"><input type="checkbox"
							style="width: 20px;" id="checkAll" value="" /></th>
						<th class="head0">登录邮箱</th>
						<th class="head1">姓名</th>
						<th class="head0">手机号码</th>
						<th class="head1">角色</th>
						<th class="head0">性别</th>
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