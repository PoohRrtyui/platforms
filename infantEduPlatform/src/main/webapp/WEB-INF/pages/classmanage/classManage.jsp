<%--
  Created by IntelliJ IDEA.
  User: PoohD
  Date: 2015/11/26
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/";
%>

<base href="<%=basePath%>">

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    <link rel="stylesheet" href="static/plugins/layer/skin/layer.css" type="text/css" />
 <script type="text/javascript" src="static/plugins/layer/layer.js"></script>
 <script type="text/javascript" src="static/js/classmanage/classManage.js"></script> 
 <script id="tpl" type="text/x-handlebars-template">
    		{{#each func}}
    		<button type="button" class="btn bt
			n-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
   			 {{/each}}
		</script>
 <style>
.addclasstable{
  height:44px;
}
</style>  
        <div>
            <!-- Content Wrapper. Contains page content -->
            <div>
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        数据列表
                        <small>班级管理</small>
                    </h1>
                    
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- /.box -->
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">班级管理</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                        <%--这里开始是数据，目前是假数据--%>
                                        <input type="button" id="addclass" value="增加" class="btn btn-info">
                                    <table id="classmanage" class="table table-bordered table-striped">
                                         <thead>
                                         <tr>
                                            <th>班级名称</th>
                                            <th>主班</th>
                                            <th>配班</th>
                                            <th>保育员</th>
                                            <th>建班年月</th>
                                            <th>学生数</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody></tbody> 
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

        </div>
        <!-- jQuery 2.1.4 -->

        <script type="text/javascript">
        $(function() {
        	classManage.init();
        });
            
     
        </script>
       
    </tiles:putAttribute>
</tiles:insertDefinition>

