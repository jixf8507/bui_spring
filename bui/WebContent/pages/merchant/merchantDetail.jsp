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
			<label>商家图片</label> <span class="field"> <img
				src="${ctx}${merchant.img}" alt="" width="350px;" /> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>商家名称</label> <span class="field"> ${merchant.name}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>商家登录号</label> <span class="field">
				${merchant.code}&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>负责人</label> <span class="field">${merchant.corporation}&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field">${merchant.mobilePhone}&nbsp;&nbsp;</span>
		</p>

		<p>
			<label>商家地址</label> <span class="field">${merchant.address}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>商家描述</label> <span class="field"> ${merchant.des}
				&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>账户金额</label> <span class="field">${merchant.totalMoney}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>冻结金额</label> <span class="field">
				${merchant.freezeMoney} &nbsp;&nbsp; </span>
		</p>
		<p>
			<label>状态</label> <span class="field"> <c:if
					test="${merchant.status == '0' }">冻结(不可取现)	</c:if> <c:if
					test="${merchant.status == '1' }">正常	</c:if> &nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>