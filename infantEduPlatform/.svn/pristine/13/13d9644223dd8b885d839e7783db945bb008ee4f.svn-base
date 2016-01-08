<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<base href="<%=basePath%>">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>统一注册认证</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="static/plugins/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="static/plugins/dist/css/font-awesome-4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="static/plugins/dist/css/ionicons/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="static/plugins/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="static/css/login.css">
<!-- iCheck -->
<link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
   <script src="static/plugins/iePlugins/html5shiv.min.js"></script>
  <script src="static/plugins/iePlugins/respond.min.js"></script>
  <![endif]-->
<style type="text/css">
.register_prompt_error{
    font-size:12px;
    color:#C8180B;
    padding:0px 5px 0px 5px;
    height:18px;
}
</style>
</head>
<body class="hold-transition login-page blur-login">
	<div class="login-box">
		<div class="login-logo">
			<a href="../../index2.html"> <i
				class="ace-icon fa fa-leaf text-green"></i> <b class="text-red">幼教</b>
				<span class="text-gray">平台</span>
			</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<h3>
				<p class="login-box-msg text-blue">统一注册认证</p>
			</h3>

			<form action="" method="post" id="register">
				<div class="form-group has-feedback" style="margin-bottom: 0px;">
					<input type="text" class="form-control required" placeholder="用户名"
						name="username" id="username"> <span
						class="glyphicon glyphicon-user form-control-feedback "></span>
				</div>
				<div id="username_prompt" style="font-size: 12px;margin-bottom: 10px;height: 20px;"></div>
				<div class="form-group has-feedback" style="margin-bottom: 0px;">
					<input type="password" class="form-control required" rangelength="8 20" placeholder="密码"
						name="password" id="password"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div id="pwd_prompt" style="font-size: 12px;margin-bottom: 10px;height: 20px;"></div>
				<div class="form-group has-feedback" style="margin-bottom: 0px;">
					<input type="password" class="form-control required" placeholder="重输密码"
						name="repassword" id="repassword"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div id="repwd_prompt" style="font-size: 12px;margin-bottom: 10px;height: 20px;"></div>
				<div class="form-group has-feedback" style="margin-bottom: 0px;">
					<input type="text" class="form-control required mobile" placeholder="手机"
						name="mobile" id="mobile"> <span
						class="glyphicon glyphicon-phone form-control-feedback"></span>
				</div>
				<div id="mobile_prompt" style="font-size: 12px;margin-bottom: 10px;height: 20px;"></div>
				<div class="row">
					<!-- /.col -->
					<div class="col-xs-12">
						<button type="button" id="registerSubmit"
							class="btn btn-primary btn-block btn-flat">
							<i class="ace-icon fa fa-key"></i> 注册
						</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			<div class="social-auth-links text-center">
				<div class="row">
					<a onclick="window.location.href = 'login/prelogin'" class="btn btn-block btn-social  btn-flat"> <i
						class="fa fa-registered"></i> 已有账号登陆
					</a>
				</div>
			</div>
			<!-- /.social-auth-links -->

			<%--<a href="#">忘记密码</a><br>
    <a href="register.html" class="text-center">新用户注册</a>--%>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 2.1.4 -->
	<script src="static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="static/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="static/plugins/iCheck/icheck.min.js"></script>
	<script src="static/plugins/layer/layer.js"></script>
	<script src="static/plugins/validate/jquery.validate.js"></script>
	<script src="static/plugins/validate/jquery.validate.extend.js"></script>
	<script src="static/js/common/base.js"></script>
	<script type="text/javascript" src="static/js/register/register.js"></script>
</body>
</html>
