var changepwd = {
	init : function() {
		changepwd.passwordstrength();
		changepwd.changePwd();
	},
	passwordstrength : function() {
		$('#newpwd')
				.keyup(
						function(e) {
							var strongRegex = new RegExp(
									"^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$",
									"g");
							var mediumRegex = new RegExp(
									"^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))" +
									"|((?=.*[a-z])(?=.*[0-9]))).*$",
									"g");
							var enoughRegex = new RegExp("(?=.{6,}).*", "g");
							if (false == enoughRegex.test($(this).val())) {
								$('#passstrength').html('密码太简单');
							} else if (strongRegex.test($(this).val())) {
								$('#passstrength').className = 'ok';
								$('#passstrength').html('强');
							} else if (mediumRegex.test($(this).val())) {
								$('#passstrength').className = 'alert';
								$('#passstrength').html('中');
							} else {
								$('#passstrength').className = 'error';
								$('#passstrength').html('弱');
							}
							return true;
						});
	},
	changePwd : function() {
		$("#change").on("click", function() {
			var oldpwd = $("#oldpwd").val();
			var newpwd = $("#newpwd").val();
			var confirmpwd = $("#confirmpwd").val();
			var boolean = formValidation($("#test"));
			if (boolean) {
				if (newpwd == oldpwd) {
					alert("新密码与旧密码不能一致");
				} else {
					if (newpwd == confirmpwd) {
						$.ajax({
							url : 'changepwd/changepwd',
							type : "POST",
							data : {
								"oldpwd" : oldpwd,
								"newpwd" : newpwd
							},
							success : function(data) {
								if (data == 0) {
									layer.msg("原密码不正确!", {
										icon : 1
									});
								} else {
									layer.msg("修改成功!", {
										icon : 1
									});
								}
							},
							error : function(data) {
								layer.msg("操作失败!", {
									icon : 1
								});
							}
						});
					} else {
						layer.msg("两次密码输入不一致!", {
							icon : 1
						});
					}
				}
			}
		});
	}
};

