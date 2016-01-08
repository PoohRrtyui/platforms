           //获取项目url（含端口）
           var serverUrl = window.location.host;
           // 获取项目名称
           var serverProPath = window.location.pathname;
           var serverProName = serverProPath.split("/")[1];
           // 拼接项目访问地址
           var projectUrl = "http://" + serverUrl + "/" + serverProName;

var table;
var addAppInfo = {
	init : function() {
		var appId = $("#appId").val();
		if(appId!=0){
			$(".form-group").show();
		}
		$.ajax({
			async : false,
			type : "POST",
			dataType:"JSON",
			data : {"id":appId},
			url : "appInfo/findById",
			success: function(data){
				$(".form-group").show();
				$("#appName").val(data.appName);
				$("#platform").val(data.platform);
				$("#locationCode").val(data.locationCode);
				$("#original").attr("src",projectUrl+data.iconUrl);
				$("#original").val(data.iconUrl);
				$("#summery").val(data.summery);
			}});
		addAppInfo.insertAppInfo();


	},
	//插入数据 
	insertAppInfo : function() {
		$(document).on("click", "#saveInfo", function() {
			if($("#appId").val()!=null&&$("#appId").val()!=''){
				var appId = $("#appId").val();
				var appName = $("#appName").val();
				var platform = $("#platform").val();
				var locationCode = $("#locationCode").val();
				var iconUrl = $("#thumnail").val();
				if(iconUrl==null||iconUrl==''){
					var iconUrl = $("#original").val();
				}
				var summery = $("#summery").val();
				if (appName!=null&&appName!=''){
					
					$.ajax({
						async : false,
						type : "POST",
						data : {"appName":appName,"appId":appId},
						url : "appInfo/updateValidate",
						success: function(data){
							if(data == 0){
								$.ajax({
									type : "POST",
									url : 'appInfo/addAppInfo',
									data : {
										"appId" : appId,
										"appName" : appName,
										"platform" : platform,
										"locationCode" : locationCode,
										"iconUrl" : iconUrl,
										"summery" : summery,
									},
									success : function(data) {
										layer.closeAll();
										layer.msg("修改成功!", {
											icon : 1
										});
										location.href ="appInfo/show";
									},
									error : function(data) {
										layer.msg('修改失败！', {icon: 2});
									}
								});
							}else{
								layer.msg("应用系统名称已存在！",{icon: 10});
							}
						}});
				}else{
					layer.tips('应用名称必填','#appName');
				}
			}else{
				var appName = $("#appName").val();
				var platform = $("#platform").val();
				var locationCode = $("#locationCode").val();
				var iconUrl = $("#thumnail").val();
				var summery = $("#summery").val();
			
				if (appName!=null&&appName!=''){

					$.ajax({
						async : false,
						type : "POST",
						data : {"appName":appName},
						url : "appInfo/updateValidate",
						success: function(data){
							if(data == 0){
								$.ajax({
									type : "POST",
									url : 'appInfo/addAppInfo',
									data : {
										"appName" : appName,
										"platform" : platform,
										"locationCode" : locationCode,
										"iconUrl" : iconUrl,
										"summery" : summery,
									},
									success : function(data) {
										
										layer.closeAll();
										layer.msg("添加成功!", {
											icon : 1
										});
										location.href ="appInfo/show";
									},
									error : function(data) {
										alert("添加失败！");
									}
								});
							}else{
								layer.msg("应用系统名称已存在！",{icon: 10});
							}
						}});
				}else{
					layer.tips('应用名称必填','#appName');
				}
			}
			
		});
		$(document).on("click", "#cancelCodeMaster", function() {
			layer.closeAll();
		});
	}
};