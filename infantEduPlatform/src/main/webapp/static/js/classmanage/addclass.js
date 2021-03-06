var schoolId=$("#schoolId").val();



var AddclassManage = {
	path : '',
	init : function(url) {
		queryClassStyle();/*classStyle中数据从codeMaster表中取*/
		$('.selectpicker').selectpicker();
		autocompleteTeat();
		addClass();/*增加班级*/
	}
};

//classStyle中数据从codeMaster表中取
function queryClassStyle() {
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
        	/*for(var d in data){
                var item = data[d];
                // Create the DOM option that is pre-selected by default
                var option = new Option(item.smallCategoryName, item.smallCategoryCd);
                // Append it to the select
                $("#classstyle").append(option);
            }*/
        },
        error:function(data){
        	alert("数据字典表中取classStyle数据失败！");
        }
	});
}



//下拉框查询
function autocompleteTeat() {
	var dt = $(".dropdown-toggle");
	dt.on("click",function(){
		var s = $(this);
		$.ajax({
			async:false,
			dataType : "JSON",
			url : 'classManage/queryClassTeacher', // wherever your data is
			data : {
				"schoolId" : schoolId,
				"mainTeacherId" : $("#selectMain").val(),
				"withTeacherId" : $("#selectWith").val(),
				"nurserygovernessId" : $("#selectNurserygoverness").val()
			},
			success:function(data){
				var ss=$(s.parent().siblings("select").find(":selected"))[0];
				s.parent().siblings("select").find("option").remove();
				for (var d in data) {
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

 
//增加班级
function addClass() {
	$(document).on("click", "#sureaddclass", function() {
			var boolean = formValidation($("#addclassform"));
			if(boolean){
				var classStyle = $("#classstyle").val();
				var className = $("#classname").val();
				// 获取主班，配班，保育员三个文本框里的userId
				var mainTeacherId = $("#selectMain").val();
				var withTeacherId = $("#selectWith").val();
				var nurserygovernessId = $("#selectNurserygoverness").val();
				var classCode=$("#classcode").val();
				if(mainTeacherId==""||withTeacherId==""||nurserygovernessId==""){
					layer.msg("请选择老师！", {icon : 2});
				}else{
					layer.confirm("确认添加吗？", {icon : 3,title : '提示'}, function(){
						//班级名称查重
						$.ajax({
							type:"POST",
							url:"classManage/queryRepeatClassName",
							data:{
								"schoolId":schoolId,
								"className":className
							},
						success:function(data){
							if(data==""){
								//班级代码查重
								$.ajax({
									type:"POST",
									url:"classManage/queryRepeatClassCode",
									data:{
										"classCode":classCode
									},
									success:function(data){
										if(data==""){
											//添加班级
											$.ajax({
												type : "POST",
												url : "classManage/insertClass",
												data : {
													"schoolId" : schoolId,
													"classCode" : classCode,
													"classStyle" : classStyle,
													"className" : className,
													"SchoolingLength" : 3,
													"mainTeacherId" : mainTeacherId,
													"withTeacherId" : withTeacherId,
													"nurserygovernessId" : nurserygovernessId
												},
												success : function(data) {
													layer.closeAll();
													layer.msg("添加成功", {
														icon : 1
													});
													$('#classmanage').DataTable().ajax.reload();
												},
												error : function(data) {
													alert("添加失败");
												}
											});
										}else{
											layer.msg("班级代码重复！", {icon : 2});
										}
									},
									error:function(data){
										alert("班级代码查重失败！");
									}
								});
							}else{
								layer.msg("班级名称重复！", {icon : 2});
							}
						},
						error:function(data){
							alert("查重失败！");
							//查重失败
						}
						});
					});
				}
			}
	});
}
