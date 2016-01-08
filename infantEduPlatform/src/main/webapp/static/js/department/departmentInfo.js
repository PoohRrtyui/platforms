var schoolId=Request("schoolId");
(function(){
	var departmentTree;
	var setting = {  
			 async: {
				 	autoParam:["id=id"],
			        enable: true,    
			        type:'post',  
			        url:"departmentInfo/select",
			       // autoParam: ["schoolId"]
			        otherParam: { "schoolId":schoolId}
			    },
			  view: {
					selectedMulti: false
				},
			 data: { 
				  simpleData: {     
				   enable: true,  
				   idKey: "id",
				   pIdKey: "pId",  
				   rootPId: 0,
				  }                            
				 }, 
			 callback: {       
				 onClick:zTreeOnClick,
					 }   
	};
	
	function zTreeOnClick(event, treeId, treeNode) {  
			show();
		$.ajax({
			type:"POST",
			dataType:"JSON",
			data:{"id":treeNode.id},
			url:"departmentInfo/findById",
			success:function(data){
				$("#dename").val(data.departmentName);
				$("#queryorderNo").val(data.orderNo);
				$("#panamecol").val(data.dutyPerson);
				$("#deption").val(data.description);
				$("#tephone").val(data.telephone);
				$("#queryfax").val(data.fax);
				$("#queryemail").val(data.email);
				
				}
			});
		}
	$(document).ready(function(){	
		departmentTree=$.fn.zTree.init($("#tree"), setting);   
		}); 
	
	//添加窗口
	$("#addDepartmentInfo").on("click",function(){
		var addDepartment = departmentTree.getSelectedNodes();
		if(addDepartment.length==0||(addDepartment!=null&&addDepartment.length == 1)){
			$.ajax({
				async : false,
				type : "post",
				url : "departmentInfo/updatedepartment",
				success : function(data){	
					layer.open({
					    type: 1,
					    title: false,
					    closeBtn: 1,
					    area: ['800px', '500px'],				    
					    shadeClose: true,
					    content: data
					});		
					submitAdd();
				}
			});
		}
	});
	
	//提交添加
	function submitAdd(){
		$("#saveInfo").on("click",function(){	
			var addDepartment = departmentTree.getSelectedNodes();
			var orderNo=$("#orderNo").val();
			var fax=$("#fax").val();
			var email=$("#email").val();
			var name = $("#nameAdd").val();
			var description = $("#descriptionAdd").val();
			var telephone = $("#telephoneAdd").val();
			var person = $("#personAdd").val();
		//	if(name==null||name==""||description==null||description==""||telephone==null||telephone==""||person==null||person==""){
		//		layer.msg('不能为空！', {icon: 2});
		//	}else if(name.length > 10){
		//		layer.msg('部门名不能多余10字！', {icon: 10});
		//	}else{
		var boolean = formValidation($("#bureauForm"));
		if(boolean){
					//部门查重 
					var pId;
					if(addDepartment.length == 0){
						pId = 0;
					}else{
						pId = addDepartment[0].id;
					}
					$.ajax({
						async : false,
						type : "POST",
						data : {"pId":pId,"name":name,"schoolId":schoolId},
						url : "departmentInfo/updateValidate",
						success: function(data){
							if(data == 0){
								$.ajax({
									async : false,
									dataType : "JSON",
									type : "POST",
									data : {"DepartmentName":name,"orderNo":orderNo,"fax":fax,"email":email,"Description":description,"Telephone":telephone,"DutyPerson":person,
										"parentId":pId,"schoolId":schoolId},
									url :"departmentInfo/addDepartmentInfo",
									success : function(data) {
										layer.closeAll();
										layer.msg('添加成功！', {icon: 1});
											if(pId==0){
												$.fn.zTree.init($("#tree"), setting);
											}else{
												var zTree = $.fn.zTree.getZTreeObj("tree"); 
												var treeNode = zTree.getNodeByParam("id", pId, null);
												treeNode.drag = false;
												zTree.removeChildNodes(treeNode);
												$.ajax({
													async : false,
													type : "post",
													dataType:"JSON",
													data : {"id":pId,"schoolId":schoolId},
													url : "departmentInfo/select",
													success : function(data){
														zTree.addNodes(treeNode, data);
													}
												});
											}
													},
									error : function(data) {
											layer.msg("添加失败！",{icon: 2});
													}
											});	
							}else{
								layer.msg('部门重名！', {icon: 10});
							}
						}
					});
				
			}
		});
	}
	
	////部门删除
	$("#deleteDepartmentInfo").on("click",function(){
		var addDepartment = departmentTree.getSelectedNodes();//栏目id列表
		if(addDepartment!=null&&addDepartment.length == 1){
			var delId = addDepartment[0].id;
			var pId=addDepartment[0].pId;
		layer.confirm('确认删除吗？', {
		    btn: ['删除','取消'] //按钮
		}, function(){
			if(addDepartment[0].isParent){	
				layer.msg('此部门有子部门，无法删除！', {icon: 3});
			}else{
				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					data : {"id" : delId},
					url :"departmentInfo/selectCount",
					success : function(data) {
						if(data>0){
							layer.msg('此部门已被用户使用，无法删除！', {icon: 3});
						}else{
							$.ajax({
							type : "POST",
							async : false,
							dataType : "JSON",
							data : {"id" : delId,"pId":pId},
							url :"departmentInfo/deleteDepartmentInfo",
							success : function(data) {
								if(data == '1'){
									layer.msg('删除成功！', {icon: 1});
										hide();
									var pId=addDepartment[0].pId;
									
									if(pId == 0){
										$.fn.zTree.init($("#tree"), setting);
									}else{
										$.ajax({
											type : "POST",
											async : false,
											dataType : "JSON",
											data : {"id" : delId,"pId":pId},
											url :"departmentInfo/findCound",
											success : function(data) {
												var zTree = $.fn.zTree.getZTreeObj("tree"); 
												var sNodes = zTree.getSelectedNodes();	
												var node = sNodes[0].getParentNode();
												if(data>0){
													node.drag = false;
													zTree.removeChildNodes(node);
													$.ajax({
														async : false,
														type : "post",
														dataType:"JSON",
														data : {"id":node.id,"schoolId":schoolId},
														url : "departmentInfo/select",
														success : function(data){
															zTree.addNodes(node, data);
														}
													});
												}else{
													if(node.pId==0){
														$.fn.zTree.init($("#tree"), setting);
													}else{
													var treeNode=node.getParentNode();
													treeNode.drag = false;
													zTree.removeChildNodes(treeNode);
													$.ajax({
														async : false,
														type : "post",
														dataType:"JSON",
														data : {"id":treeNode.id,"schoolId":schoolId},
														url : "departmentInfo/select",
														success : function(data){
															zTree.addNodes(treeNode, data);
														}
													});
													}	
												}
												}
											});
									}
								}
							},
							error : function(data) {
								layer.msg('删除失败！', {icon: 2});
							},
						});
						}				
					}
				});
			}
		});
		}else if(addDepartment==null || addDepartment == ""){
			layer.msg('请选择要删除的部门！', {icon: 10});
		}
	});
	
	
	////部门修改
	$("#updateDepartmentInfo").on("click",function(){
		var treeArray = departmentTree.getSelectedNodes();
		if(treeArray.length == 0){
			layer.msg('请选择要修改的部门！', {icon: 10});
		}else{
			$.ajax({
				async : false,
				type : "post",
				url : "departmentInfo/updatedepartment",
				success : function(data){
					layer.open({
					    type: 1,
					    title: false,
					    closeBtn: 1,
					    area: ['800px', '500px'],				    
					    shadeClose: true,
					    content: data
					});	
					$("#nameAdd").val($("#dename").val());
					$("#orderNo").val($("#queryorderNo").val());
					$("#descriptionAdd").val($("#deption").val());
					$("#telephoneAdd").val($("#tephone").val());
					$("#fax").val($("#queryfax").val());
					$("#email").val($("#queryemail").val());
					$("#personAdd").val($("#panamecol").val());
					submitUpdate();
				}
			});
		}
	});

	//提交修改
	function submitUpdate(){
		$("#saveInfo").on("click",function(){	
			var addDepartment = departmentTree.getSelectedNodes();//栏目id列表
			var name = $("#nameAdd").val();
			var orderNo=$("#orderNo").val();
			var fax=$("#fax").val();
			var email=$("#email").val();
			var description = $("#descriptionAdd").val();
			var telephone = $("#telephoneAdd").val();
			var person = $("#personAdd").val();		
		//	if(name.length > 10){
		//		layer.msg('部门名不能多余10字！', {icon: 10});
		//	}else{
			var boolean = formValidation($("#bureauForm"));
			if(boolean){
					//部门查重
					var pId=addDepartment[0].pId;
					var id=addDepartment[0].id;
					$.ajax({
						async : false,
						type : "POST",
						data : {"pId":pId,"name":name,"id":id,"schoolId":schoolId},
						url : "departmentInfo/updateValidate",
						success: function(data){
							if(data == 0){
								$.ajax({
									async : false,
									dataType : "JSON",
									type : "POST",
									data : {"DepartmentName":name,"orderNo":orderNo,"fax":fax,"email":email,"Description":description,"Telephone":telephone,"DutyPerson":person,
										"DepartmentId":id},
									url :"departmentInfo/addDepartmentInfo",
									success : function(data) {
										layer.closeAll();
											layer.msg("修改成功！",{icon: 1});
												hide();
											if(pId==0){
												$.fn.zTree.init($("#tree"), setting);
											}else{
												var zTree = $.fn.zTree.getZTreeObj("tree"); 
												var treeNode = zTree.getNodeByParam("id", pId, null);
												treeNode.drag = false;
												zTree.removeChildNodes(treeNode);
												$.ajax({
													async : false,
													type : "post",
													dataType:"JSON",
													data : {"id":pId,"schoolId":schoolId},
													url : "departmentInfo/select",
													success : function(data){
														zTree.addNodes(treeNode, data);
													}
												});
											}
													},
									error : function(data) {
											layer.msg("修改失败！",{icon: 2});
													}
											});	
							}else{
								layer.msg("部门重名！",{icon: 10});
							}
						}
					});
				
			}
		});
	}	
	
	function show(){
		$("#dename").show();
		$("#queryorderNo").show();
		$("#panamecol").show();
		$("#deption").show();
		$("#tephone").show();
		$("#queryfax").show();
		$("#queryemail").show();
	}
	
	function hide(){
		$("#dename").hide();
		$("#queryorderNo").hide();
		$("#panamecol").hide();
		$("#deption").hide();
		$("#tephone").hide();
		$("#queryfax").hide();
		$("#queryemail").hide();
	}
	
	
})(jQuery);