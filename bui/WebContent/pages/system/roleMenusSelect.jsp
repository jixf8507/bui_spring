<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<title>BUI 管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<link rel="StyleSheet" href="${ctx}/js/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/dtree/dtree.js"></script>

<body style="margin-left: 6px;">
	<input type="hidden" id="roleId" value="${rid}" />
	<p>
		<a href="javascript: d.openAll();">展开</a> | <a
			href="javascript: d.closeAll();">收起</a>
	</p>
	<script type="text/javascript">
		var d = new dTree('d');
		d.config.folderLinks = false;
		d.config.useSelection = false;
		d.config.useIcons = false;
		d.config.useLines = false;
		d.config.useSelection = false;
		d.config.useStatusText = true;
		d.config.closeSameLevel = true;
		d.add(0, -1, '');
	</script>
</body>
<script type="text/javascript"
	src="${ctx}/js/pages/system/roleMenuSelect.js"></script>
</html>