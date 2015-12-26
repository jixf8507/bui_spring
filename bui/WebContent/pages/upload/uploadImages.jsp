<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Uploader上传控件</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	var contextPath = '${ctx}';
</script>

<!-- include -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/upload/webuploader.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/upload/style.css">
	<!-- include -->
</head>
<body>
	<input type="hidden" value="${tableId}" id="tableId">
	<input type="hidden" value="${fileType }" id="fileType">
	<input type="hidden" value="${pathType }" id="pathType">
	<input type="hidden" value="${tableName }" id="tableName">
	<div id="uploader">
		<div class="queueList">
			<div id="dndArea" class="placeholder">
				<div id="filePicker"></div>
				<p>或将照片拖到这里，单次最多可选300张</p>
			</div>
		</div>

		<div class="statusBar" style="display: none;">
			<div class="progress">
				<span class="text">0%</span> <span class="percentage"></span>
			</div>
			<div class="info"></div>
			<div class="btns">
				<div id="filePicker2"></div>
				<div class="uploadBtn">开始上传</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/js/upload/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/upload/webuploader.js"></script>
	<script type="text/javascript"
		src="${ctx}/js/pages/upload/uploadImages.js"></script>

</body>
</html>