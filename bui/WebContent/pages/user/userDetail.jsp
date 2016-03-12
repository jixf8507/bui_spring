<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>用户名称</label> <span class="field">
				${user.name}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>手机号码</label> <span class="field">${user.mobilePhone}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证</label> <span class="field">
				${user.idCard}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>身份证图片</label> <span class="field"> <img
				src="${ctx}${user.idCardImg}" alt="" width="350px;" /> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>学生证图片</label> <span class="field"> <img
				src="${ctx}${user.studentIdCardImg}" alt="" width="350px;" />
				&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>其它证件图片</label> <span class="field"> <img
				src="${ctx}${user.otherImg}" alt="" width="350px;" /> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>账户状态</label> <span class="field"> <c:if
					test="${user.status == '0' }">注册中	</c:if> <c:if
					test="${user.status == '1' }">审核中	</c:if> <c:if
					test="${user.status == '2' }">已通过审核	</c:if> <c:if
					test="${user.status == '3' }">审核不通过	</c:if> <c:if
					test="${user.status == '4' }">冻结	</c:if> &nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>