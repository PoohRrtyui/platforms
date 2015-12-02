/**
 * Created by Administrator on 2015/12/1.
 *
 */
var orgTypeDic = {"0":"普通单位","1":"主管单位"};
var bureauTree;
var bureau = {
    init: function () {
        bureau.showBureauTree();
        bureau.bureauAdd();
    },
    //显示栏目树一级栏目
    showBureauTree: function () {
         bureauTree =$.fn.zTree.init($("#bureauTree"), bureauSetting);
    },

    queryBureauInfo: function (bureauIds) {
        $.ajax({
            async:true,
            type:"POST",
            url:"bureau/queryBureauInfo",
            data:{"bureauId":bureauIds},
            dataType:"JSON",
            success:function(data){
                var boxdd= $("#bureau-box").find("dd");
                boxdd[0].innerText = data[0].bureauName;
                boxdd[1].innerText = data[0].organizationCode;
                boxdd[2].innerText = orgTypeDic[data[0].orgType];
                boxdd[3].innerText = data[0].parentBureauId;
                boxdd[4].innerText = data[0].parentBureauName;
            }
        });
    },
    /**
     * 查询显示单位下面的学校
     * 以及班级管理 部门管理 用户管理
     * @param bureauIds
     */
    querySchoolByBureauId:function(bureauIds){
        $.ajax({
            async:true,
            type:"POST",
            url:"school/querySchoolByBureauId",
            data:{"bureauId":bureauIds},
            dataType:"JSON",
            success:function(data){
                var html ;
                $("#schoolContainer").empty();
                for(var i in data){
                    html='<div class="col-xs-5">'
                        +'<div class="box box-solid box-primary">'
                        +'<div class="box-header">'
                        +'<div>'
                        +'<h3 class="widget-user-username">'+data[i].schoolName+'</h3>'
                        +'<h5 class="widget-user-desc">负责人：'+data[i].dutyPerson+'</h5>'
                        +'<div class="btn-group pull-right">'
                        +'<button type="button" class="btn bg-olive">修改学校</button>'
                        +'<button type="button" class="btn bg-olive">删除学校</button>'
                        +'</div>'
                        +'</div>'
                        +'</div>'
                        +'<div class="box-body">'
                        +'<div class="row">'
                        +'<div class="col-sm-4 border-right">'
                        +'<div class="description-block">'
                        +'<span class="description-text">'
                        +'<a href="#" class="small-box-footer">'
                        +'班级管理 <i class="fa fa-arrow-circle-right"></i>'
                        +'</a></span>'
                        +'</div>'
                        +'</div>'
                        +'<div class="col-sm-4 border-right">'
                        +'<div class="description-block">'
                        +'<span class="description-text">'
                        +'<a href="#" class="small-box-footer">'
                        +'用户管理 <i class="fa fa-arrow-circle-right"></i>'
                        +'</a></span>'
                        +'</div>'
                        +'</div>'
                        +'<div class="col-sm-4">'
                        +'<div class="description-block">'
                        +'<span class="description-text">'
                        +'<a href="#" class="small-box-footer">'
                        +'部门管理 <i class="fa fa-arrow-circle-right"></i>'
                        +'</a></span>'
                        +'</div>'
                        +'</div>'
                        +' </div>'
                        +'</div>';
                    $("#schoolContainer").append(html);
                }
            }
        });
    },
    bureauAdd:function(){
        $("#bureauAdd").on("click",function(){
            $.post('page/bureauOp', {}, function(str){
                layer.open({
                    type: 1,
                    area:['1000px','700px'],
                    content: str //注意，如果str是object，那么需要字符拼接。
                });
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
        url: 'bureau/queryBureauList'
    },
    callback: {
        onClick: bureauTreeOnClick
    }
};

function bureauTreeOnClick (event, treeId, treeNode) {
    var bureauId = bureauTree.getSelectedNodes()[0].id;
    bureau.queryBureauInfo(bureauId);
    bureau.querySchoolByBureauId(bureauId);
}
