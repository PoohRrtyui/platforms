var table;

var discussion = {
	init : function() {
		// 加载树
		discussion.showTree();
		// 绑定数据
		discussion.message();
		// 删除方法
		discussion.deleteDiscussion();
		// 全选方法
		discussion.checkAll();
	},
	showTree : function() { 		
		$.ajax({ 
			type : "POST", 
			url : "discussion/subjectList", 
			dataType: "json",
			success : function(data) { 
				if(data != null && data.length >0){
					// 加载数据					
					$.fn.zTree.init($("#ztree"), setting, data);
				}
			} 
		}); 
	},
	 zTreeOnClick:function(event, treeId, treeNode, clickFlag) {
		 discussion.message(treeNode.nameKey);
	},

	// dataTables数据表查询
	message : function(subject) {
		if (!subject) {
			subject = "";
		}
		var tpl = $("#tpl").html();
		// 预编译模板
		var template = Handlebars.compile(tpl);
		var organizationId = 1;
		$('#discussion')
				.DataTable(
						{
							// 服务器数据加载
							serverSide : true,
							// 搜索框
							searching : false,
							// 分页框
							lengthChange : false,
							// 分页数（数组）
							lengthMenu : [ 10 ],
							// 列头排序
							ordering : false,
							ajax : {
								type : 'post',
								url : 'discussion/discussionList',
								data : {
									"organizationId" : organizationId,
									"subject" : subject
								},
							},
							columns : [
									{
										data : null,
										render : function(data, type, row) {
											return '<input type="checkbox" name="chkOne" class="editor-active" value="'
													+ data.discussionId + '">';
										}
									}, {
										"data" : "createdBy"
									}, {
										"data" : "authorPhotoUrl"
									}, {
										"data" : "strCreationTime"
									}, {
										"data" : "discussionContent"
									}, {
										"data" : "replyCount"
									}, {
										"data" : "dynamicCount"
									}, {
										"data" : null
									} ],
							columnDefs : [ {
								targets : 7,
								render : function(data) {
									var context = {
										func : [ {
											"name" : "删除",
											"fn" : "codeMaster.del(\'"
													+ data.discussionId + "\')",
											"type" : "danger"
										} ]
									};
									return template(context);
								}
							}

							],
							"language" : {
								"lengthMenu" : "_MENU_ 条记录每页",
								"zeroRecords" : "没有找到记录",
								"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
								"infoEmpty" : "无记录",
								"infoFiltered" : "(从 _MAX_ 条记录过滤)",
								"paginate" : {
									"previous" : "上一页",
									"next" : "下一页"
								}
							},
							"dom" : "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>"
									+ "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>"

						});
	},
	//全选功能
	 checkAll:function(){
		$(document).on("click",".chkAll",function(obj){
			$("input[name='chkOne']").prop("checked", this.checked);
			
		});
	},
	deleteDiscussion:function(){
		$("#deleteDiscussion").on("click", function() {
			var id = new Array();
			$(":checkbox[name='chkOne']").each(function() {
				if ($(this).prop("checked")) {
					id.push($(this).val());

				}
			});
			var ids = id.toString();
			if (ids.length != 0) {
				$.ajax({
					url : 'discussion/deleteList',
					type : "POST",
					data : {
						"ids" : ids
					},
					success : function(data) {
						$('#discussion').DataTable().ajax.reload();
						layer.msg('操作成功！', {
							icon : 1
						});
						$("input[name='chkAll']").prop("checked", false);
					},
					error : function(data) {
						layer.msg('操作失败！', {
							icon : 2
						});
					}
				});
			} else {
				layer.msg('请选择！', {
					icon : 10
				});
			}
		});
	}

};

//树属性设置
var setting = {
		treeId:"ztree",
		treeObj:null,
		callback: {
			onClick: discussion.zTreeOnClick
		}
};
