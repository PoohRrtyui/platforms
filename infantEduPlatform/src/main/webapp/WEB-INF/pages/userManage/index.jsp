<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
	<link rel="stylesheet" href="static/plugins/zTree3.5.18/css/metroStyle/metroStyle.css" type="text/css" />
	<script type="text/javascript" src="static/plugins/zTree3.5.18/js/jquery.ztree.all-3.5.min.js"></script>
	<script src="static/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="static/js/unit.js"></script>
	<!-- <script src="static/js/handlebars/handlebars-1.0.0.beta.6.js"></script> -->
	<!--     <script src="static/plugins/dataTables/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="static/plugins/dataTables/dataTables.bootstrap.js" type="text/javascript"></script> -->
    <script src="static/plugins/layer/layer.js"></script>
    <script src="static/plugins/dataTables/dataTables.bootstrap.min.js" type="text/javascript"></script>
    <script src="static/plugins/dataTables/handlebars-1.0.0.beta.6.js" type="text/javascript"></script>
	<script id="tpl" type="text/x-handlebars-template">
    		{{#each func}}
    		<button type="button" class="btn bt
n-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
   			 {{/each}}
		</script>
		<style>
			.ztree li span{font-size:15px;}
		</style>
			<div>
				<!-- Content Wrapper. Contains page content -->
				<div>
					<!-- Content Header (Page header) -->
					<section class="content-header">
						<h1>
							后台管理 <small>用户管理</small>
						</h1>
					</section>
					<!-- Main content -->
					<section class="content">
						<div class="row">
							<div class="col-xs-12">
								<div class="box">
									<div class="box-header">
										<h3 class="box-title"></h3>
										用户管理>>查看
										</h3>
									</div>
									<div class="right">
										<div
											style="width: 13%; height: auto; float: left;">
											<ul id="tree" class="ztree"></ul>
										</div>
										<!-- <div id="channeldetails" style="margin-right: 120px;">
											<table>
											
											</table>
										</div> -->
										<div style="width:85%;float:left;">
											
											<div style="margin:15px auto 0px;">
												<p style="line-height:30px;font-size:14px;font-weight:bold;background-color:#3c8dbc;color:#fff; text-indent:15px;">用户列表</p>
												
												<div class="box-tools" style="float:left;width:170px;margin:15px 0 0 0;">
								                    <div style="width: 150px;" class="input-group">
								                      <input type="text" placeholder="搜索用户名" class="form-control input-sm pull-right" name="table_search" id="table_search">
								                      <div class="input-group-btn">
								                        <button style="padding:8px;" class="btn btn-sm btn-default" id="searchUser"><i class="fa fa-search" ></i></button>
								                      </div>
								                    </div>
								                  </div>
												
												<div style="width:70%;float:right;">
													<input type="button" style="padding:7px 25px;margin:15px;background-color:#7bace9;color:#fff;float:right" id="addUserInfo" value="添加" class="btn btn-{{this.type}} btn-sm" name="add"/>
													
													<input type="button" style="padding:7px 25px;margin:15px;background-color:#7bace9;color:#fff;float:right" id="setAdmin" value="设置管理员" class="btn btn-{{this.type}} btn-sm" name="setAdmin"/>
												
													<input type="button" style="padding:7px 25px;margin:15px;background-color:#dd4b39;color:#fff;float:right" id=colseAdmin value="撤销管理员" class="btn btn-{{this.type}} btn-sm" name="colseAdmin"/>
												
													<input type="button" style="padding:7px 25px;margin:15px;background-color:#f39c12;color:#fff;float:right" id="editUnit" value="更换单位" class="btn btn-{{this.type}} btn-sm" name="editUnit"/>
													
													<input type="button" style="padding:7px 25px;margin:15px;background-color:#f39c12;color:#fff;float:right" id="audit" value="用户认证" class="btn btn-{{this.type}} btn-sm" name="audit"/>
													
													<input type="button" style="padding:7px 25px;margin:15px;background-color:#f39c12;color:#fff;float:right" id="userExport" onclick="userExport()" value="用户导出" class="btn btn-{{this.type}} btn-sm" name="userExport"/>
													<form name="form1" method="post" enctype="multipart/form-data" action="user/userImport" id="form1">
													<input type="hidden" id="schoolId" name="schoolId" value="${schoolId}">
													<input type="hidden" id="type" name="type" value="${type}">
													<input type="hidden" id="isClas" name="isClas"/>
													<input type="hidden" id="tId" name="tId"/>
													<input  name="excelFile" id="excelFile" type="file" />
													<input type="button" style="padding:7px 25px;margin:15px;background-color:#f39c12;color:#fff;float:right" id="userImport" value="用户导入" class="btn btn-{{this.type}} btn-sm" name="userImport"/>
													</form>
												</div>
											</div>
											<table id="tbl_user_mgr" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<!-- <th class="center">
														<label class="pos-rel">
															<input type="checkbox"/>
															<span class="lbl"></span>
														</label>
													</th> -->
													<th><input type="checkbox" id="chkAll" name="chkAll" class="chkAll"></th>
													<th>用户编号</th>
													<th>登录名</th>
													<th>真实姓名</th>
													<th>昵称</th>
													<th>用户身份</th>
													<th>性别</th>
													<th>联系电话</th>
													<th>手机</th>
													<th>操作</th>
												</tr>
											</thead>
	
											<tbody>
												
											</tbody>
										</table>
									</div>
										
										
										
									
									</div>
									</div>
								</div>
							</div>
						</div>
				</div>
				</section>
			</div>

			</div>
			<script type="text/javascript">
			$(function(){
				var schoolId = $("#schoolId").val();
				var setting = {  
						 async: {
							 	autoParam:["id=id"],
						        enable: true,    
						        type:'post',  
						        url:"user/select",   
						        otherParam:{"schoolId":schoolId}
						    },   
					    view: {
								selectedMulti: false
							},
						 data: {                                    
							  simpleData: {     
							   enable: true,  
							   idKey: "id",
							   pIdKey: "pId",  
							   rootPId: 0
							  }                            
							 }, 
						 callback: {       
							 onClick:zTreeOnClick,
								 }   
				};
				
				function zTreeOnClick(event, treeId, treeNode) { 
					$("#tId").val(treeNode.id);
					$("#isClas").val(treeNode.isClass);
					/* _reload() */
					var path = "user/list?schoolId=" + schoolId + "&treeId=" + $("#tId").val() + "&isClass=" + $("#isClas").val();
					/* User.message("#tbl_user_mgr",path); */
					/* $('#tbl_user_mgr').DataTable(); */
					//User.init("#tbl_user_mgr");
					$('#tbl_user_mgr').DataTable().ajax.url( path ).load();
					/* $('#tbl_user_mgr').DataTable().ajax.reload(); */
					};
				//搜索框事件
				$("#searchUser").on("click",function(){
					var keyValue = $("#table_search").val();					
					if(keyValue==""){
						layer.alert("请输入正确的登录名!", {
							icon : 5
						});
					}
					/* _reload() */
					var path = "user/list?schoolId=" + $("#schoolId").val() + "&treeId=" + $("#tId").val() + "&isClass=" + $("#isClas").val() + "&keyValue=" + keyValue;
					$('#tbl_user_mgr').DataTable().ajax.url( path ).load();
					
				});
				
				$(document).ready(function(){	
					tree=$.fn.zTree.init($("#tree"), setting); 
					/* var path = "user/list?schoolId=" + 1; */
					   User.init();
					if($("#type").val()=="B"){
						$("input[name=editUnit]").attr("type","hidden");
					}
					/* User.init("#tbl_user_mgr"); //初始化数据表格，并初始化checkbox的全选功能 */
					}); 
			});
			/*用户导出*/
			function userExport(){
					var id = new Array();
					$(":checkbox[name='chkOne']").each(function (){
						if($(this).prop("checked")){
							id.push($(this).val());
						}			
					});
					var ids = id.toString();
					if(ids.length!=0){
						location.href="user/userExport?ids="+ids+"&treeId=" + $("#tId").val()+"&schoolId=" + $("#schoolId").val()+"&isClass=" + $("#isClas").val()+"&type="+$("#type").val();    
						$('#tbl_user_mgr').DataTable().ajax.reload();
						$("input[name='chkAll']").prop("checked", false);
					}else{
						layer.msg('请选择用户！', {icon: 10});
					}
				};
        </script>
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
