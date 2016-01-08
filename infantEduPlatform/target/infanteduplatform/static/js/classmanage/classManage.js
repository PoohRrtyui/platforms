var table;
var schoolId = Request("schoolId");

var classManage = {
	path : '',
	init : function(url) {
		message();
		skipAddClass();
	}
};

// dataTables数据表查询
function message() {
	var tpl = $("#tpl").html();
	// 预编译模板
	var template = Handlebars.compile(tpl);
	$('#classmanage')
			.DataTable(
					{
						
						ordering:false,
						retrieve: true,
						// 服务器数据加载
						serverSide : true,
						// 搜索框
						searching : false,
						
						// 分页框
						lengthChange : false,
						// 分页数（数组）
						lengthMenu : [ 5 ],
						ajax : {
							type : 'post',
							url : 'classManage/queryClassList',
							data : {
								"schoolId" : schoolId
							},
						},
						columns : [ {
							"data" : "className"
						}, {
							"data" : "mainTeacherName"
						}, {
							"data" : "withTeacherName"
						}, {
							"data" : "nurserygovernessName"
						}, {
							"data" : "stringEstablishmentDate"
						}, {
							"data" : "usercount"
						}, {
							"data" : null
						}, ],
						columnDefs : [ {
							targets : 6,
							render : function(data) {
								var context = {
									func : [
											{
												"name" : "修改",
												"fn" : "skipupdateClassInfo(\'"
														+ data.classId + /*
																			 * "\',\'" +
																			 * data.systemFlg +
																			 */"\')",
												"type" : "primary"
											},
											{
												"name" : "删除",
												"fn" : "deleteClass(\'"
														+ data.classId + /*
																			 * "\',\'" +
																			 * data.systemFlg +
																			 */"\')",
												"type" : "danger"
											} ]
								};
								var html = template(context);
								return html;
							}
						}/*,
						//as
						{
							targets : 4,
							render : function(data) {
								console.log(data);
								Date date =new Date(data.establishmentDate); 
								SimpleDateFormat k = new SimpleDateFormat("yyyy-MM-dd,HH-mm-ss");
								String kk = k.format(date);
								return kk;
							},
						}*/

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
								+ "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",

					});
}

// 跳转增加页面
function skipAddClass() {
	$("#addclass").on("click",function() {
		$.ajax({
			type:"POST",
			url:"classManage/skipclassadd",
			data:{
				"schoolId":schoolId
			},
			success:function(data){
				layer.open({
				    type: 1,
				    title: '添加班级',
				    area: ['600px', '500px'],
				    shift: 'top', //从左动画弹出
				    content: data
				});
			},
			error:function(data){
				alert("跳转增加页面失败！");
			}
		});
		
	});
}

// 跳转修改班级页面
function skipupdateClassInfo(classId) {
	$.ajax({
		type:"POST",
		url:"classManage/skipclassupdate",
		data:{
			"classId":classId,
			"schoolId":schoolId
		},
		success:function(data){
			layer.open({
			    type: 1,
			    title: '修改班级',
			    area: ['600px', '400px'],
			    shift: 'top', //从左动画弹出
			    content: data
			});
		},
		error:function(data){
			alert("跳转修改页面失败！");
		}
	});
}

// 删除班级
function deleteClass(classId) {
	layer.confirm("确认删除吗？", {icon : 3,title : '提示'}, function(){
		// 查询该学校该班级下是否有学生
		$.ajax({
			type : "POST",
			dataType : "JSON",
			data : {
				"classId" : classId,
				"organizationId" : schoolId
			},
			url : "classManage/queryClassStudents",
			success : function(data) {
				if (data.length == 0) {
					// 该班级下没有学生时，查询该班级下是否有普通教师
					$.ajax({
						type : "POST",
						dataType : "JSON",
						data : {
							"classId" : classId,
							"organizationId" : schoolId
						},
						url : "classManage/queryClassTeachers",
						success : function(data) {
							if (data.length == 0) {
								// 该班级下没有普通教师时，删除班级,并且删除该班级下的主班，配班，保育员
								$.ajax({
									type : "POST",
									url : "classManage/deleteClass",
									data : {
										"classId" : classId,
										"schoolId" : schoolId
									},
									success : function(data) {
										layer.msg("删除成功", {
											icon : 1
										});
										$('#classmanage').DataTable().ajax.reload();
									},
									error : function(data) {
										alert("删除失败！");
									}
								});
							} else {
								// 该班级有教师教职工时，提示无法删除班级！
								layer.msg("该班级下有普通教师，无法删除！请先删除班级下的普通教师", {
									icon : 2
								});
							}
						},
						error : function(data) {
							alert("查询该班级下教师失败！");
						}
					});
				} else {
					// 该班级下有学生时，提示无法删除班级！
					layer.msg("该班级有学生，无法删除！请先删除班级下的学生！", {
						icon : 2
					});
				}
			},
			error : function(data) {
				alert("查询该班级下学生失败！");
			}
		});
	});
}
