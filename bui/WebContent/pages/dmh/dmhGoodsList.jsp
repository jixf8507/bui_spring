<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<script type="text/javascript" src="${ctx}/js/models/dmh/DmhGoods.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/dmh/dmhGoodsList.js"></script>
<body>

	<div id="contentwrapper" class="contentwrapper">
		<div id="list" class="subcontent">
			<div class="overviewhead">
				<div class="overviewselect">
					<select id="status" class="chzn-select" style="width: 200px;"
						tabindex="2">
						<option value="">请选择状态</option>
						<option value="0">已下架</option>
						<option value="1">已上架</option>
					</select> <select id="isTop" class="chzn-select" style="width: 200px;"
						tabindex="2">
						<option value="">请选择是否首页显示</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</div>
				商品名称: &nbsp;<input type="text" id="name" /> &nbsp;
				<button class="publishbutton radius3" id="queryBtn">查询</button>
				<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
			</div>
			<br clear="all" />
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="userTable">
				<colgroup>
					<col class="con1" width="20px" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
				</colgroup>
				<thead>
					<tr>
						<th class="head1" width="30px"><input type="checkbox"
							style="width: 20px;" name="checkAll" value="" id="checkAll" /></th>
						<th class="head0">商品名称</th>
						<th class="head1">价格</th>
						<th class="head0">图片</th>
						<th class="head1">类型</th>
						<th class="head0">是否首页推荐</th>
						<th class="head1">状态</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<br /> <br />
		</div>
	</div>
	<br clear="all" />

</body>
</html>