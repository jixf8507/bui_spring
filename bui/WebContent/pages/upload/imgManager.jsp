<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/upload/Upload.js"></script>
<script type="text/javascript" src="${ctx}/js/models/upload/Img.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/upload/imgManager.js"></script>
<body>
	<input type="hidden" id="tableId" value="${tableId}" />
	<input type="hidden" id="tableName" value="${tableName}" />
	<input type="hidden" id="fileType" value="${fileType}" />
	<input type="hidden" id="pathType" value="${pathType}" />
	<div id="contentwrapper" class="contentwrapper">
		<div id="list" class="subcontent">
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="userTable">
				<colgroup>
					<col class="con1" width="20px" />
					<col class="con0" />
					<col class="con1" width="150px" />
				</colgroup>
				<thead>
					<tr>
						<th class="head1" width="30px"><input type="checkbox"
							style="width: 20px;" name="checkAll" value="" id="checkAll" /></th>
						<th class="head0">图片</th>
						<th class="head1">创建时间</th>
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