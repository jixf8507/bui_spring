<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/upload/Upload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/models/merchant/Merchant.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/merchant/merchantEdite.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>商家图片</label> <span class="field"><input type="text"
				name="img" id="img" class="longinput" style="width: 200px;" />&nbsp;&nbsp;<input
				type="button" id="bt" value="上传图片" /></span>
		</p>
		<p>
			<label>商家名称</label> <span class="field"> <input type="text"
				name="name" id="name" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>商家登录号</label> <span class="field"> <input type="text"
				name="code" id="code" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>负责人</label> <span class="field"> <input type="text"
				name="corporation" id="corporation" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"><input type="text"
				name="mobilePhone" id="mobilePhone" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>

		<p>
			<label>商家地址</label> <span class="field"><input type="text"
				name="address" id="address" class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>商家描述</label> <span class="field"> <textarea rows="5"
					cols="6" name="des"></textarea> &nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>