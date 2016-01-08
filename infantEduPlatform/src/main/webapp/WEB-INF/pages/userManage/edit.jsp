<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
<script type="text/javascript" src="static/js/common/base.js"></script>
<script src="static/plugins/validate/jquery.validate.js"></script>
<script src="static/plugins/validate/jquery.validate.extend.js"></script>
<script type="text/javascript" src="static/js/unit.js"></script>
<body>
	<div class="col-sm-10" id="settings">
		<form class="form-horizontal" id="userForm">
			<input type="hidden" id="schoolId" name="schoolId"
				value="${schoolId}"/> <input type="hidden" id="isClas"
				name="isClas" value="${isClass}"/> <input type="hidden" id="tId" name="tId" value="${treeId}"/>
				<input type="hidden" id="type" name="type" value="${type}"/>
				<input type="hidden" id="userId" name="userId" value="${users.userid}">
				<input type="hidden" id="nickName" name="nickName" value="${users.nickname}">
				<input type="hidden" id="Name" name="Name" value="${users.NAME}">
				<input type="hidden" id="genderCode" name="genderCode" value="${users.gendercode}">
				<input type="hidden" id="idNo" name="idNo" value="${users.idno}">
				<input type="hidden" id="Mobile" name="Mobile" value="${users.mobile}">
				<input type="hidden" id="Email" name="Email" value="${users.email}">
				<input type="hidden" id="UserName" name="UserName" value="${users.username}">
				<input type="hidden" id="passWord" name="passWord" value="${passWord}">
				<input type="hidden" id="userId" name="userId" value="${userId}">
			<div class="form-group">
				<label for="locationCode" class="col-sm-3 control-label">登录名</label>
				<div class="col-sm-8">
					<input type="text" class="form-control required" id="username" placeholder="Name"
						name="username">
				</div>
			</div>
			<div class="form-group">
				<label for="parentBureau" class="col-sm-3 control-label">昵称</label>
				<div class="col-sm-8">
					<input type="text" class="form-control required" id="nickname" name="nickname"
						placeholder="昵称"> <input type="hidden" name="" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="parentBureau" class="col-sm-3 control-label">登录密码</label>
				<div class="col-sm-8">
					<input type="password" class="form-control required" id="pwd" name='pwd'
						placeholder="登录密码">
						<%-- <input type="hidden" name="password" value="${password}" id=${password} }> --%>
				</div>
			</div>
			<div class="form-group">
				<label for="parentBureau" class="col-sm-3 control-label">确认密码</label>
				<div class="col-sm-8">
					<input type="password" class="form-control required" id="sPwd" name='sPwd'
						placeholder="确认密码">
				</div>
			</div>
			<div class="form-group">
				<label for="bureauName" class="col-sm-3 control-label">真实姓名</label>
				<div class="col-sm-8">
					<input type="text" class="form-control chinese" id="NAME" name="NAME"
						placeholder="真实姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="telephone" class="col-sm-3 control-label">身份证号</label>

				<div class="col-sm-8">
					<input type="text" class="form-control identityCard" id="idno" name="idno"
						placeholder="idno">
				</div>
			</div>
			<div class="form-group">
				<label for="bureauAddress" class="col-sm-3 control-label">手机</label>
				<div class="col-sm-8">
					<input class="form-control mobile" id="mobile" name="mobile"
						placeholder="手机" />
				</div>
			</div>
			<div class="form-group">
				<label for="xingbie" class="col-sm-3 control-label">性别</label>
					<div class="iradio_square-blue">
                        <input  type="radio" id="input-1" name="gendercode" value="1">
                    </div>
                    <label for="input-1" class="">男</label>

                    <div class="iradio_square-blue" >
                        <input  type="radio" id="input-2" name="gendercode" value="2">
                    </div>
                    <label for="input-2" class="">女</label>
                    
                    <div class="iradio_square-blue" >
                        <input  type="radio" id="input-3" name="gendercode" value="0">
                    </div>
                    <label for="input-3" class="">保密</label>
			</div>
			
			<div class="form-group">
				<label for="homepageUrl" class="col-sm-3 control-label ">邮箱</label>

				<div class="col-sm-8">
					<input type="text" class="form-control email" id="email" name="email"
						placeholder="Email">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-9">
					<button type="button" id="addUser" class="btn btn-danger">保存</button>
				</div>
			</div>
		</form>
		<script src="static/plugins/iCheck/icheck.min.js"></script>
		<script type="text/javascript">
		$(function () {
			var userId = $("#userId").val();
		    $('input').iCheck({
		        labelHover: false,
		        cursor: true,
		        checkboxClass: 'icheckbox_square-blue',
		        radioClass: 'iradio_square-blue',
		        increaseArea: '20%'
		    });
		    if (userId != ""&&userId!=null) {
		    	$("input[name=nickname]").val($("#nickName").val());
		    	$("input[name=NAME]").val($("#Name").val());
		    	$("input[name=idno]").val($("#idNo").val());
		    	$("input[name=mobile]").val($("#Mobile").val());
		    	$("input[name=email]").val($("#Email").val());
		    	$("input[name=pwd]").attr("type","hidden");
		    	$("input[name=sPwd]").attr("type","hidden");
		    	$("input[name=username]").val($("#UserName").val());
		    	$("input[name=pwd]").attr("readonly","readonly");
		    	$("input[name=sPwd]").attr("readonly","readonly");
		    	$("input[name=username]").attr("readonly","readonly");
	            $(":radio[name='gendercode'][value='" + $("#genderCode").val() + "']").iCheck('check');
		    }
		});
		$("#addUser").on("click",function(){
			/* var boolean = formValidation($("#userForm")); */
			var schoolId = $("#schoolId").val();
			var treeId = $("#tId").val();
			var isClass = $("#isClas").val();
			var type = $("#type").val();
			var pwd = $("#pwd").val();
			var spwd = $("#sPwd").val();
			var username = $("#username").val();
			var mobile = $("#mobile").val();
			var userId = $("#userId").val();
			var Mobile = $("#Mobile").val();
			if (formValidation($("#userForm"))) {
				if (pwd!=spwd) {
					layer.alert('密码不一致', {icon: 5});
				} else {
					$.ajax({
    					async : false,
    					type : "POST",
    					data : {"name":username,"mobile":mobile,"Mobile":Mobile,"userId":userId},
    					url : "register/userManageValidate",
    					success: function(data){
				        	if(data==0){
				        		 $.ajax({
								        async:true,
								        type:"POST",
								        url:"user/saveUserInfo",
								        data:$("#userForm").serialize()+"&pwd="+pwd+"&type="+type+"&schoolId="+schoolId+"&treeId="+treeId+"&isClass="+isClass+"&userId"+userId,
								        dataType:"JSON",
								        success:function(){
								        	layer.closeAll();
								            layer.msg('保存成功', {icon: 1,time: 2000});
								            var path = "user/list?schoolId=" + schoolId + "&treeId=" + $("#tId").val() + "&isClass=" + $("#isClas").val();
											$('#tbl_user_mgr').DataTable().ajax.url( path ).load();
								        }
								    }); 
				        	}else{
				        		layer.msg('此用户已存在！', {icon: 10});
				        	}
				        }});
				}
			}
		});
		</script>
	</div>
</body>
