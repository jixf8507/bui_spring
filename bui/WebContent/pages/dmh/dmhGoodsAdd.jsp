<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="${ctx}/js/models/upload/Upload.js"></script>
<script type="text/javascript" src="${ctx}/js/models/dmh/DmhGoods.js"></script>
<script type="text/javascript"
	src="${ctx}/js/pages/dmh/dmhGoodsEdite.js"></script>
<body>

	<form id="form1" class="stdform stdform2" method="post" action="">

		<p>
			<label>商品名称</label> <span class="field"> <input type="text"
				name="name" id="name" value="" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>价格</label> <span class="field"> <input type="text"
				name="price" id="price" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>商品图片</label> <span class="field"><input type="text"
				name="img" id="img" class="longinput" readonly="readonly"
				style="width: 200px;" />&nbsp;&nbsp;<input type="button" id="bt"
				value="上传图片" /></span>
		</p>

		<p>
			<label>类型</label> <span class="field"><select id="type"
				name="type" class="chzn-select" style="width: 200px;" tabindex="2">
					<option value="">请选择</option>
					<option value="1">3c数码</option>
					<option value="2">分期游</option>
			</select>&nbsp;&nbsp;</span>

		</p>
		<p>
			<label>是否首页显示</label> <span class="field"><select id="isTop"
				name="isTop" class="chzn-select" style="width: 200px;" tabindex="2">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
			</select> &nbsp;&nbsp;</span>

		</p>
		<p>
			<label>商品描述1</label> <span class="field"> <textarea rows="5"
					cols="6" name="des1"></textarea> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>商品描述2</label> <span class="field"> <textarea rows="5"
					cols="6" name="des2"></textarea> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>商品描述3</label> <span class="field"> <textarea rows="5"
					cols="6" name="des3"></textarea> &nbsp;&nbsp;
			</span>
		</p>
	</form>

</body>
</html>