/**
 * author:lpp 通知公告
 */
var table;
var bulletin = {
	init : function() {
		bulletin.message();
		bulletin.deleteBulletinList();
		bulletin.checkAll();
	},

	// dataTables数据表查询
	message : function() {
		var tpl = $("#tpl").html();
		// 预编译模板
		var template = Handlebars.compile(tpl);
		var bulletinType = 1;
		$('#bulletin')
				.DataTable(
						{
							// 服务器数据加载
							serverSide : true,
							// 搜索框
							searching : true,
							// 分页框
							lengthChange : false,
							// 分页数（数组）
							lengthMenu : [ 10 ],
							// 列头排序
							ordering : false,
							ajax : {
								type : 'post',
								url : 'bulletin/queryBulletinList',
								data : {
									"bulletinType" : bulletinType
								},
							},
							columns : [
									{
										data : null,
										render : function(data, type, row) {
											return '<input type="checkbox" name="chkOne" class="editor-active" value="'
													+ data.bulletinId + '">';
										}
									},
									{
										"data" : "organizationId",
									},
									{
										"data" : "title",
									},
									{
										"data" : function(data) {
											if (1 == data.bulletinType) {
												return "通知公告";
											} else if (2 == data.bulletinType) {
												return "任务";
											} else if (3 == data.bulletinType) {
												return "动态";
											} else if (4 == data.bulletinType) {
												return "健康关怀";
											} else if (5 == data.bulletinType) {
												return "育儿助手";
											} else if (6 == data.bulletinType) {
												return "消息";
											} else if (8 == data.bulletinType) {
												return "成长档案";
											}
										}
									},
									{
										"data" : "bulletinContent",
									},
									{
										"data" : "classId",
									},
									{
										"data" : function(data) {
											if (1 == data.bulletinLevel) {
												return "本班级";
											} else if (2 == data.bulletinLevel) {
												return "本园";
											} else if (3 == data.bulletinLevel) {
												return "公开";
											}
										}
									}, {
										"data" : "strCreationTime"
									}, {
										"data" : "createdBy"
									}, {
										"data" : null
									} ],
							columnDefs : [ {
								targets : 9,
								render : function(data) {
									var context = {
										func : [
												{
													"name" : "修改",
													"fn" : "bulletin.edit(\'"
															+ data.bulletinId
															+ "\',\'"
															+ data.organizationId
															+ "\',\'"
															+ data.title
															+ "\',\'"
															+ data.bulletinType
															+ "\',\'"
															+ data.bulletinContent
															+ "\',\'"
															+ data.classId
															+ "\',\'"
															+ data.bulletinLevel
															+ "\',\'"
															+ data.creationTime
															+ "\',\'"
															+ data.createdBy
															+ "\')",
													"type" : "primary"
												}
												]
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

	// 全选功能
	checkAll : function() {
		$(document).on("click", ".chkAll", function(obj) {
			$("input[name='chkOne']").prop("checked", this.checked);

		});
	},

	// 批量删除
	deleteBulletinList : function(bulletinId) {
		$("#deleteList").on("click", function() {
			var id = new Array();
			$(":checkbox[name='chkOne']").each(function() {
				if ($(this).prop("checked")) {
					id.push($(this).val());

				}
			});
			var ids = id.toString();
			if (ids.length != 0) {
				$.ajax({
					url : 'bulletin/deleteList',
					type : "POST",
					data : {
						"ids" : ids
					},
					success : function(data) {
						$('#bulletin').DataTable().ajax.reload();
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