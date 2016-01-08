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
		<script src="static/plugins/validate/jquery.validate.js"></script>
		<script src="static/plugins/validate/jquery.validate.extend.js"></script>
		<script src="static/plugins/layer/layer.js"></script>
		<script type="text/javascript" src="static/plugins/dropzone-4.2.0/dropzone.js"></script>
		<script src="static/js/appInfo/appInfo.js"></script>
		<script id="tpl" type="text/x-handlebars-template">
    		{{#each func}}
    		<button type="button" class="btn bt
			n-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
   			 {{/each}}
		</script>
		<script type="text/javascript">
			$(function() {
				appInfo.init();
			});
		</script>
		<div>
			<div>
				<section class="content-header">
					<h1>应用管理列表</h1>
				</section>
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-body">
								<div>
										<input type="button" id="addAppInfo" value="添加"
											class="btn btn-{{this.type}} btn-sm" name="add"> 
										<input type="button" id="openList" value="开启"
											class="btn btn-{{this.type}} btn-sm" name="openList"> 
										<input type="button" id="closeList" value="关闭"
											class="btn btn-{{this.type}} btn-sm" name="closeList">
										<input type="button" id="deleteList" value="删除"
											class="btn btn-{{this.type}} btn-sm" name="deleteList">  
									</div>
									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th><input type="checkbox" id="chkAll" name="chkAll" class="chkAll"></th>
												<th>应用系统代码</th>
												<th>应用系统名称</th>
												<th>应用所属平台</th>
												<th>应用所属地区</th>
												<th>应用状态</th>
												<th>应用图标</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
									
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
