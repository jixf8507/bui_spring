<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<html>
<head>
<meta charset="UTF-8" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
<title>系统登录</title>

<link rel="stylesheet" type="text/css" href="${ctx}/login/css/demo.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/login/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/login/css/animate-custom.css" />
</head>
<script type="text/javascript">
	var contextPath = '${ctx}';
</script>
<script type="text/javascript"
	src="${ctx}/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pages/login.js"></script>

<body>
	<div class="container">

		<header>
		<h1>
			BUI <span>BUI</span>
		</h1>
		</header>
		<section>
		<div id="container_demo">

			<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
				id="tologin"></a>
			<div id="wrapper">
				<div id="login" class="animate form">
					<form action="" autocomplete="on">
						<h1>登录</h1>
						<p>
							<label for="username" class="uname" data-icon="u"> 用户名 </label> <input
								id="username" name="username" required="required" type="text"
								placeholder="用户名" />
						</p>
						<p>
							<label for="password" class="youpasswd" data-icon="p">
								登录密码 </label> <input id="password" name="password" required="required"
								type="password" placeholder="登录密码" />
						</p>
						<p class="keeplogin">
							<input type="checkbox" name="loginkeeping" id="loginkeeping"
								value="loginkeeping" /> <label for="loginkeeping">记住密码</label>
						</p>
						<p class="login button">
							<input type="button" value="登录" id="submit" />
						</p>

					</form>
				</div>

			</div>
		</div>
		</section>
	</div>
</body>

</html>