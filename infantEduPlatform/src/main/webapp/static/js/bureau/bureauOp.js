/**
 * Created by Administrator on 2015/12/3.
 * 单位操作页面js
 */
var bureauId = $("#bureauId").val();
$(function(){
    $('input').iCheck({
        labelHover : false,
        cursor : true,
        checkboxClass : 'icheckbox_square-blue',
        radioClass : 'iradio_square-blue',
        increaseArea : '20%'
    });
    if(bureauId!=""){
        queryBureauForUpdate();
    }
});

$("#bureauSave").on("click",function(){
    bureauSave();
});
function queryBureauForUpdate(){
    $.ajax({
        async:true,
        type:"POST",
        url:"bureau/queryBureauInfo",
        data:{"bureauId":bureauId},
        dataType:"JSON",
        success:function(data){
            for(var element in data){
                $("input[name="+element+"][type='text']").val(data[element]);
            }
            $(":radio[name='orgType'][value='"+data.orgType+"']").iCheck('check');
        }
    });
}
function bureauSave(){
    $.ajax({
        async:true,
        type:"POST",
        url:"bureau/saveBureauInfo",
        data:$("#bureauForm").serialize(),
        dataType:"JSON",
        success:function(){
            layer.msg('保存成功', {icon: 6});
        }
    });
}
