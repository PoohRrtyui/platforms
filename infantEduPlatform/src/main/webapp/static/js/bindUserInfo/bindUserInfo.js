/**
 * 用户绑定身份信息js
 * Created by PoohDing on 2015/12/16.
 */

var userTypeDic = {"1":{"S":"教职工","B":"单位员工"},"4":"教师","8":"学生","16":"家长","0":"普通用户"};
$(function () {
    $('input').iCheck({
        labelHover: false,
        cursor: true,
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%'
    });
    showSelectByUserType();
    $('.selectpicker').selectpicker();
    $("#saveUserBoundInfo").on("click",function(){
        saveUserBoundInfo();
    });
    $("#delUserBound").on("click",function(){
        deleteUserBoundInfo();
    });

    locationCodeSelect();
    queryUserBoundInfo();

});

function hideDiv(){
    $("#organization").hide();
    $("#schoolOrg").hide();
    $("#code").hide();
    $("#relation").hide();
    $("#timeIntoOrg").hide();

}
function showSelectByUserType(){
    hideDiv();

    $("input[name=userType]").on("ifClicked",function(){
        var t;
        hideDiv();
        t = $(this).attr("id");
        if(t == "radio-1"){
            $($("#organization").show().children("div").get(1)).hide();
            $("#code").show();
        }else{
            $("#organization").show().children("div").show();
            if(t == "radio-2"){
                $($($("#schoolOrg").show().children("div").hide())[1]).show();
            }else if(t == "radio-3"){
                $($($("#schoolOrg").show().children("div").hide())[0]).show();
            }else if(t=="radio-4"){
                $($("#schoolOrg").show().children("div").show().get(0)).hide();
                $("#relation").show();
            }else{
                $($($("#schoolOrg").show().children("div").hide())[1]).show();
            }
        }
    });
}
function queryUserBoundInfo(){
    $.ajax({
        type:"POST",
        url:"bindUserInfo/queryUserBoundInfo",
        dataType:"JSON",
        success:function(data){
            if(data.bureau != null) {
                $("input[name = 'userId']").val(data.user.userid);
               /* $(":radio[name='userType'][value='" + data.user.userType + "']").iCheck('check');
                $("input[name = 'userCode']").val(data.user.userCode);
                $("input[name = 'relation']").val(data.user.relation);
                $("#isAuthentication").val(data.user.isAuthentication);
                $('input').iCheck('disable');*/
                $("#saveBoundInfo").hide();
                $("#identity").hide();
                var ut = data.user.usertype;
                var ot = data.user.organizationtype;
                if(ut == 1){
                    ut = userTypeDic[ut][ot];
                }else{
                    ut = userTypeDic[ut];
                }
                var idInfo = " "+data.bureau.bureauName;
                if(data.school != null){
                    idInfo+="->"+data.school.schoolName;
                }if(data.dept != null){
                    idInfo+="->"+data.dept.departmentName;
                }if(data.classes != null){
                    idInfo+="->"+data.classes.className;
                }
                $("#idInfo").text(idInfo+" "+ut);
            }else{
                $("#identityInfo").hide();
            }
        }
    });
}

function saveUserBoundInfo(){
    if ('' == $("input[name = startTime]").val()) {
     $("input[name = startTime]").val(new Date());
}
    $.ajax({
        type:"POST",
        url:"bindUserInfo/bind",
        data:$("#bindUserForm").serialize(),
        success:function(){
            layer.msg('保存成功！', {
                icon: 6,
                time: 2000//2秒关闭（如果不配置，默认是3秒）
            })
        },
        error:function(){
            layer.msg('保存失败！', {
                icon: 6,
                time: 2000//2秒关闭（如果不配置，默认是3秒）
            })
        }
    });
}

function deleteUserBoundInfo(){
    $.ajax({
       type:"POST",
        url:"bindUserInfo/deleteUserBoundInfo",
        data:{"userId":$("#userId").val(),"userType":$(":radio[name=userType]").val()},
        success:function(){
            layer.msg('删除成功！', {
                icon: 6,
                time: 2000//2秒关闭（如果不配置，默认是3秒）
            });
            $("#idInfo").text("");
            window.location.href=window.location.href;

        },
        error:function(){
            layer.msg('删除失败！', {
                icon: 6,
                time: 2000//2秒关闭（如果不配置，默认是3秒）
            })
        }
    });
}

/**
 * 所属机构下拉框数据获取
 */
function locationCodeSelect() {
    var userBoundInfo = $(".dropdown-toggle");
    userBoundInfo.on("click", function () {
        var s = $(this);
        if (s.attr("data-id") == "bureau") {
            getSelectData(s, "bureau/queryBureauList", "", "name", "id");
        } else if (s.attr("data-id") == "school") {
            getSelectData(s, "school/querySchoolByBureauId",
                {"bureauId": $("#bureau").val()}, "schoolName", "schoolId");
        } else if (s.attr("data-id") == "classes") {
            getSelectData(s, "classManage/queryClassBySchoolId",
                {"schoolId": $("#school").val()}, "className", "classId");
        } else if (s.attr("data-id") == "student") {
            getSelectData(s, "bindUserInfo/queryUsersInClass", {
                "classId": $("#classes").val(),
                "userType": "8"
            }, "name", "id");
        } else if (s.attr("data-id") == "department") {
            getSelectData(s, "departmentInfo/queryDeptBySchoolId",
                {"bureauId": $("#school").val()}, "departmentName", "departmentId");
        }
    });
}

/**
 * ajax获取下拉数据并且赋值到对应的Select
 * @author PoohDing
 * @param s 当前下拉框
 * @param url ajax的url
 * @param param ajax的传递参数
 * @param optionName 创建Select的Option的Text
 * @param optionId 创建Select的Option的value
 */

function getSelectData(s, url, param, optionName, optionId) {
    $.ajax({
        async: false,
        type: "POST",
        dataType: "JSON",
        url: url, // wherever your data is
        data: param,
        success: function (data) {
            var item;
            s.parent().siblings("select").find("option").remove();
            for (var d in data) {
                item = data[d];
                // Create the DOM option that is pre-selected by default
                var option = new Option(item[optionName], item[optionId]);
                // Append it to the select
                s.parent().siblings("select").append(option);
            }
            s.parent().siblings("select").selectpicker('refresh');
        }
    });
}
