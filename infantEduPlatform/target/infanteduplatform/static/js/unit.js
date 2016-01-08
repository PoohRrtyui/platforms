var table;
var User = {
	init : function() {
		User.message();
		User.addUserInfo();
		User.setAdmin();
		User.closeAdmin();
		User.editUnit();
		User.audit();
		User.userImport();
/*		User.del();
*///		User.addCodeMaster();
//		User.insertCodeMaster();
		
	},

	message : function() {
		var tpl = $("#tpl").html();
		//预编译模板
		var template = Handlebars.compile(tpl);
		var sId = $("#schoolId").val();
		var tId = $("#tId").val();
		var isClas = $("#isClas").val();
		 $('#tbl_user_mgr').DataTable({
			// 服务器数据加载
				serverSide : true,
				// 搜索框
				searching : false,
				// 分页框
				lengthChange : false,
				// 分页数（数组）
				lengthMenu : [ 10 ],
				//排序屏蔽
				ordering:false,
//			fnDrawCallback:[
//				_reload(path)
//			],
			ajax: {
				url : 'user/list',
				type: 'post',
				data : {
					schoolId : sId,
					treeId : tId,
					isClass  : isClas,
				},
			}, 
			columns: [
					{
						data : null,
						render: function ( data, type, row ) {
					            return '<input type="checkbox" name="chkOne" class="editor-active" value="'+data.userId+'" idAdmin="'+data.idAdmin+'">';
					    }
					},
			      	{data: "userId"},                 
			      	{data: "userName"}, 
			      	{data: "name"}, 
			    	{data: "nickName"}, 
			      	{data: function(data){
						if(1==data.userType){
							if(1==data.isAdmin){
								return "管理员";
							}else if(2==data.isAdmin){
								return "默认管理员";
							}else if(3==data.isAdmin){
								return "超级管理员";
							}else{
								if('S'==data.organizationType){
									return "学校教工";
								}else{
									return "单位用户";
								}
							}
						}else if(4==data.userType){
							if(1==data.type){
								return "主班";
							}else if(2==data.type){
								return "配班";
							}else{
								return "教师";
							}
						}else if(3==data.userType){
							return "学生";
						}else{
							return "其他";
						}
					}},
//			      	{data: "gendercode"}, 
			      	{data: function(data){
						if(1==data.gendercode){
							return "男";
						}else if(2==data.gendercode){
							return "女";
						}else{
							return "不详";
						}
					}},
			      	{data: "telephone"}, 
			      	{data: "mobile"}, 
//			      	{data: function(data){
//			      		format(data.creationTime, 'yyyy-MM-dd')
//					}}, 
			      	{data: function (data) {
                        var context =
                        {
                            func: [
                                {"name": "修改", "fn": "User.edit(\'"
		                                	+ data.userId
											+ "\',\'"
											+ sId
											+ "\',\'"
											+ tId
											+ "\',\'"
											+ isClas
											+ "\',\'"
											+data.userType
											+ "\')", "type": "primary"},
                                {"name": "删除", "fn": "User.del(\'"
											+ data.userId
											+ "\',\'"
											+ sId
											+ "\',\'"
											+ tId
											+ "\',\'"
											+ isClas
											+ "\',\'"
											+data.userType
											+ "\')", "type": "danger"}
                            ]
                        };
                        var html = template(context);
                        return html;
                    }}, 
			       ],
			       /*columnDefs: [
				                   {
				                       targets: 7,
				                       
				                   }
				                   
				               ],*/
//	               fnDrawCallback:[
//						function zTreeOnClick(event, treeId, treeNode) { 
//							$("#tId").val(treeNode.id);
//							$("#isClas").val(treeNode.isClass);
//							/* _reload() */
//							var path = "user/list?schoolId=" + 1 + "&treeId=" + $("#tId").val() + "&isClass=" + $("#isClas").val();
//							/* var tab = Tab.init("#tal_finalists",path); */
//							_reload(path);
//	}
//				                               ],
			      "language": {                 
			      	"lengthMenu": "_MENU_ 条记录每页",
			      	"zeroRecords": "没有找到记录",
			      	"info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
			      	"infoEmpty": "无记录",
			      	"infoFiltered": "(从 _MAX_ 条记录过滤)",
			      	"paginate": {
			      		"previous": "上一页",                     
			      		"next": "下一页"               
			      	}            
			      },  
			      "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" + "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",  
				
		
		});
	},
	//用户新增
	addUserInfo:function(){
        $("#addUserInfo").on("click",function(){
        	var sId = $("#schoolId").val();
    		var tId = $("#tId").val();
    		var isClas = $("#isClas").val();
//    		var type = $("#type").val();
    		var type = $("type").val();
            $.post('user/editUser', {treeId:tId,isClass:isClas,schoolId:sId,type:type}, function(str){
                layer.open({
                    type: 1,
                    title:"用户编辑",
                    area:['600px','800px'],
                    content: str 
                });
            });
        });
    },
  //全选功能
	 checkAll:function(){
		$(document).on("click",".chkAll",function(obj){
			$("input[name='chkOne']").prop("checked", this.checked);
			
		});
	},
	
    //用户修改
    edit:function(userid,schoolId,treeId,isClass,userType){
    		/*var sId = $("#schoolId").val();*/
//    		var type = $("#type").val();
    		var type = "S";
    		$.post('user/editUser', {userId:userid,treeId:treeId,isClass:isClass,schoolId:schoolId,userType:userType,type:type}, function(str){
    			layer.open({
    				type: 1,
    				title:"用户编辑",
    				area:['600px','800px'],
    				content: str 
    			});
    		});
    },
    /* 删除 */
	del : function(userid,schoolId,treeId,isClass,userType) {
		userType=4;
		$.ajax({
			url : "user/delUser",
			type : "POST",
			data : {
				"userId" : userid,
				"schoolId" : schoolId,
				"treeId" : treeId,
				"isClass" : isClass,
				"userType": userType
			},
			success : function(data) {
				layer.closeAll();
				layer.msg("删除成功!", {	
					icon : 1
				});
				$('#tbl_user_mgr').DataTable().ajax.reload();
			},
			error : function(data) {
				layer.msg("操作失败!", {
					icon : 1
				});
			}
		});
	},
	/*设置管理员*/
	setAdmin :function(){
		$("#setAdmin").on("click",function(){
			var id = new Array();
			$(":checkbox[name='chkOne']").each(function (){
				if($(this).prop("checked")){
					id.push($(this).val());
				}			
		});
			var ids = id.toString();
			if(ids.length!=0){
				$.ajax({
					url : 'user/updateAdmin',
					type : "POST",
					data : {
						"ids" : ids,
						"schoolId" : $("#schoolId").val(),
					},
					success : function(data) {
						$('#tbl_user_mgr').DataTable().ajax.reload();
						layer.msg('操作成功！', {icon: 1});
						$("input[name='chkAll']").prop("checked", false);
						
					},
					error : function(data) {
						layer.msg('操作失败！', {icon: 2});
						
					}
				});
		}else{
			layer.msg('请选择用户！', {icon: 10});
		}
			
		});
	},
	/*设置管理员*/
	closeAdmin:function(){
		$("#colseAdmin").on("click",function(){
			var id = new Array();
			$(":checkbox[name='chkOne']").each(function (){
				if($(this).prop("checked")){
					id.push($(this).val());
				}			
		});
			var ids = id.toString();
			if(ids.length!=0){
				$.ajax({
					url : 'user/closeAdmin',
					type : "POST",
					data : {
						"ids" : ids
					},
					success : function(data) {
						$('#tbl_user_mgr').DataTable().ajax.reload();
						layer.msg('操作成功！', {icon: 1});
						$("input[name='chkAll']").prop("checked", false);
						
					},
					error : function(data) {
						layer.msg('操作失败！', {icon: 2});
						
					}
				});
		}else{
			layer.msg('请选择用户！', {icon: 10});
		}
			
		});
	},
	/*更改单位*/
	editUnit:function(){
		$("#editUnit").on("click",function(){
			var sId = $("#schoolId").val();
			var tId = $("#tId").val();
			var isClas = $("#isClas").val();
			var id = new Array();
			$(":checkbox[name='chkOne']").each(function (){
				if($(this).prop("checked")){
					id.push($(this).val());
				}			
			});
			var ids = id.toString();
			if(ids.length!=0){
			$.post('user/editUnit', {ids:ids,schoolId:sId,treeId:tId,isClass:isClas}, function(str){
                layer.open({
                    type: 1,
                    title:"更换单位",
                    area:['200px','500px'],
                    content: str 
                });
            });
			}else{
				layer.msg('请选择用户！', {icon: 10});
			}
		});
	},
	
	/*用户认证*/
	audit:function(){
		$("#audit").on("click",function(){
			var id = new Array();
			$(":checkbox[name='chkOne']").each(function (){
				if($(this).prop("checked")){
					id.push($(this).val());
				}			
			});
			var ids = id.toString();
			if(ids.length!=0){
				$.ajax({
					url : 'user/auditUser',
					type : "POST",
					data : {
						"ids" : ids
					},
					success : function(data) {
						$('#tbl_user_mgr').DataTable().ajax.reload();
						layer.msg('操作成功！', {icon: 1});
						$("input[name='chkAll']").prop("checked", false);
						
					},
					error : function(data) {
						layer.msg('操作失败！', {icon: 2});
						
					}
				});
			}else{
				layer.msg('请选择用户！', {icon: 10});
			}
		});
	},
	
	/*用户导入*/
	userImport:function(){
		$("#userImport").on("click",function(){
			var fileName = $("#excelFile").val();
			var index1=fileName.lastIndexOf("."); 
			var index2=fileName.length;
			var name=fileName.substring(index1,index2);
			if(fileName){
			if(name==".xls"||name==".xlsx"){
				if($("#form1").submit()){
					$('#tbl_user_mgr').DataTable().ajax.reload();
					layer.msg('操作成功！', {icon: 1});
				}
//				data +=$("#form1").serialize()+"&sId="+sId+"&tId="+tId+"&isClas="+isClas+"&type="+type;
//					$.ajax({
//						url : 'user/userImport',
//						type : "POST",
//						data : $("#form1").serialize()+"&sId="+sId+"&tId="+tId+"&isClas="+isClas+"&type="+type,
//						success : function(data) {
//							$('#tbl_user_mgr').DataTable().ajax.reload();
//							layer.msg('操作成功！', {icon: 1});
//							$("input[name='chkAll']").prop("checked", false);
//							
//						},
//						error : function(data) {
//							layer.msg('操作失败！', {icon: 2});
//							
//						}
//					});
			}else{
				layer.alert('请选择正确的Excel文件！', {icon: 10});
				return false;
			}
			}else{
				layer.alert('请选择要导入的文件！', {icon: 10});
				return false;
			}
		});
	},
	
};
