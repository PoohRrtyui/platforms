(function($){
    
    $("#registerSubmit").on("click", function() {
    	if(validate($("#username"))&&validate($("#password"))&&validate($("#repassword"))&&validate($("#mobile"))){
    		var username = $("#username").val();
    		var password = $("#password").val();
    		var mobile = $("#mobile").val();
    		
    				$.ajax({
    					async : false,
    					type : "POST",
    					data : {"name":username,"mobile":mobile},
    					url : "register/updateValidate",
    					success: function(data){
    						if(data == 0){
    							$.ajax({
    								async : false,
    								dataType : "JSON",
    								type : "POST",
    								data : {"name":username,"password":password,"mobile":mobile},
    								url :"register/register",
    								success : function(data) {
    									layer.msg('注册成功！', {icon: 1});
    									window.location.href = "page/data";
    												},
    								error : function(data) {
    										layer.msg("注册失败！",{icon: 2});
    												}
    										});	
    						}else{
    							layer.msg('此用户已存在！', {icon: 10});
    						}
    					}
    				});
    			
    		
    		
    	}else{
    		layer.msg('注册信息格式错误，请重新填写！', {icon: 2});
    	}
    });
	
    
    $("#username").focus(function(){
    	$("#username_prompt").removeClass("register_prompt_error");
        $("#username_prompt").html("昵称可由大小写英文字母、数字组成，长度为2-20个字符");
    }).blur(function(){
        validate($(this));
    });
    
   
    $("#password").focus(function(){
    	$("#pwd_prompt").removeClass("register_prompt_error")
        $("#pwd_prompt").html("密码可由大小写英文字母、数字组成，长度为6－20个字符");
    }).blur(function(){
        validate($(this));
    });
    
    $("#repassword").focus(function(){
    	$("#repwd_prompt").removeClass("register_prompt_error")
    	$("#repwd_prompt").html("请再次输入您的密码");
    }).blur(function(){
        validate($(this));
    });
   
    $("#mobile").focus(function(){
    	$("#mobile_prompt").removeClass("register_prompt_error")
        $("#mobile_prompt").html("此手机号将是您登录的账号");
    }).blur(function(){
        validate($(this));
    });
    
    function validate($dom){
    	var v=$dom.val();
        var id=$dom.attr("id");
        var flag=true;
        switch (id){
        case "username":
            $("#username_prompt").html("");
            var reg=/^[a-zA-Z0-9]{2,20}$/;
            if(v==""){
                $("#username_prompt").removeClass().addClass("register_prompt_error").html("昵称为必填项，请输入您的昵称");
                flag= false;
            }else if(reg.test(v)==false){
                $("#username_prompt").removeClass().addClass("register_prompt_error").html("请用大小写英文字母、数字，长度为2-20个字符");
                flag= false;
            }else{
                $("#username_prompt").removeClass("register_prompt_error");   
                flag=true;
            }
            break;
        case "password":
            $("#pwd_prompt").html("");
            var reg=/^[a-zA-Z0-9]{6,20}$/;
            if(v==""){
                $("#pwd_prompt").removeClass().addClass("register_prompt_error").html("密码为必填项，请设置您的密码");
                flag=false;
            }else if(reg.test(v)==false){
                $("#pwd_prompt").removeClass().addClass("register_prompt_error").html("请用大小写英文字母、数字，长度为6-20个字符");
                flag=false;
            }else{
                $("#pwd_prompt").removeClass("register_prompt_error"); 
                flag=true;
            }
            break;
        case "repassword":
            $("#repwd_prompt").html("");
            if(v==""){
                $("#repwd_prompt").removeClass().addClass("register_prompt_error").html("请再次输入您的密码");
                flag=false;
            }else if($("#password").val()!=v){
                $("#repwd_prompt").removeClass().addClass("register_prompt_error").html("两次输入密码不一致，请重新输入");
                flag=false;
            }else{
                $("#repwd_prompt").removeClass("register_prompt_error"); 
                flag=true;
            }
            break;
        case "mobile":
            $("#mobile_prompt").html("");
            var reg=/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
            if(v==""){
                $("#mobile_prompt").removeClass().addClass("register_prompt_error").html("手机为必填项，请输入你的手机号码");
                flag=false;
            }else if(reg.test(v)==false){
                $("#mobile_prompt").removeClass().addClass("register_prompt_error").html("手机号码格式不正确，请重新输入");
                flag=false;
            }else{
                $("#mobile_prompt").removeClass("register_prompt_error"); 
                flag=true;
            }
            break;
        }
        return flag;
    }
})(jQuery);
