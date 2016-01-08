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
		<script src="static/js/common/base.js"></script>
		<script src="static/plugins/layer/layer.min.js"></script>
		<script src="static/plugins/validate/jquery.validate.js"></script>
		<script src="static/plugins/validate/jquery.validate.extend.js"></script>
		<script src="static/js/changepwd/changepwd.js"></script>
		<script type="text/javascript">
			$(function(){
				changepwd.init();
			});

		</script>
		<div>
			<!-- Content Wrapper. Contains page content -->
			<div>
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>修改密码</h1>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
							<!-- /.box
								 -->
							<div class="box">
								<div class="box-header">
									<h3 class="box-title"></h3>
								</div>
								<!-- /.box-header -->
								<div class="box-body" align="center">
									<%--这里开始是数据，目前是假数据--%>
									<form id="test">
									<table id="example1" class="table table-bordered table-striped"
										align="center">
										
											<tr>
												<td width="31%" align="right">原始密码：</td>
												<td width="69%" align="left" valign="middle">
												<input type="password" name="oldpwd" size="50" 
												id="oldpwd" class="required" /> 
												<span id="oldpassword"></span></td>
											</tr>
											<tr>
												<td width="31%" align="right">新密码：</td>
												<td width="69%" align="left"><input type="password"
													name="newpwd" id="newpwd" size="50" 
													class="required" rangelength="8 20" />
													 <span id="passstrength"></span></td>
											</tr>
											<tr>
												<td width="31%" align="right">确认新密码：</td>
												<td width="69%" align="left" valign="middle"><input
													type="password" name="confirmpwd" size="50"
													class="required" id="confirmpwd"><span
													id="newpwdtrength"></span></td>
											</tr>
											<tr>
												<td width="31%"></td>
												<td valign="bottom"><input type="button" value="确定"
													id="change" class="btn"></td>
											</tr>
										
									</table>
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
