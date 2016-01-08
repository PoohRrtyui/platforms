<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
    	<link rel="stylesheet" href="static/plugins/zTree3.5.18/css/metroStyle/metroStyle.css">
        <script src="static/plugins/zTree3.5.18/js/jquery.ztree.all-3.5.min.js"></script>
        <script src="static/plugins/layer/layer.js"></script>
        
        <div class="col-xs-2">
            <div class="row">
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>
        <div class="col-xs-10">
            <div class="row">
                <div class="col-md-8">
                    <div class="box box-solid">
                        <div class="box-header with-border">
                            <i class="fa fa-text-width"></i>
                            <h3 class="box-title">部门基本信息</h3>
                            <div class="btn-group pull-right">
                                <button id="addDepartmentInfo" type="button" class="btn btn-info">增加部门
                                </button>
                                <button id="deleteDepartmentInfo" type="button" class="btn btn-info">删除部门
                                </button>
                                <button id="updateDepartmentInfo" type="button" class="btn btn-info">修改部门
                                </button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                <div class="form-horizontal" id="departmentForm">              
                <div class="form-group">
                    <label for="dename" class="col-sm-3 control-label">部门名：</label>
                    <div class="col-sm-6" style="height: 36px;">
                        <input type="text" class="form-control" id="dename" name="dename" style="border-style:none;background-color: white" readonly="true">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="queryorderNo" class="col-sm-3 control-label">排序号:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="queryorderNo" name="queryorderNo" style="border-style:none;background-color: white" readonly="true">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="panamecol" class="col-sm-3 control-label">负责人：</label>
                    <div class="col-sm-6" style="height: 36px;">
                        <input type="text" class="form-control" id="panamecol" name="panamecol" style="border-style:none;background-color: white" readonly="true">
                    </div>
                 </div>
                 <div class="form-group">
                    <label for="tephone" class="col-sm-3 control-label">电话：</label>
                    <div class="col-sm-6" style="height: 36px;">
                        <input type="text" class="form-control" id="tephone" name="tephone" style="border-style:none;background-color: white" readonly="true">
                    </div>
                 </div>
                  <div class="form-group">
                    <label for="queryfax" class="col-sm-3 control-label">传真：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="queryfax" name="queryfax" style="border-style:none;background-color: white" readonly="true">
                    </div>
                 </div>
                  <div class="form-group">
                    <label for="queryemail" class="col-sm-3 control-label">电子邮件：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="queryemail" name="queryemail" style="border-style:none;background-color: white" readonly="true">
                    </div>
                 </div>
				 <div class="form-group">
                    <label for="deption" class="col-sm-3 control-label">简介：</label>
                    <div class="col-sm-6" style="height: 100px;">
                         <textarea class="form-control" readonly="true" id="deption" name="deption"
                               style="resize:none;background-color: white;border-style:none"/></textarea>
                    </div>             
                </div> 
            </div>
                    </div>
                </div>
            </div>
        </div>
        
	<script type="text/javascript" src="static/js/department/departmentInfo.js"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>	