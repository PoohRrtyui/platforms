/**
 * Created by Administrator on 2015/12/1.
 *
 */
var orgTypeDic = {"0": "普通单位", "1": "主管单位"};
var bureauTree;
var s = $("#bureau-box");
var o = $("#bureauOpBtn");
var bureau = {
    init: function () {
        bureau.showBureauTree();
        bureau.bureauAdd();
        bureau.schoolAdd();
        s.hide();
        bureau.bureauUpdate();
        bureau.bureauDelete();
    },
    //显示栏目树一级栏目
    showBureauTree: function () {
    	$.ajax({
			  async : true,
			  type : "POST",
			  url : 'bureau/queryBureauList',
			  data:{"oid":-1},
			  dataType:"JSON",
			  success : function(data){
				  bureauTree = $.fn.zTree.init($("#bureauTree"),bureauSetting,data);
			  }
		});
    },

    /**
     * 查询
     * @param bureauIds
     */
    queryBureauInfo: function (bureauIds) {
        $.ajax({
            async: true,
            type: "POST",
            url: "bureau/queryBureauInfo",
            data: {"bureauId": bureauIds},
            dataType: "JSON",
            success: function (data) {
                s.show();
                o.show();
                var boxdd = s.find("dd");
                boxdd[0].innerText = data.bureauName;
                boxdd[1].innerText = data.organizationCode;
                boxdd[2].innerText = orgTypeDic[data.orgType];
                boxdd[3].innerText = data.parentBureauId;
                boxdd[4].innerText = data.parentBureauName;
                boxdd[5].innerText = data.dutyPerson;
                $("#bureauId").val(data.organizationId);
                bureau.bureauUser();
            }
        });
    },
    /**
     * 查询显示单位下面的学校
     * 以及班级管理 部门管理 用户管理
     * @param bureauIds
     */
    querySchoolByBureauId: function (bureauIds) {
        $.ajax({
            async: true,
            type: "POST",
            url: "school/querySchoolByBureauId",
            data: {"bureauId": bureauIds},
            dataType: "JSON",
            success: function (data) {
                var html;
                $("#schoolContainer").empty();
                for (var i in data) {
                    html = '<div class="col-xs-4">'
                        + '<div class="box box-solid box-default">'
                        + '<div class="box-header">'
                        + '<div>'
                        + '<h3 class="widget-user-username">' + data[i].schoolName + '</h3>'
                        + '<h5 class="widget-user-desc">负责人：' + data[i].dutyPerson + '</h5>'
                        + '<div class="btn-group pull-right">'
                        + '<button type="button" class="btn bg-olive" value="' + data[i].schoolId
                        + '" name="updateSchool">修改学校</button>'
                        + '<button type="button" class="btn bg-olive" value="' + data[i].schoolId
                        + '" name="deleteSchool">删除学校</button>' + '</div>' + '</div>' + '</div>'
                        + '<div class="box-body">'
                        + '<div class="row">'
                        + '<div class="col-sm-4 border-right">'
                        + '<div class="description-block">'
                        + '<span class="description-text">'
                        + '<a href="page/classmanage?schoolId=' + data[i].schoolId + '"class="small-box-footer">'
                        + '班级管理 <i class="fa fa-arrow-circle-right"></i>'
                        + '</a></span>' + '</div>' + '</div>'
                        + '<div class="col-sm-4 border-right">'
                        + '<div class="description-block">'
                        + '<span class="description-text">'
                        + '<a href="user/index?type=' + 'S&schoolId=' + data[i].schoolId + '" class="small-box-footer">'
                        + '用户管理<i class="fa fa-arrow-circle-right"></i>'
                        + '</a></span>' + '</div>' + '</div>'
                        + '<div class="col-sm-4">'
                        + '<div class="description-block">'
                        + '<span class="description-text">'
                        + '<a href="departmentInfo/show?schoolId='+data[i].schoolId+'" class="small-box-footer">'
                        + '部门管理 <i class="fa fa-arrow-circle-right"></i>'
                        + '</a></span>' + '</div>' + '</div>' + ' </div>' + '</div>';
                    $("#schoolContainer").append(html);
                }
                $("button[name=updateSchool]").on("click", function () {
                    bureau.schoolUpdate($(this).val());
                });
                $("button[name=deleteSchool]").on("click", function () {
                    bureau.schoolDelete($(this).val());
                });
            }
        });
    },
    bureauAdd: function () {
        $("#bureauAdd").on("click", function () {
            $.post('page/bureauOp', {}, function (str) {
                layer.open({
                    type: 1,
                    title: "添加单位",
                    area: ['900px', '500px'],
                    content: str //注意，如果str是object，那么需要字符拼接。
                });
            });
        });
    },

    bureauUpdate: function () {
        $("#bureauUpdate").on("click", function () {
            var bureauInfoId = $("#bureauInfoId").val();
            $.post('page/bureauOp', {"bureauId": bureauInfoId}, function (str) {
                layer.open({
                    type: 1,
                    title: "修改单位信息",
                    area: ['900px', '500px'],
                    content: str //注意，如果str是object，那么需要字符拼接。
                });
            });
        });
    },
    bureauDelete: function () {
        $("#bureauDel").on("click", function () {
            var bureauId = $("#bureauInfoId").val();
            var parentBureauId = bureauTree.getSelectedNodes()[0].pId;
            layer.confirm('确认删除' + bureauTree.getSelectedNodes()[0].name + ' 吗？', function (index) {
                layer.close(index);
               //查询该单位下是否有子单位
                $.ajax({
                	dataType:"JSON",
                	type:"POST",
                	url:"bureau/queryChildBureauId",
                	data:{"bureauId":bureauId},
                	success:function(data) {
                	    if(data[0].isChild==0) {
                	    	//查询该单位下是否有学校
                	    	$.ajax({
                	    		type:"POST",
                	    		dataType:"JSON",
                	    		url:"school/queryChildSchool",
                	    		data:{"bureauId":bureauId},
                	    		success:function(data){
                	    			//此单位下没有学校
                	    			if(data.length==0) {
                           	    	 $.ajax({
                                            async: true,
                                            type: "POST",
                                            url: "bureau/deleteBureauInfo",
                                            data: {"bureauId": bureauId, "parentBureauId": parentBureauId},
                                            success: function () {
                                                layer.msg('删除成功！', {
                                                    icon: 6,
                                                    time: 2000//2秒关闭（如果不配置，默认是3秒）
                                                }, function () {
                                                    layer.closeAll();
                                                    var refreshPNode = bureauTree.getNodeByParam("id", parentBureauId);
                                                    bureauTree.reAsyncChildNodes(refreshPNode, "refresh", true);
                                                });
                                                window.location.reload(); 
                                            }
                                        });
                	    			}
                	    			else{
                	    				layer.msg("此单位下有学校，无法删除！", {icon : 2});
                	    			}
                	    		},
                	    		error:function(data) {
                	    			alert("查询该单位下的学校失败！");
                	    		}
                	    	});
                	    }else{
                	    	layer.msg("此单位下有子单位，无法删除！", {icon : 2});
                	    }
                	},
                	error:function(data) {
                		alert("查询该单位下的子单位失败！");
                	}
                });
            });
        });
    },
    bureauUser:function(){
    	$("#bureauUser").attr("href",'user/index?type=B&schoolId='+$("#bureauInfoId").val());
    	
    },
    schoolAdd: function () {
        $("#schoolAdd").on("click", function () {
            $.post('page/schoolOp', {}, function (str) {
                layer.open({
                    type: 1,
                    title: "添加学校",
                    area: ['900px', '500px'],
                    content: str //注意，如果str是object，那么需要字符拼接。
                });
            });
        });
    },
    schoolUpdate: function (schoolId) {
        $.post('page/schoolOp', {"schoolId": schoolId}, function (str) {
            layer.open({
                type: 1,
                title: "修改学校",
                area: ['900px', '500px'],
                content: str
            });
        });
    },
    schoolDelete: function (schoolId) {
        layer.confirm('确认删除吗？', function (index) {
            layer.close(index);
            $.ajax({
                async: true,
                type: "POST",
                url: "school/deleteSchool",
                data: {"schoolId": schoolId},
                success: function () {
                    layer.msg('删除成功！', {
                        icon: 6,
                        time: 2000//2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        layer.closeAll();
                    });
                   //var bureauId = bureauTree.getSelectedNodes()[0].id;
                   bureau.queryBureauInfo( $("#bureauInfoId").val());
                   bureau.querySchoolByBureauId( $("#bureauInfoId").val());  
                }
            });
        });
    }

};
var bureauSetting = {
    async: {
        autoParam: ["id=id"],
        contentType: "application/x-www-form-urlencoded",
        enable: true,
        type: "post",
        url: 'bureau/queryBureauList',
        otherParam:{"oid":"1"}
    },
    callback: {
        onClick: bureauTreeOnClick
    }
};

function bureauTreeOnClick(event, treeId, treeNode) {
    var bureauId = bureauTree.getSelectedNodes()[0].id;
    $("#bureauInfoId").val(bureauId);
    bureau.queryBureauInfo(bureauId);
    bureau.querySchoolByBureauId(bureauId);
}
