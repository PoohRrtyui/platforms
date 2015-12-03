<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
        <link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
        <script src="static/plugins/iCheck/icheck.min.js"></script>
</head>
<body>
        <div class="col-sm-8" id="settings" style="margin-top: 60px;margin-left: 150px;'">
            <form class="form-horizontal" id="bureauForm">              
                <div class="form-group">
                    <label for="nameAdd" class="col-sm-3 control-label">部门名:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="nameAdd"
                               name="nameAdd" placeholder="部门名">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="personAdd" class="col-sm-3 control-label">负责人：</label>

                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="personAdd"
                               name="personAdd" placeholder="负责人">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="telephoneAdd" class="col-sm-3 control-label">电话：</label>

                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="telephoneAdd"
                               name="telephoneAdd" placeholder="电话">
                    </div>
                 </div>
				 <div class="form-group">
                    <label for="descriptionAdd" class="col-sm-3 control-label">简介:</label>

                    <div class="col-sm-6">
                        <textarea class="form-control" id="descriptionAdd" name="descriptionAdd"
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