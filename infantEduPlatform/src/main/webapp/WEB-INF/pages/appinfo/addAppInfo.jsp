<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<link rel="stylesheet" href="static/plugins/layer/skin/layer.css" type="text/css" />
		<script type="text/javascript" src="static/js/common/base.js"></script>
		<script src="static/plugins/layer/layer.js"></script>
		<script type="text/javascript" src="static/plugins/dropzone-4.2.0/dropzone.js"></script>
		<script src="static/js/appInfo/addAppInfo.js"></script>
		<script type="text/javascript">
					$(function() {
						addAppInfo.init();
					});
		</script>

        <div class="col-sm-6" id="settings" style="margin-top: 60px;margin-left: 150px;'">
        	<input type="hidden" id="appId" value="${requestScope.id}">
            <form class="form-horizontal" id="bureauForm">              
                <div class="form-group">
                    <label for="appName" class="col-sm-3 control-label">应用系统名称:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="appName"
                               name="appName" placeholder="应用系统名称">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="platform" class="col-sm-3 control-label">应用所属平台：</label>

                    <div class="col-sm-6">
                   	    <select class="form-control" name="platform" id="platform"> 
							<option name="source" value="1">基础教育</option>
							<option name="source" value="2">职业教育</option>
							<option name="source" value="4">高等教育</option>
							<option name="source" value="8">教师教育</option>
							<option name="source" value="16">社会教育</option>
						</select>
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="locationCode" class="col-sm-3 control-label">应用所属地区：</label>

                    <div class="col-sm-6">
                    	<select class="form-control" name="locationCode" id="locationCode"> 
							<option name="source" value="000000">整个平台</option>
							<option name="source" value="000001">门户平台</option>
							<option name="source" value="000002">管理平台</option>
							<option name="source" value="000003">APP平台</option>
						</select>
                    </div>
                 </div>
                 
                <div class="form-group">
                    <label for="descriptionAdd" class="col-sm-3 control-label">简介:</label>

                    <div class="col-sm-6">
                        <textarea class="form-control" id="summery" name="summery"
                               style="resize:none;"  placeholder="简介"/></textarea>
                    </div>                   
                </div> 
                <div class="form-group" style="display: none;">
                    <label for="original" class="col-sm-3 control-label">原始图标:</label>

                    <div class="col-sm-6">
                        <img id="original" height="30" width="30" src="">
                    </div>                   
                </div> 
                </form>
                 <div class="form-group">
                    <label for="iconUrl" class="col-sm-3 control-label" style="padding-left: 115px;">应用图标：</label>

                    <div class="col-sm-6">
                        <form action="filemanage/uploadFiles " class="dropzone" id="dropzone">
							<div class="fallback">
								<input name="upload" type="file" multiple="multiple" />
							</div>
						</form>
                        <input type="hidden" id="thumnail" name="iconUrl" >
                    </div>
                 </div>                 
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-10">
                        <button type="button" id="saveInfo" class="btn btn-danger">保存</button>
                    </div>
                </div>
           
        </div>
        <script>
        
           //获取项目url（含端口）
           var serverUrl = window.location.host;
           // 获取项目名称
           var serverProPath = window.location.pathname;
           var serverProName = serverProPath.split("/")[1];
           // 拼接项目访问地址
           var projectUrl = "http://" + serverUrl + "/" + serverProName;
	            var thumnail = "";
	            var myDropzone;
	            var attachmentsDrop;
	            var uploadParentName = "|resources|uploadImages|appinfo|";
	                $(function(){
	                	 try {
	                		myDropzone = new Dropzone(
	                				"#dropzone",
	                				{
	                					params : {
	                						"dirId" : 1,
	                						"allParentNames" : uploadParentName
	                					},
	                					paramName : "upload", // The name that will be used to
	                											// transfer the file
	                					maxFilesize : 10, // MB
	                					acceptedFiles:"image/*",
	                					addRemoveLinks : true,
	                					dictDefaultMessage : '<p id="aa"><span class="bigger-130 bolder"><i class="upload-icon ace-icon fa fa-cloud-upload blue fa-1x"></i> 拖动缩略图</span> 上传 \
	                		<span class="smaller-80 grey">(或者点击)</span></p> <br /> \
	                		',
	                					dictResponseError : '上传文件出错！',
	
	                					// change the previewTemplate to use Bootstrap progress bars
	                					previewTemplate : "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>",
	                				});
	                		
	                	} catch (e) {
	                		alert('您的浏览器不支持此插件哟~请更换成最新版本的chrome');
	                	} 
	                	myDropzone.on("success", function(file) {
	                		$("#aa,.dz-size").hide();
	                		$(".dz-filename").html("上传成功！");
	                		$(".dz-remove").on("click", function() {
	                			$("#aa").show();
	                		});
	                		var fileData = eval("(" + file.xhr.response + ")");
	                		$("#thumnail")
	                				.val(
	                						 "/resources/uploadImages/appinfo/"
	                								+ fileData.message);
	                	}); 
	                });
            </script>
	</tiles:putAttribute>
</tiles:insertDefinition>