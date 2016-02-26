<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/upload/Upload.js"></script>
<script type="text/javascript" src="${ctx}/js/models/user/Account.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/user/accountEdite.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<input type="hidden" name="id" value="${userAccount.id}" /> <input
			type="hidden" name="user.id" value="${userAccount.userId}" />
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
			<label>身份证图片</label> <span class="field"><input type="text"
				name="user.idCardImg" id="idCardImg" value="${userAccount.idCardImg}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;<input
				type="button" id="bt" value="上传图片" /></span>
		</p>
		<p>
			<label>学生证图片</label> <span class="field"><input type="text"
				name="user.studentIdCardImg" id="studentIdCardImg" value="${userAccount.studentIdCardImg}"  readonly="readonly"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;<input
				type="button" id="sbt" value="上传图片" /></span>
		</p>
		<p>
			<label>账户状态</label> <span class="field"> <select id="status"
				name="status" class="chzn-select" style="width: 200px;" tabindex="2">
					<option value="">请选择</option>
					<option value="1">正常</option>
					<option value="2">不可取现可分期</option>
					<option value="3">不可取现不可分期</option>
			</select>&nbsp;&nbsp; <script type="text/javascript">
				$('#status').val('${userAccount.status}');
			</script>
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