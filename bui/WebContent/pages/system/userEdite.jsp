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
<script type="text/javascript" src="${ctx}/js/pages/system/userAdd.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<input type="hidden" name="id" value="${sysUser.id}" />
		<p>
			<label>登录号</label> <span class="field"> <input type="text"
				name="code" id="code" value="${sysUser.code}" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>姓名</label> <span class="field"><input type="text"
				name="name" id="name" value="${sysUser.name}" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"><input type="text"
				name="phone" id="phone" value="${sysUser.phone}" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>性别</label> <span class="field"><select id="sex"
				data-placeholder="请选择性别" id="sex" name="sex" class="uniformselect"
				tabindex="2">
					<option value="男">男</option>
					<option value="女">女</option>
			</select>&nbsp;&nbsp;</span>
			<script type="text/javascript">
				$('#sex').val('${sysUser.sex}');
			</script>
		</p>
		<p>
			<label>系统角色</label> <span class="field"><select name="roleId"
				id="roleId" class="chzn-select" style="width: 200px;" tabindex="2">
					<option value=""></option>
			</select>&nbsp;&nbsp;</span>
			<script type="text/javascript">
				roleCombox.beforeload = function() {
					this.emptyValue = '${sysUser.roleId}';
				};
			</script>
		</p>

	</form>

</body>
</html>