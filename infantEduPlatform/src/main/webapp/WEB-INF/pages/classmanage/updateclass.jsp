<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改班级</title>
 <link rel="stylesheet"
	href="static/plugins/bootstrap-select/css/bootstrap-select.css">
	<link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">

</head>
<body>
<div class="col-sm-12" id="settings">
    <form class="form-horizontal" id="updateclassform">
      <input type="hidden" id="schoolId" value="${requestScope.schoolId}">
        <div class="form-group">
            <label for="classname" class="col-sm-3 control-label">班级名称</label>

            <div class="col-sm-8">
                  <input type="hidden" id="classid" value="${requestScope.classId}">
                <input id="classname" type="text"
                       class="form-control required chineseRemovebracket"
                       name="classname" placeholder="班级名称" rangelength="1 15" value="${requestScope.className}">
            </div>
        </div>
        <div class="form-group">
            <label for="classname" class="col-sm-3 control-label">班级类型</label>
            <input type="hidden" id="classstyleId" value="${requestScope.classStyle}">
            <div class="col-xs-8">
                <select id="classstyle" class="form-control">
                </select>
            </div>
        </div>
        <div class="form-group">
            <input type="hidden" id="selectmainteacherId" value="${requestScope.mainTeacherId}">
            <label for="selectMain" class="col-sm-3 control-label">主班</label>

            <div class="col-xs-8">
                <select class="selectpicker" data-live-search="true"
                         id="selectMain" title='${requestScope.mainTeacherName}' data-size="5"
                        onchange="updateSureMainTeacher(this.options[this.options.selectedIndex].value)"></select>
            </div>

        </div>

        <div class="form-group">
            <input type="hidden" id="selectwithteacherId" value="${requestScope.withTeacherId}">
            <label for="selectWith" class="col-sm-3 control-label">配班</label>

            <div class="col-xs-8">
                <select class="selectpicker " data-live-search="true"
                         id="selectWith" title='${requestScope.withTeacherName}' data-size="5"
                         onchange="updateSureWithTeacher(this.options[this.options.selectedIndex].value)">
                </select>
            </div>

        </div>
        <div class="form-group">
            <input type="hidden" id="selectnurserygovernessId" value="${requestScope.nurserygovernessId}">
            <label for="selectNurserygoverness" class="col-sm-3 control-label">保育员</label>

            <div class="col-sm-8">
                <select class="selectpicker " data-live-search="true"
                        title='${requestScope.nurserygovernessName}' id="selectNurserygoverness" data-size="5"
                        onchange="updateSureNurserygoverness(this.options[this.options.selectedIndex].value)">
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <input type="button" id="sureupdateclass"  value="确定"
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
	<script type="text/javascript" src="static/js/classmanage/updateclass.js"></script>
	<script  type="text/javascript">
	$(function() {
		$('input').iCheck({
            labelHover: false,
            cursor: true,
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%'
        });
		UpdateclassManage.init();
	});
	</script>
</body>
</html>