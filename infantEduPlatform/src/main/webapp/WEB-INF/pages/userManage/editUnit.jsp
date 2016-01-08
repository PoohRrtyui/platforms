<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script type="text/javascript" src="static/plugins/zTree3.5.18/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/unit.js"></script>
<body>
	<div class="col-sm-10" id="settings">
	<input type="hidden" value="${ids}" id="ids" name="ids">
	<input type="hidden" value="${schoolId}" id="sId" name="sId">
	<input type="hidden" value="${treeId}" id="treeId" name="treeId">
	<input type="hidden" value="${isClass}" id="isClass" name="isClass">
		<div	
			style="width: 13%; height: auto; float: left;">
			<ul id="unitTree" class="ztree"></ul>
		</div>
	</div>
	<script src="static/plugins/iCheck/icheck.min.js"></script>
		<script type="text/javascript">
		$(function(){
			var sId = $("#sId").val();
			var ids = $("#ids").val();
			var treeId = $("#treeId").val();
			var isClass = $("#isClass").val();
			var schoolId = $("#schoolId").val();
			var setting = {  
					 async: {
						 	autoParam:["id=id"],
					        enable: true,    
					        type:'post',  
					        url:"user/select",   
					        otherParam:{"schoolId":sId}
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
						 onClick:czTreeOnClick,
							 }   
			};
			
			function czTreeOnClick(event, treeId, treeNode) { 
				/* $("#tId").val(treeNode.id);
				$("#isClas").val(treeNode.isClass); */
				saveUnit(event, treeId, treeNode);
				/* var path = "user/list?schoolId=" + schoolId + "&treeId=" + $("#tId").val() + "&isClass=" + $("#isClas").val();
				$('#tbl_user_mgr').DataTable().ajax.url( path ).load(); */
				/* User.message("#tbl_user_mgr",path); */
				/* $('#tbl_user_mgr').DataTable(); */
				//User.init("#tbl_user_mgr");
				/* $('#tbl_user_mgr').DataTable().ajax.reload(); */
				}
			function saveUnit(event, treeId, treeNode){
				if(treeNode.isClass=isClass){
					$.ajax({
						url : 'user/saveUnit',
						type : "POST",
						data : {
							"ids" : $("#ids").val(),
							"treeId": treeId,
							"tId": treeNode.id,
							"isClas": treeNode.isClass
						},
						success : function(data) {
							layer.msg('操作成功！', {icon: 1});
							layer.closeAll();
							$('#tbl_user_mgr').DataTable().ajax.reload();
							/* $("input[name='chkAll']").prop("checked", false); */
						},
						error : function(data) {
							layer.msg('操作失败！', {icon: 2});
						}
					});
				}else{
					layer.alert('只能操作同一机构下的单位信息！', {icon: 2});
				}
			}
			$(document).ready(function(){	
				departmentTree=$.fn.zTree.init($("#unitTree"), setting); 
				}); 
		});
		</script>
</body>
