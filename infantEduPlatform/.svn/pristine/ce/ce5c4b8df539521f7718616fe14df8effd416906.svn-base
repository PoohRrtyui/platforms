/*
 * author:lpp
 * 数据字典
 */

var table;
$("#codeMasterForm").validate({
    rules: {
    largeCategoryCd: {
            remote: {
                url: "bureau/queryCoderMasterRepeat",
                type: "POST",
                data: {
                    largeCategoryCd: function () {
                        return $("#largeCategoryCd").val();
                    },
                    largeCategoryName: function () {
                        return $("#largeCategoryName").val();
                    },
                    middleCategoryCd: function () {
                        return $("#middleCategoryCd").val();
                    },
                    middleCategoryName: function () {
                        return $("#middleCategoryName").val();
                    },
                    smallCategoryCd: function () {
                        return $("#smallCategoryCd").val();
                    },
                    smallCategoryName: function () {
                        return $("#smallCategoryName").val();
                    },
                }
            }
        }/*,
        largeCategoryName: {
            remote: {
                url: "codeMaster/queryCoderMasterRepeat",
                type: "post",
                data: {
                	largeCategoryName: function () {
                        return $("#largeCategoryName").val();
                    },
                    organizationId: function () {
                        return $("#bureauId").val();
                    }
                }
            }
        }*/
    },
    errorPlacement: function (error, element) {
        layer.tips($(error).text(), element);
    }
});

var codeMaster = {
    init: function () {
        codeMaster.message();
        codeMaster.addCodeMaster();
        codeMaster.insertCodeMaster();
        codeMaster.updateCodeMaster();
    },
    // dataTables数据表查询
    message: function () {
        var tpl = $("#tpl").html();
        // 预编译模板
        var template = Handlebars.compile(tpl);
        $('#example1')
            .DataTable(
                {
                    // 服务器数据加载
                    serverSide: true,
                    // 搜索框
                    searching: true,
                    // 分页框
                    lengthChange: false,
                    // 分页数（数组）
                    lengthMenu: [10],
                    //列头排序
                    ordering: false,
                    ajax: {
                        type: 'post',
                        url: 'codeMaster/queryCodeMasterInfo'
                    },
                    columns: [{
                        "data": "orderNo"
                    }, {
                        "data": "largeCategoryCd"
                    }, {
                        "data": "largeCategoryName"
                    }, {
                        "data": "middleCategoryCd"
                    }, {
                        "data": "middleCategoryName"
                    }, {
                        "data": "smallCategoryCd"
                    }, {
                        "data": "smallCategoryName"
                    }, {
                        "data": function (data) {
                            if (0 == data.systemFlg) {
                                return "非系统字段";
                            } else {
                                return "系统字段";
                            }
                        }
                    }, {
                        "data": "description"
                    }, {
                        "data": null
                    }],
                    columnDefs: [{
                        targets: 9,
                        render: function (data) {
                            var context = {
                                func: [
                                    {
                                        "name": "修改",
                                        "fn": "codeMaster.edit(\'"
                                        + data.largeCategoryCd
                                        + "\',\'"
                                        + data.largeCategoryName
                                        + "\',\'"
                                        + data.middleCategoryCd
                                        + "\',\'"
                                        + data.middleCategoryName
                                        + "\',\'"
                                        + data.smallCategoryCd
                                        + "\',\'"
                                        + data.smallCategoryName
                                        + "\',\'"
                                        + data.systemFlg
                                        + "\',\'"
                                        + data.orderNo
                                        + "\',\'"
                                        + data.description
                                        + "\')",
                                        "type": "primary"
                                    },
                                    {
                                        "name": "删除",
                                        "fn": "codeMaster.del(\'"
                                        + data.smallCategoryCd
                                        + "\',\'"
                                        + data.systemFlg
                                        + "\')",
                                        "type": "danger"
                                    }]
                            };
                            return template(context);
                        }
                    }

                    ],
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
                    "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>"
                    + "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>"

                });
    },

    // 修改跳转页面
    edit: function (largeCategoryCd, largeCategoryName, middleCategoryCd,
                    middleCategoryName, smallCategoryCd, smallCategoryName, systemFlg,
                    orderNo, description) {
        layer.open({
            type: 1,
            shade: [0.4, '#000'],
            title: "修改数据字典",
            skin: 'layui-layer-rim',
            area: ['600px', '600px'],
            content: '<div class="col-sm-12" id="settings">'
                + '<form id="updateForm" class="form-horizontal">'
                + '<input type="hidden" id="enabled1" name="enabled" value="'
                + systemFlg
                + '">'
               + '<div class="form-group">'
                       + '<label for="largeCategoryCd" class="col-sm-3 control-label">大分类编号:</label>'
                       + '<div class="col-sm-6">'
                           + '<input type="text" class="form-control required" id="largeCategoryCd"'
                                  + 'name="largeCategoryCd" placeholder="大分类编号" rangelength="1 30" readonly = "readonly"'
                                  + 'value="'
                                  + largeCategoryCd
                                  + '">'
                 + '</div>'
                + '</div>'
               + '<div class="form-group">'
                       + '<label for="largeCategoryName" class="col-sm-3 control-label">大分类名称：</label>'
                       + '<div class="col-sm-6">'
                          + '<input type="text" class="form-control required" id="largeCategoryName"'
                                  + 'name="largeCategoryName" placeholder="大分类名称" rangelength="1 30"'
                                  + 'value="'
                                  + largeCategoryName
                                  + '">'
                       + '</div>'
                + '</div>'
               + '<div class="form-group">'
                       + '<label for="middleCategoryCd" class="col-sm-3 control-label">中分类编号:</label>'
                       + '<div class="col-sm-6">'
                           + '<input type="text" class="form-control" id="middleCategoryCd"'
                                  + 'name="middleCategoryCd" placeholder="中分类编号" rangelength="0 30" readonly = "readonly"'
                                  + 'value="'
                                  + middleCategoryCd
                                  + '">'
                       + '</div>'
                + '</div>'
               + '<div class="form-group">'
                       + '<label for="middleCategoryName" class="col-sm-3 control-label">中分类名称：</label>'
                       + '<div class="col-sm-6">'
                           + '<input type="text" class="form-control" id="middleCategoryName"'
                                  + 'name="middleCategoryName" placeholder="中分类名称" rangelength="0 30"'
                                  + 'value="'
                                  + middleCategoryName
                                  + '">'
                       + '</div>'
                + '</div>'
               + '<div class="form-group">'
                       + '<label for="smallCategoryCd" class="col-sm-3 control-label">小分类编号：</label>'
                       + '<div class="col-sm-6">'
                           + '<input type="text" class="form-control required" id="smallCategoryCd"'
                                  + 'name="smallCategoryCd" placeholder="小分类编号" rangelength="1 30" readonly = "readonly"'
                                  + 'value="'
                                 + smallCategoryCd
                                  + '">'
                       + '</div>'
                + '</div>'
               + '<div class="form-group">'
                       + '<label for="smallCategoryName" class="col-sm-3 control-label">小分类名称：</label>'
                       + '<div class="col-sm-6">'
                           + '<input type="text" class="form-control required" id="smallCategoryName"'
                                  + 'name="smallCategoryName" placeholder="小分类名称" rangelength="1 30"'
                                  + 'value="'
                                  + smallCategoryName
                                  + '">'
                       + '</div>'
                + '</div>'
               + '<div class="form-group">'
               + '<label for="input-1" class="col-sm-3 control-label">系统字段：</label>'
               + '<div class="col-sm-6">'
                   + '<div class="iradio_square-blue">'
                       + '<input class="required" type="radio" id="true1" name="systemFlg" value="1">'
                   + '</div>'
                   + '<label for="true1" class="">启用</label>'
                   + '<div class="iradio_square-blue">'
                       + '<input class="required" type="radio" id="false1" name="systemFlg" value="0">'
                   + '</div>'
                   + '<label for="false1" class="">禁用</label>'
               + '</div>'
               + '</div>'
               + '<div class="form-group">'
                       + '<label for="orderNo" class="col-sm-3 control-label">排       序：</label>'
                       + '<div class="col-sm-6">'
                           + '<input type="text" class="form-control required shuzi" id="orderNo"'
                                  + 'name="orderNo" placeholder="排序号" value="'
                                  + orderNo
                                  + '">'
                       + '</div>'
               + '</div>'
              + '<div class="form-group">'
                       + '<label for="description" class="col-sm-3 control-label">描       述：</label>'
                       + '<div class="col-sm-6">'
                           + '<textarea class="form-control required" id="description" name="description"'
                                  + 'style="resize:none;"  placeholder="简介"/></textarea>'
                       + '</div>'
               + '</div>'
               + '<div class="form-group">'
               + '<div class="col-sm-offset-5 col-sm-7">'
                   + '<button type="button" id="updateMassage" class="btn btn-danger">确定</button>'
               + '</div>'
              + '</div>'

        });
        $("#description").val(description);
        
        var value = $("#enabled1").val();
        if (value == 1) {
            $("#true1").attr("checked", true);
        } else {
            $("#false1").attr("checked", true);
        }
        var middleCategoryCd = $("#middleCategoryCd").val();
        var middleCategoryName = $("#middleCategoryName").val();
        var description = $("#description").val();
        if (middleCategoryCd == "null") {
            $("#middleCategoryCd").attr("value", "");
        }
        if (middleCategoryName == "null") {
            $("#middleCategoryName").attr("value", "");
        }
        if (description == "null") {
            $("#description").attr("value", "");
        }
        $('input').iCheck({
            labelHover: false,
            cursor: true,
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%'
        });
    },

    /* 删除功能 */
    del: function (smallCategoryCd, systemFlg) {
        if (systemFlg == 0) {
            layer.confirm("确认删除吗？", {
                icon: 3,
                title: '提示'
            }, function () {
                $.ajax({
                    url: "codeMaster/deleteCodeMaster",
                    type: "POST",
                    data: {
                        "smallCategoryCd": smallCategoryCd
                    },
                    success: function (data) {
                        layer.closeAll();
                        layer.msg("删除成功!", {
                            icon: 1
                        });
                        $('#example1').DataTable().ajax.reload();
                    },
                    error: function (data) {
                        layer.msg("操作失败!", {
                            icon: 1
                        });
                    }
                });
            });

        } else {
            layer.msg("请勿删除系统字段的数据!", {
                icon: 4
            });
        }
    },

    /* 点击增加跳转页面 */
    addCodeMaster: function () {
        $("#addCodeMasterInfo").on("click", function () {
            layer.open({
                type: 1,
                title: "增加数据字典信息",
                skin: 'layui-layer-rim',
                area: ['600px', '600px'],
                content: '<div class="col-sm-12" id="settings">'
                    + '<form id="codeMasterForm" class="form-horizontal">'
                    + '<div class="form-group">'
                            + '<label for="largeCategoryCd" class="col-sm-3 control-label">大分类编号:</label>'
                            + '<div class="col-sm-6">'
                                + '<input type="text" class="form-control required" id="largeCategoryCd"'
                                       + 'name="largeCategoryCd" placeholder="大分类编号" rangelength="1 30">'
                      + '</div>'
                     + '</div>'
                    + '<div class="form-group">'
                            + '<label for="largeCategoryName" class="col-sm-3 control-label">大分类名称：</label>'
                            + '<div class="col-sm-6">'
                               + '<input type="text" class="form-control required" id="largeCategoryName"'
                                       + 'name="largeCategoryName" placeholder="大分类名称" rangelength="1 30">'
                            + '</div>'
                     + '</div>'
                    + '<div class="form-group">'
                            + '<label for="middleCategoryCd" class="col-sm-3 control-label">中分类编号:</label>'
                            + '<div class="col-sm-6">'
                                + '<input type="text" class="form-control" id="middleCategoryCd"'
                                       + 'name="middleCategoryCd" placeholder="中分类编号" rangelength="0 30">'
                            + '</div>'
                     + '</div>'
                    + '<div class="form-group">'
                            + '<label for="middleCategoryName" class="col-sm-3 control-label">中分类名称：</label>'
                            + '<div class="col-sm-6">'
                                + '<input type="text" class="form-control" id="middleCategoryName"'
                                       + 'name="middleCategoryName" placeholder="中分类名称" rangelength="0 30">'
                            + '</div>'
                     + '</div>'
                    + '<div class="form-group">'
                            + '<label for="smallCategoryCd" class="col-sm-3 control-label">小分类编号：</label>'
                            + '<div class="col-sm-6">'
                                + '<input type="text" class="form-control required" id="smallCategoryCd"'
                                       + 'name="smallCategoryCd" placeholder="小分类编号" rangelength="1 30">'
                            + '</div>'
                     + '</div>'
                    + '<div class="form-group">'
                            + '<label for="smallCategoryName" class="col-sm-3 control-label">小分类名称：</label>'
                            + '<div class="col-sm-6">'
                                + '<input type="text" class="form-control required" id="smallCategoryName"'
                                       + 'name="smallCategoryName" placeholder="小分类名称" rangelength="1 30">'
                            + '</div>'
                     + '</div>'
                    + '<div class="form-group">'
                    + '<label for="input-1" class="col-sm-3 control-label">系统字段：</label>'
                    + '<div class="col-sm-6">'
                        + '<div class="iradio_square-blue">'
                            + '<input class="required" type="radio" id="true" name="systemFlg" value="1">'
                        + '</div>'
                        + '<label for="true1" class="">启用</label>'
                        + '<div class="iradio_square-blue">'
                            + '<input class="required" type="radio" id="false" name="systemFlg" value="0">'
                        + '</div>'
                        + '<label for="false1" class="">禁用</label>'
                    + '</div>'
                    + '</div>'
                    + '<div class="form-group">'
                            + '<label for="orderNo" class="col-sm-3 control-label">排       序：</label>'
                            + '<div class="col-sm-6">'
                                + '<input type="text" class="form-control required shuzi" id="orderNo"'
                                       + 'name="orderNo" placeholder="排序号">'
                            + '</div>'
                    + '</div>'
                   + '<div class="form-group">'
                            + '<label for="description" class="col-sm-3 control-label">描       述：</label>'
                            + '<div class="col-sm-6">'
                                + '<textarea class="form-control required" id="description" name="description"'
                                       + 'style="resize:none;"  placeholder="简介"/></textarea>'
                            + '</div>'
                    + '</div>'
                    + '<div class="form-group">'
                    + '<div class="col-sm-offset-5 col-sm-7">'
                        + '<button type="button" id="add" class="btn btn-danger">确定</button>'
                    + '</div>'
                   + '</div>',
            });
            $('input').iCheck({
                labelHover: false,
                cursor: true,
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%'
            });
        });
    },

    /* 插入数据 */
    insertCodeMaster: function () {
        $(document).on("click", "#add", function () {
            var largeCategoryCd = $("#largeCategoryCd").val();/*大分类编号*/
            var largeCategoryName = $("#largeCategoryName").val();/*大分类名称*/
            var middleCategoryCd = $("#middleCategoryCd").val();/*中分类编号*/
            var middleCategoryName = $("#middleCategoryName").val();/*中分类名称*/
            var smallCategoryCd = $("#smallCategoryCd").val();/*小分类编号*/
            var smallCategoryName = $("#smallCategoryName").val();/*小分类名称*/
            var enabled = 0;
            if ($("#true").is(":checked") == true) {
                enabled = 1;
            } else if ($("#false").is(":checked") == true) {
                enabled = 0;
            }
            var orderNo = $("#orderNo").val();
            var description = $("#description").val();
            var boolean = formValidation($("#codeMasterForm"));
            if (boolean) {
            	$.ajax({
                    type: "POST",
                    url: 'codeMaster/saveCodeMaster',
                    data: {
                        "largeCategoryCd": largeCategoryCd,
                        "largeCategoryName": largeCategoryName,
                        "middleCategoryCd": middleCategoryCd,
                        "middleCategoryName": middleCategoryName,
                        "smallCategoryCd": smallCategoryCd,
                        "smallCategoryName": smallCategoryName,
                        "systemFlg": enabled,
                        "orderNo": orderNo,
                        "description": description
                    },
                    success: function (data) {
                        layer.closeAll();
                        layer.msg("添加成功!", {
                            icon: 1
                        });
                        $('#example1').DataTable().ajax.reload();
                    },
                    error: function (data) {
                        alert("添加失败！");
                    }
                });
            /*
            //添加时编号名称查重
                //大分类编号名称查重            	
            	$.ajax({
            		type:"POST",
            		url:"codeMaster/queryLargeRepeat",
            		data:{
            			"largeCategoryCd":largeCategoryCd,
            			"largeCategoryName":largeCategoryName
            		},
            		success:function(data) {
            			
            		},
            		error:function(data) {
                                   			
            		}
            	});
            */}
        });
        $(document).on("click", "#cancelCodeMaster", function () {
            layer.closeAll();
        });
    },
    // 修改数据
    updateCodeMaster: function () {
        $(document).on("click", "#updateMassage", function () {
            var largeCategoryCd = $("#largeCategoryCd").val();
            var largeCategoryName = $("#largeCategoryName").val();
            var middleCategoryCd = $("#middleCategoryCd").val();
            var middleCategoryName = $("#middleCategoryName").val();
            var smallCategoryCd = $("#smallCategoryCd").val();
            var smallCategoryName = $("#smallCategoryName").val();
            var enabled = 0;
            if ($("#true1").is(":checked") == true) {
                enabled = 1;
            } else if ($("#false1").is(":checked") == true) {
                enabled = 0;
            }
            var orderNo = $("#orderNo").val();
            var description = $("#description").val();
            var boolean = formValidation($("#updateForm"));
            if (boolean) {
                $.ajax({
                    type: "POST",
                    url: 'codeMaster/updateCodeMaster',
                    data: {
                        "largeCategoryCd": largeCategoryCd,
                        "largeCategoryName": largeCategoryName,
                        "middleCategoryCd": middleCategoryCd,
                        "middleCategoryName": middleCategoryName,
                        "smallCategoryCd": smallCategoryCd,
                        "smallCategoryName": smallCategoryName,
                        "systemFlg": enabled,
                        "orderNo": orderNo,
                        "description": description
                    },
                    success: function (data) {
                        layer.closeAll();
                        layer.msg("修改成功!", {
                            icon: 1
                        });
                        $('#example1').DataTable().ajax.reload();
                    },
                    error: function (data) {
                        alert("修改失败！");
                    }
                });
            }
        });
        $(document).on("click", "#cancelUpdate", function () {
            layer.closeAll();
        });
    }

};
