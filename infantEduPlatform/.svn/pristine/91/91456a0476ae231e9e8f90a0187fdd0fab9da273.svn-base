var schoolId = $("#schoolId").val();

var UpdateclassManage = {
	path : '',
	init : function(url) {
		queryUpdateClassStyle();/*classStyle中数据从codeMaster表中取*/
		// 下拉框查询
		$('.selectpicker').selectpicker();
		autocompleteTeat();
		// 修改显示班级类型时下拉框选中
		updateClassStyleSelected();
		// 修改班级
		updateClassInfo();
	}
};

//classStyle中数据从codeMaster表中取
function queryUpdateClassStyle() {
	$.ajax({
		async:false,
        type:"POST",
        dataType : "JSON",
        url : 'codeMaster/queryCodeMaster', // wherever your data is
        data:{
        	"largeCategoryCd":"OrganizationStyle"
        },
        success:function(data){
        	var j=data.length;
        	var datahtml="";
        	for(var i=0;i<j;i++) {
              datahtml+='<option value="'+data[i].smallCategoryCd+'">'+data[i].smallCategoryName+'</option>';        		
        	}
        	$("#classstyle").html(datahtml);
        },
        error:function(data){
        	alert("数据字典表中取classStyle数据失败！");
        }
	});
}


// 修改显示班级类型时下拉框选中
function updateClassStyleSelected() {
	var classStyleId = $("#classstyleId").val();
	// 绑定classStyle选中状态
	$("#classstyle").val(classStyleId);
	//$("#classStyleId").attr("selected", "selected");
	/*if (classStyleId == 00) {
		$("#00").attr("selected", "selected");
	} else {
		if (classStyleId == 10) {
			$("#10").attr("selected", "selected");                                   
		} else {
			if (classStyleId == 20) {
				$("#20").attr("selected", "selected");
			} else {
				if (classStyleId == 30) {
					$("#30").attr("selected", "selected");
				}
			}
		}
	}*/
}

// 下拉框查询
function autocompleteTeat() {
	var dt = $(".dropdown-toggle");
	dt.on("click", function() {
		var s = $(this);
		$.ajax({
			async : false,
			dataType : "JSON",
			url : 'classManage/queryClassTeacher', // wherever your data is
			data : {
				"schoolId" : schoolId,
				"mainTeacherId" : $("#selectMain").val(),
				"withTeacherId" : $("#selectWith").val(),
				"nurserygovernessId" : $("#selectNurserygoverness").val()
			},
			success : function(data) {
				var ss = $(s.parent().siblings("select").find(":selected"))[0];
				s.parent().siblings("select").find("option").remove();
				for ( var d in data) {
					var item = data[d];
					// Create the DOM option that is pre-selected by default
					var option = new Option(item.name, item.userId);
					// Append it to the select
					s.parent().siblings("select").append(option);
				}
				console.log("如果此时多人操作同一个老师，会有BUG");
				s.parent().siblings("select").prepend(ss);
				s.parent().siblings("select").selectpicker('refresh');
			}
		});
	});
}

// 修改班级
function updateClassInfo() {
	$("#sureupdateclass").on("click", function() {
		var boolean = formValidation($("#updateclassform"));
		if(boolean){
			var className = $("#classname").val();
			var classStyle = $("#classstyle").val();
			var classId = $("#classid").val();
			layer.confirm("确认修改吗？", {icon : 3,title : '提示'}, function(){
				//修改时班级名称查重
				$.ajax({
					type:"POST",
					url:"classManage/queryUpdateRepeatClassName",
					data:{
						"classId":classId,
						"className":className,
						"schoolId":schoolId
					},
				success:function(data){
					if(data==""){
						$.ajax({
							type : "POST",
							url : "classManage/updateClassInfo",
							data : {
								"classId" : classId,
								"className" : className,
								"classStyle" : classStyle,
							},
							success : function(data) {
								layer.closeAll();
								layer.msg("修改成功", {
									icon : 1
								});
								$('#classmanage').DataTable().ajax.reload();
							},
							error : function(data) {
								alert("修改班级信息失败！");
							}
						});
					}else{
					    layer.msg("班级名称重复！", {icon : 2});
					}
				},
				error:function(data){
					alert("修改时查重失败！");
				}	
				});
				
			});
		}
	});
}

//实时修改主班老师
function updateSureMainTeacher(){
	//获取被修改老师的useId
	var oldMainTeacherId=$("#selectmainteacherId").val();
	//获取新老师的userId
	var newMainTeacherId=$("#selectMain").val();
	//获取班级classId
	var classId=$("#classid").val();
	layer.confirm("确认修改主班老师吗？", {icon : 3,title : '提示'}, function(){
		$.ajax({
			type:"POST",
			url:"classManage/UpdateClassMainTeacherInClass",
			data:{
				"newMainTeacherId" : newMainTeacherId,
				"organizationId" : schoolId,
				"classId" : classId,
				"type" : 1,
				"oldMainTeacherId" : oldMainTeacherId
			},
			success:function(data){
				layer.msg("修改主班老师成功", {icon : 1});
			},
			error:function(data){
				alert("修改主班老师失败");
			}
		});
	});
}

//实时修改配班老师
function updateSureWithTeacher(){
	//获取被修改老师的useId
	var oldMainTeacherId=$("#selectwithteacherId").val();
	//获取新老师的userId
	var newMainTeacherId=$("#selectWith").val();
	//获取班级classId
	var classId=$("#classid").val();
	layer.confirm("确认修改配班老师吗？", {icon : 3,title : '提示'}, function(){
		$.ajax({
			type:"POST",
			url:"classManage/UpdateClassMainTeacherInClass",
			data:{
				"newMainTeacherId" : newMainTeacherId,
				"organizationId" : schoolId,
				"classId" : classId,
				"type" : 2,
				"oldMainTeacherId" : oldMainTeacherId
			},
			success:function(data){
				layer.msg("修改配班老师成功", {icon : 1});
			},
			error:function(data){
				alert("修改配班老师失败");
			}
		});
	});
}


//实时修改保育员
function updateSureNurserygoverness(){
	//获取被修改老师的useId
	var oldMainTeacherId=$("#selectnurserygovernessId").val();
	//获取新老师的userId
	var newMainTeacherId=$("#selectNurserygoverness").val();
	//获取班级classId
	var classId=$("#classid").val();
	layer.confirm("确认修改配班老师吗？", {icon : 3,title : '提示'}, function(){
		$.ajax({
			type:"POST",
			url:"classManage/UpdateClassMainTeacherInClass",
			data:{
				"newMainTeacherId" : newMainTeacherId,
				"organizationId" : schoolId,
				"classId" : classId,
				"type" : 3,
				"oldMainTeacherId" : oldMainTeacherId
			},
			success:function(data){
				layer.msg("修改保育员成功", {icon : 1});
			},
			error:function(data){
				alert("修改保育员失败");
			}
		});
	});
}