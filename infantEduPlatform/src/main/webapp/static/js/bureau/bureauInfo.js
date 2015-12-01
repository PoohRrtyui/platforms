/**
 * Created by Administrator on 2015/12/1.
 *
 */
var orgTypeDic = {"0":"普通单位","1":"主管单位"};
var bureauTree;
var bureau = {
    init: function () {
        bureau.showBureauTree();
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
    querySchoolByBureauId:function(bureauIds){
        $.ajax({
            async:true,
            type:"POST",
            url:"school/querySchoolByBureauId",
            data:{"bureauId":bureauIds},
            dataType:"JSON",
            success:function(data){
                debugger;
            }
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
