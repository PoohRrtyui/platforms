<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<base href="<%=basePath%>">
    
<head>
    <title>登录认证</title>
    <script type="text/javascript"	src="static/js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript"	src="static/js/jquery/jquery.tools.min.js"></script>

<script>
/* function r()
{

	var username=$("#username").val();
	var pwd=$("#password").val();
	if(username=="")
	{
		alert("请输入用户名");
		return;
	}
	if(pwd=="")
	{
		alert("请输入密码");
		return;
	}
} */
jQuery(function($) {
 	//初始化登陆
 	Login.init();
	$("#loginSubmit").on("click",Login.checkLogin);
});

var Login = function(){
	return {
		init : function(){
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
		checkLogin:function(){
			var username = $("#username").val();
			var password = $("#password").val();
			if (!username) {
				/* username.tips({
					side : 2,
					msg : '用户名不得为空',
					time : 3
				}); */
				alert("sss");
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
				alert("ss");
				return false;
			}
			$.ajax({
				type: "POST",
				url:  '<%=basePath%>login/process',
		    	data: {username:username,password:password},
				dataType:'json',
				cache: false,
				success: function(data){
					if("SUCCESS" == data.errCode){
						Login.saveCookie();
						window.location.href=BEDU.contextPath + "/main/index";
					}else{
						$("#username").tips({
							side : 1,
							msg : data.errMsg,
							bg : '#FF5080',
							time : 15
						});
						$("#username").focus();
					}
				}
			});
		},
		saveCookie: function() {
			if ($("#remeberme").attr("checked")) {
				Cookies.set('username', $("#username").val(), {
					expires : 7
				});
				Cookies.set('password', $("#password").val(), {
					expires : 7
				});
			}
		}
	}
}();

</script>
</head>
<body>
    <table  width="350" bgcolor="#ccffcc" style="border-color" border="1">
                     <tr align=center>
						<td>用户名</td><td><input type="text" name="username"  id="username"></td>
		     		 </tr>
                     <tr align=center>
                   		<td>密 码</td><td><input type="password" name="password" id="password"></td>
                     </tr>
                     <tr align=center>
						<input type="checkbox" class="ace" id="remeberme" name="remeberme"/>
						<span class="lbl">记住密码</span>
                   		<td colspan="2">
                   		<button type="button"  id="loginSubmit">点击登录</button>
                   	    </td>
                   	 </tr>
                               
    </table>
</body>
</html>