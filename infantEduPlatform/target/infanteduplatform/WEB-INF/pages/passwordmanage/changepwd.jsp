<%--
  Created by IntelliJ IDEA.
  User: PoohD
  Date: 2015/11/26
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
		<script src="static/js/common/base.js"></script>
		<script src="static/plugins/layer/layer.js"></script>
		<script src="static/plugins/validate/jquery.validate.js"></script>
		<script src="static/plugins/validate/jquery.validate.extend.js"></script>
		<script src="static/plugins/iCheck/icheck.min.js"></script>
		<script src="static/js/passwordmanage/changepwd.js"></script>
		<script type="text/javascript">
			$(function(){
				changepwd.init();
			});

		</script>
		<div>
			<!-- Content Wrapper. Contains page content -->
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>修改密码</h1>
				</section>
				<div class="col-sm-12" id="settings">
				<form id="test" class="form-horizontal">              
                <div class="form-group">
                    <label class="col-sm-3 control-label">原始密码:</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control required" size="50" id="oldpwd"
                         name="oldpwd">
                        <span id="oldpassword"></span>
                    </div>
                 </div>
                 <div class="form-group">
                    <label class="col-sm-3 control-label">新密码:</label>
                    <div class="col-sm-6" >
                        <input type="password" class="form-control required" size="50" name="newpwd" 
                        id="newpwd">
                        <span id="passstrength"></span>
                    </div>
                 </div>
                 <div class="form-group">
                    <label class="col-sm-3 control-label">确认新密码:</label>
                    <div class="col-sm-6">
                        <input type="password" name="confirmpwd" size="50" class="form-control required" 
                        id="confirmpwd" >
                    </div>
                 </div>
				 <div class="form-group">
				 <div class="col-sm-offset-3 col-sm-9">
                    <input type="button"  value="确定" id="change" class="btn btn-danger">
                    </div>
                </div> 
                </form>
            </div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
