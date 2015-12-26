<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/system/Role.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/system/roleAdd.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<input type="hidden" name="id" value="${sysRole.id}" />
		<p>
			<label>角色名称</label> <span class="field"> <input type="text"
				name="roleName" id="roleName" value="${sysRole.roleName}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>角色描述</label> <span class="field"><input type="text"
				name="roleRemark" id="roleRemark" value="${sysRole.roleRemark}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
	</form>

</body>
</html>