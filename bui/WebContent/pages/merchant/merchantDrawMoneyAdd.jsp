<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript"
	src="${ctx}/js/models/merchant/MerchantDrawMoney.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/merchant/merchantDrawMoneyAdd.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">

		<p>
			<label>提款金额</label> <span class="field"> <input type="text"
				name="money" id="money" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>说明</label> <span class="field"> <textarea rows="5"
					cols="6" name="remarks"></textarea> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>银行名称</label> <span class="field"> <input type="text"
				name="bankName" id="bankName" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>银行卡号</label> <span class="field"><input type="text"
				name="cardNumber" id="cardNumber" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>

	</form>

</body>
</html>