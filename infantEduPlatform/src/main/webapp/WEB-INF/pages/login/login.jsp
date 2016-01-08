<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/";
    String weiboClientId = "4012392217";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<base href="<%=basePath%>">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>统一登录认证</title>
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
            <p class="login-box-msg text-blue">统一登录认证</p>
        </h3>

        <form action="authToken/accessToken" method="post" id="loginForm">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="账号"
                       name="username" id="username">
                <span  class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="密码"
                       name="password" id="password"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label> <input type="checkbox" id="remeberme"
                                       name="remeberme"> 记住我
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="button" id="loginSubmit"
                            class="btn btn-primary btn-block btn-flat">
                        <i class="ace-icon fa fa-key" onKeyDown="keydownEvent()"></i> 登录
                    </button>
                    <button type="submit" id="oAuthLogin"
                            class="btn btn-primary btn-block btn-flat">
                        <i class="ace-icon fa fa-key" ></i> OAuth登录
                    </button>
                </div>
                <!-- /.col -->
            </div>
            <div>
                <input type="hidden" id="grant_type" name="grant_type" value="client_credentials">
                <input type="hidden" id="client_id" name="client_id" value="platform">
                <input type="hidden" id="client_secret" name="client_secret" value="platform">
                <%--<input type="hidden" id="scope" name="scope"   value="get_user_info"/>
                <input type="hidden" id="state" name="state" value="infantEduPlatform"/>
                <input type="hidden" id="response_type" name="response_type" value="code"/>
                <input type="hidden" name="redirect_uri" value="http://localhost:8080/infantEdu/authToken/accessToken"/>--%>
            </div>
        </form>

        <div class="social-auth-links text-center">
            <p>-快速登录-</p>
            <div class="row"
                 style="border-top: 1px solid #D5E3EF; padding: 14px 0 14px 0;">

                <a href="accountLogin/qqLogin" class="btn  btn-social  btn-flat">
                    <i class="fa fa-qq"> </i>QQ</a>
                <a href="weibo/login" class="btn  btn-social  btn-flat">
                    <i class="fa fa-weibo"> </i>新浪微博</a>
                <a href="#" class="btn  btn-social  btn-flat">
                    <i class="fa fa-renren"> </i>人人</a>
                <%-- <a href="#" class="btn btn-block btn-social  btn-flat">
                    <i class="fa fa-facebook"></i>
                     忘记密码</a>--%>
            </div>
            <div class="row">
                <a href="register/show" class="btn btn-block btn-social  btn-flat">
                    <i class="fa fa-registered"></i> 新用户注册
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
<script>
    jQuery(function ($) {

        $("#oAuthLogin").on("click",function(){
            $.ajax({
                type:"POST",
                url:"authToken/accessToken",
                data:{"grant_type":"client_credentials"}
            });
        });


        //记住密码初始化
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
        //初始化登陆
        Login.init();
        $("#loginSubmit").on("click", Login.checkLogin);
        /* var e = window.event || arguments.callee.caller.arguments[0];
         if(e.keyCode == 13){
         alert("sss");
         $("#loginSubmit").on("click",Login.checkLogin);
         } */

    });
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            Login.checkLogin();
        }
    };

    var Login = function () {
        return {
            init: function () {
                //初始加载读取用户保存的账号密码
                /* var username = Cookies.get('username');
                 var password = Cookies.get('password'); */
                if (typeof(loginname) != "undefined"
                        && typeof(password) != "undefined") {
                    $("#username").val(username);
                    $("#password").val(password);
                    $("#remeberme").attr("checked", true);
                }
            },
            checkLogin: function () {
                var username = $("#username").val();
                var password = $("#password").val();
                var appCode = $("#appCode").val();
                if (!username) {
                    /* username.tips({
                     side : 2,
                     msg : '用户名不得为空',
                     time : 3
                     }); */
                    alert("用户名不得为空");
                    /* username.focus(); */
                    return false;
                } else {
                    $("#username").val(jQuery.trim(username));
                }
                if (!password) {
                    /* password.tips({
                     side : 2,
                     msg : '密码不得为空',
                     time : 3
                     }); */
                    /* password.focus(); */
                    alert("密码不得为空");
                    return false;
                }
                $.ajax({
                    type: "POST",
                    url: '<%=basePath%>login/process',
                    data: {
                        username: username,
                        password: password,
                        appCode: appCode
                    },
                    dataType: 'json',
                    cache: false,
                    success: function (data) {
                        if ("SUCCESS" == data.errCode) {
                            Login.saveCookie();
                            window.location.href = "<%=basePath%>page/data";
                            /* alert("登录成功"); */
                        } else {
                            /* $("#username").tips({
                             side : 1,
                             msg : data.errMsg,
                             bg : '#FF5080',
                             time : 15
                             });
                             $("#username").focus(); */
                            alert("登录失败")
                        }
                    }
                });
            },
            saveCookie: function () {
                if ($("#remeberme").attr("checked")) {
                    Cookies.set('username', $("#username").val(), {
                        expires: 7
                    });
                    Cookies.set('password', $("#password").val(), {
                        expires: 7
                    });
                }
            }
        }
    }();
</script>
</body>
</html>
