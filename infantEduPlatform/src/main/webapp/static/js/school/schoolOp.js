/**
 * 学校操作js
 * Created by Pooh on 2015/12/7.
 */
var schoolId = $("#schoolId").val();
var schoolSetting = {
    async: {
        autoParam: ["id=id"],
        contentType: "application/x-www-form-urlencoded",
        enable: true,
        type: "post",
        url: 'bureau/queryBureauList',
        otherParam:{"oid":"1"}
    },
    callback: {
        onClick: bureauOpTreeOnClick
    }
};
$(function () {
    $('input').iCheck({
        labelHover: false,
        cursor: true,
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%'
    });
    if (schoolId != "") {
        querySchoolForUpdate();
    }
    loadBureauTree();
});

$("#schoolSave").on("click", function () {
    schoolSave();
});
function loadBureauTree(){
	$.ajax({
		  async : true,
		  type : "POST",
		  url : 'bureau/queryBureauList',
		  data:{"oid":-1},
		  dataType:"JSON",
		  success : function(data){
			  $.fn.zTree.init($("#parentBureauTree"), schoolSetting,data);
		  }
	});
}
function showBureauTree() {
    $("#bureauContent").css({left: "15px", top: "33px", "z-index": "9999"}).slideDown("fast");
    $("body").on("mousedown", onBodyDown);
}
function hideMenu() {
    $("#bureauContent").fadeOut("fast");
    $("body").off("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menusContent" || $(event.target).parents("#bureauContent").length > 0)) {
        hideMenu();
    }
}
$("#bureauName").on("click", function () {
    showBureauTree();
});
function bureauOpTreeOnClick(event, treeId, treeNode) {
    $("input[name=bureauId]").val(treeNode.id);
    $("input[name=bureauName]").val(treeNode.name);
}


function querySchoolForUpdate() {
    $.ajax({
        async: true,
        type: "POST",
        url: "school/querySchool",
        data: {"schoolId": schoolId},
        dataType: "JSON",
        success: function (data) {
            for (var element in data) {
                $("input[name=" + element + "][type='text']").val(data[element]);
            }
            $("input[name=bureauId]").val(data.bureauId);
            $(":radio[name='schoolStyle'][value='" + data.schoolStyle + "']").iCheck('check');
            schoolValid();
        }
    });
}

function schoolSave(){
    var flag = schoolValid();
    var RefreahBureauId=$("input[name=bureauId]").val();
    if(flag){
        $.ajax({
            async: true,
            type: "POST",
            url: "school/saveSchool",
            data: $("#schoolForm").serialize(),
            dataType: "JSON",
            success: function () {
                layer.msg('保存成功', {
                    icon: 6,
                    time:2000
                },function(){
                    layer.closeAll();
                });
                bureau.queryBureauInfo(RefreahBureauId);
                bureau.querySchoolByBureauId(RefreahBureauId); 
            }
        });
    }
}
/**
 * 表单验证
 * @returns {true or false} 表单验证结果
 */
function schoolValid(){
    $( "#schoolForm" ).validate({
        rules: {
            schoolName: {
                remote: {
                    url: "school/querySchoolByNameAndBureauId",
                    type: "post",
                    data: {
                        schoolName: function() {
                            return $( "#schoolName" ).val();
                        },
                        bureauId: function(){
                            return $("input[name=bureauId]").val();
                        },
                        schoolId: function(){
                            return  $("#schoolId").val();
                        }
                    }
                }
            },
            schoolCode:{
                remote:{
                    url:"school/querySchoolByNameAndBureauId",
                    type:"post",
                    data:{
                        schoolId:function(){
                            return $("#schoolId").val();
                        }
                    }
                }
            }
        },
        errorPlacement : function(error, element) {
            layer.tips($(error).text(), element);
        }
    });
    return formValidation($("#schoolForm"));
}
