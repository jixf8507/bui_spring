<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>BUI 管理系统</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var contextPath = '${ctx}';
</script>
<link rel="stylesheet" href="${ctx}/css/style.default.css"
	type="text/css" />

<script type="text/javascript" src="${ctx}/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/colorpicker.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/jquery.alerts.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/pageDateTables.js"></script>
<script type="text/javascript" src="${ctx}/js/common/comBox.js"></script>

<script type="text/javascript" src="${ctx}/js/zdialog/zDrag.js"></script>
<script type="text/javascript" src="${ctx}/js/zdialog/zDialog.js"></script>