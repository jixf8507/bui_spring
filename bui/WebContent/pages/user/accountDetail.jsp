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
				${userAccount.name}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>手机号码</label> <span class="field">${userAccount.mobilePhone}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证</label> <span class="field">
				${userAccount.idCard}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>身份证图片</label> <span class="field"> <img
				src="${ctx}${userAccount.idCardImg}" alt="" width="350px;" />
				&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>学生证图片</label> <span class="field"> <img
				src="${ctx}${userAccount.studentIdCardImg}" alt="" width="350px;" />
				&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>其它证件图片</label> <span class="field"> <img
				src="${ctx}${userAccount.otherImg}" alt="" width="350px;" />
				&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>可用额度</label> <span class="field">${userAccount.usableLimit}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>当前可用额度</label> <span class="field">${userAccount.curUsableLimit}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>提现额度</label> <span class="field">
				${userAccount.whiteBarLimit}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>当前提现额度</label> <span class="field">
				${userAccount.curWhiteBarLimit}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>账单日</label> <span class="field">
				${userAccount.statementDate}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>还款日</label> <span class="field">
				${userAccount.repaymentDate}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>账户状态</label> <span class="field"> <c:if
					test="${userAccount.status == '1' }">正常	</c:if> <c:if
					test="${userAccount.status == '2' }">不可取现可分期	</c:if> <c:if
					test="${userAccount.status == '2' }">不可取现不可分期	</c:if> &nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>