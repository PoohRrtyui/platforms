/**
 * Created by Administrator on 2015/12/3.
 * 单位操作页面js
 */
var bureauId = $("#bureauId").val();
var bureauOpSetting = {
    async: {
        autoParam: ["id=id"],
        contentType: "application/x-www-form-urlencoded",
        enable: true,
        type: "post",
        url: 'bureau/queryBureauList'
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
    if (bureauId != "") {
        queryBureauForUpdate();
    }
    loadBureauTree();
});

$("#bureauSave").on("click", function () {
    bureauSave();
});
function queryBureauForUpdate() {
    $.ajax({
        async: true,
        type: "POST",
        url: "bureau/queryBureauInfo",
        data: {"bureauId": bureauId},
        dataType: "JSON",
        success: function (data) {
            for (var element in data) {
                $("input[name=" + element + "][type='text']").val(data[element]);
            }
            $(":radio[name='orgType'][value='" + data.orgType + "']").iCheck('check');
        }
    });
}
function bureauSave() {
    $.ajax({
        async: true,
        type: "POST",
        url: "bureau/saveBureauInfo",
        data: $("#bureauForm").serialize(),
        dataType: "JSON",
        success: function () {
            layer.msg('保存成功', {icon: 6});
        }
    });
}

//显示栏目树一级栏目
function loadBureauTree() {
    $.fn.zTree.init($("#parentBureauTree"), bureauOpSetting);
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
$("#parentBureau").on("click", function () {
    showBureauTree();
});

function bureauOpTreeOnClick(event, treeId, treeNode) {
    $("input[name=parentBureauId]").val(treeNode.id);
    $("input[name=parentBureauName]").val(treeNode.name);
}
