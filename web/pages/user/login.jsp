<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Login</title>
	<base href="http://localhost:8080/BeiYueWeb_war_exploded/">
<%--    <base href="http://localhost:8080/BeiYueWeb/">--%>
	<script type="text/javascript" src="static/js/jquery-3.5.1.min.js"></script>
	<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
	<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="static/css/login.css">
	<script type="text/javascript" src="static/js/login.js"></script>
	<script type="text/javascript" language="JavaScript">
		$(document).ready(function () {
			InitMsg();
		})
		function InitMsg(){
			if("${requestScope.msg}"!==""){
				let warnDiv = "<div class=\"alert alert-warning alert-dismissible fade in\" role=\"alert\">\n" +
						"\t\t<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n" +
						"\t\t${requestScope.msg}.\n" +
						"\t</div>";
				$("#warnDivContainer").append(warnDiv);
			}
		}

	</script>
</head>
<body>
	<div class="container">
		<div class="row" id="head">
	  		<div class="col-md-2">
	  			<p style="line-height: 50px;font-size: 20px;">登录界面</p>
	  		</div>
	  		<div class="col-md-2"></div>
	  		<div class="col-md-2"></div>
	  		<div class="col-md-2"></div>
	  		<div class="col-md-2"></div>
	  		<div class="col-md-2"></div>
		</div>
		<div class="row" id="content">
			<div class="col-md-2"></div>
			<div class="col-md-5">
				<img src="static/img/bird.png">
			</div>
			<div class="col-md-3">
				<form name="loginForm" action="login?action=login" method="POST">
				  <div class="form-group">
				    <label for="exampleInputEmail1">用户名</label>
				    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="1-20位英文字母" name="username" onblur="checkFormat()" value="${requestScope.user.username }">
				    <p id="usernameWarning" class="warning">&nbsp;</p>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">密码</label>
				    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="6-10位数字或者英文字母" name="pwd" onblur="checkFormat()">
				    <p id="passwordWarning" class="warning">&nbsp;</p>
				  </div>
				  <div class="checkbox">
				    <label>
				      <input type="checkbox">记住密码
				    </label>
				  </div>
				  <button type="submit" class="btn btn-success" onclick="return login()" style="display:inline-block;margin-left:50px;">登录</button>
				  <button class="btn" style="margin-left:20px;">注册</button>
				</form>
			</div>
			<div class="col-md-2"></div>
		</div>
		<div id="warnDivContainer" class="footer"></div>
	</div>
</body>
</html>