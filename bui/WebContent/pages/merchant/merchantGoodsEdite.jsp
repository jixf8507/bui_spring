<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript"
	src="${ctx}/js/models/merchant/Merchant.js"></script>
<script type="text/javascript" src="${ctx}/js/models/upload/Upload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/models/merchant/MerchantGoods.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/merchant/merchantGoodsEdite.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<input type="hidden" name="id" value="${merchantGoods.id}" />
		<p>
			<label>商品名称</label> <span class="field"> <input type="text"
				name="name" id="name" value="${merchantGoods.name}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>价格</label> <span class="field"> <input type="text"
				name="price" id="price" value="${merchantGoods.price}"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>商品图片</label> <span class="field"><input type="text"
				name="img" id="img" value="${merchantGoods.img}" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;<input type="button" id="bt"
				value="上传图片" /></span>
		</p>

		<p>
			<label>商家</label> <span class="field"><select
				name="merchantId" id="merchantId" class="chzn-select"
				style="width: 200px;" tabindex="2">
					<option value=""></option>
			</select>&nbsp;&nbsp;</span>
			<script type="text/javascript">
				merchantCombox.beforeload = function() {
					this.emptyValue = '${merchantGoods.merchantId}';
				};
			</script>
		</p>
		<p>
			<label>商品描述1</label> <span class="field"> <textarea rows="5"
					cols="6" name="des1">${merchantGoods.des1}</textarea> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>商品描述2</label> <span class="field"> <textarea rows="5"
					cols="6" name="des2">${merchantGoods.des2}</textarea> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>商品描述3</label> <span class="field"> <textarea rows="5"
					cols="6" name="des3">${merchantGoods.des3}</textarea> &nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>