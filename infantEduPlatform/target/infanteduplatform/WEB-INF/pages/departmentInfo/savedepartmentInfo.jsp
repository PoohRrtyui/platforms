<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
        <link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
        <script src="static/plugins/iCheck/icheck.min.js"></script>
        <script src="static/plugins/validate/jquery.validate.js"></script>
        <script src="static/plugins/validate/jquery.validate.extend.js"></script>
</head>
<body>
        <div class="col-sm-8" id="settings" style="margin-top: 60px;margin-left: 150px;'">
            <form class="form-horizontal" id="bureauForm">              
                <div class="form-group">
                    <label for="nameAdd" class="col-sm-3 control-label">部门名:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control required chinesezimu" id="nameAdd"
                               name="nameAdd" placeholder="部门名" rangelength="1 15">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="orderNo" class="col-sm-3 control-label">排序号:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control required shuzi" id="orderNo"
                               name="orderNo" placeholder="排序号">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="personAdd" class="col-sm-3 control-label">负责人：</label>

                    <div class="col-sm-6">
                        <input type="text" class="form-control required chinese" id="personAdd"
                               name="personAdd" placeholder="负责人" rangelength="1 20">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="telephoneAdd" class="col-sm-3 control-label">电话：</label>

                    <div class="col-sm-6">
                        <input type="text" class="form-control required mobile" id="telephoneAdd"
                               name="telephoneAdd" placeholder="电话">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="fax" class="col-sm-3 control-label">传真：</label>

                    <div class="col-sm-6">
                        <input type="text" class="form-control fax" id="fax"
                               name="fax" placeholder="传真(可不填)">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="fax" class="col-sm-3 control-label">电子邮件：</label>

                    <div class="col-sm-6">
                        <input type="text" class="form-control emails" id="email"
                               name="email" placeholder="电子邮件(可不填)">
                    </div>
                 </div>
				 <div class="form-group">
                    <label for="descriptionAdd" class="col-sm-3 control-label">简介:</label>

                    <div class="col-sm-6">
                        <textarea class="form-control required" id="descriptionAdd" name="descriptionAdd"
                               style="resize:none;"  placeholder="简介"/></textarea>
                    </div>
                    
                </div> 
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-10">
                        <button type="button" id="saveInfo" class="btn btn-danger">保存</button>
                    </div>
                </div>
            </form>
            <script>
                $(function(){
                    $('input').iCheck({
                        labelHover : false,
                        cursor : true,
                        checkboxClass : 'icheckbox_square-blue',
                        radioClass : 'iradio_square-blue',
                        increaseArea : '20%'
                    });
                });
            </script>
        </div>
</body>
</html>