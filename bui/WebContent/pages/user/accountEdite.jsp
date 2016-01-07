<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/user/Account.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/user/accountEdite.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<input type="hidden" name="id" value="${userAccount.id}" />
		<input type="hidden" name="user.id" value="${userAccount.userId}" />
		<p>
			<label>用户名称</label> <span class="field"> <input type="text"
				name="user.name" id="name" value="${userAccount.name}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"><input type="text"
				name="user.mobilePhone" id="mobilePhone"
				value="${userAccount.mobilePhone}" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证</label> <span class="field"> <input type="text"
				name="user.idCard" id="idCard" value="${userAccount.idCard}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>可用额度</label> <span class="field"><input type="text"
				name="usableLimit" id="usableLimit"
				value="${userAccount.usableLimit}" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>白条额度</label> <span class="field"> <input type="text"
				name="whiteBarLimit" id="whiteBarLimit"
				value="${userAccount.whiteBarLimit}" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>