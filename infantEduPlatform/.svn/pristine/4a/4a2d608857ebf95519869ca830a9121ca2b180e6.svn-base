<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加班级</title>
    <!-- Select2 -->
    <link rel="stylesheet"
          href="static/plugins/bootstrap-select/css/bootstrap-select.css">
    <link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">

</head>
<body>
<div class="col-sm-12" id="settings">
    <form class="form-horizontal" id="addclassform">
    <input type="hidden" id="schoolId" value="${requestScope.schoolId}">
        <div class="form-group">
            <label for="classname" class="col-sm-3 control-label">班级名称</label>

            <div class="col-sm-8">
                <input id="classname" type="text"
                       class="form-control required chineseRemovebracket"
                       name="classname" placeholder="班级名称" rangelength="1 15">
            </div>
        </div>
        <div class="form-group">
            <label for="classcode" class="col-sm-3 control-label">班级代码</label>

            <div class="col-sm-8">
                <input id="classcode" type="text"
                       class="form-control required character"
                       name="classcode" placeholder="班级代码" rangelength="1 15">
            </div>
        </div>
        <div class="form-group">
            <label for="classname" class="col-sm-3 control-label">班级类型</label>

            <div class="col-xs-8">
                <select id="classstyle" class="form-control">
                    <!-- <option value="00">幼儿园</option>
                    <option value="10">小学</option>
                    <option value="20">初中</option>
                    <option value="30">高中</option> -->.
                </select>
            </div>
        </div>
        <div class="form-group">
            <input type="hidden" id="selectmainteacherId">
            <label for="selectMain" class="col-sm-3 control-label">主班</label>

            <div class="col-xs-8">
                <select class="selectpicker" data-live-search="true"
                        title='请选择主班老师' id="selectMain" data-size="5"></select>
            </div>

        </div>

        <div class="form-group">
            <input type="hidden" id="selectwithteacherId">
            <label for="selectWith" class="col-sm-3 control-label">配班</label>

            <div class="col-xs-8">
                <select class="selectpicker " data-live-search="true"
                        title='请选择配班老师' id="selectWith" data-size="5">
                </select>
            </div>

        </div>
        <div class="form-group">
            <input type="hidden" id="selectnurserygovernessId">
            <label for="selectNurserygoverness" class="col-sm-3 control-label">保育员</label>

            <div class="col-sm-8">
                <select class="selectpicker " data-live-search="true"
                        title='请选择保育员' id="selectNurserygoverness" data-size="5">
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <input type="button" id="sureaddclass" value="确定"
                       class="btn btn-danger">
                <input type="reset" value="重置" class="btn btn-danger">
            </div>

        </div>
    </form>
</div>
<script type="text/javascript" src="static/js/common/base.js"></script>
<script src="static/plugins/validate/jquery.validate.js"></script>
<script src="static/plugins/validate/jquery.validate.extend.js"></script>
<script type="text/javascript"
        src="static/plugins/bootstrap-select/js/bootstrap-select.js">

</script>
<script src="static/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="static/js/classmanage/addclass.js"></script>
<script type="text/javascript">
    $(function () {
        $('input').iCheck({
            labelHover: false,
            cursor: true,
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%'
        });
        AddclassManage.init();
    });
</script>
</body>
</html>
