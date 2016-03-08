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

		<input type="hidden" name="user.id" value="${user.id}" /> <input
			type="hidden" name="userId" value="${user.id}" />
		<p>
			<label>用户名称</label> <span class="field"> <input type="text"
				name="user.name" id="name" value="${user.name}" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"><input type="text"
				name="user.mobilePhone" id="mobilePhone" value="${user.mobilePhone}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证</label> <span class="field"> <input type="text"
				name="user.idCard" id="idCard" value="${user.idCard}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>身份证图片</label> <span class="field"><input type="text"
				name="user.idCardImg" id="idCardImg" value="${user.idCardImg}" readonly="readonly"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;<input
				type="button" id="bt" value="上传图片" /></span>
		</p>
		<p>
			<label>学生证图片</label> <span class="field"><input type="text"
				name="user.studentIdCardImg" id="studentIdCardImg" value="${user.studentIdCardImg}"  readonly="readonly"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;<input
				type="button" id="sbt" value="上传图片" /></span>
		</p>
		<p>
			<label>其它证件图片</label> <span class="field"><input type="text"
				name="user.otherImg" id="otherImg" value="${user.otherImg}"  readonly="readonly"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;<input
				type="button" id="obt" value="上传图片" /></span>
		</p>
		<p>
			<label>审核状态</label> <span class="field"> <select id="status"
				name="user.status" class="chzn-select" style="width: 200px;"
				tabindex="2">
					<option value="">请选择</option>
					<option value="2">通过</option>
					<option value="3">不通过</option>
			</select>&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>审核说明</label> <span class="field"> <input type="text"
				name="user.statusDesc" id="statusDesc" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>可用额度</label> <span class="field"><input type="text"
				name="usableLimit" id="usableLimit" value="" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>白条额度</label> <span class="field"> <input type="text"
				name="whiteBarLimit" id="whiteBarLimit" value="" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>账单日</label> <span class="field"> <select
				id="statementDate" name="statementDate" class="chzn-select"
				style="width: 200px;" tabindex="2">
					<c:forEach var="i" begin="1" end="28" step="1">
						<c:if test="${i<10}">
							<option value="0${i}">0${i}</option>
						</c:if>
						<c:if test="${i>=10}">
							<option value="${i}">${i}</option>
						</c:if>
					</c:forEach>
			</select>&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>还款日</label> <span class="field"> <select
				id="repaymentDate" name="repaymentDate" class="chzn-select"
				style="width: 200px;" tabindex="2">
					<c:forEach var="i" begin="1" end="28" step="1">
						<c:if test="${i<10}">
							<option value="0${i}">0${i}</option>
						</c:if>
						<c:if test="${i>=10}">
							<option value="${i}">${i}</option>
						</c:if>
					</c:forEach>
			</select>&nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>